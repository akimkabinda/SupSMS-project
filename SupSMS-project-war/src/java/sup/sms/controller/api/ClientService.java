/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.controller.api;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import sup.sms.entity.Contact;
import sup.sms.entity.User;
import sup.sms.service.ContactService;
import sup.sms.service.UserService;

/**
 *
 * @author laurent
 */
@Path("/user/{id}")
public class ClientService {
    
    @EJB
    UserService userService;
    
    @EJB
    ContactService contactService;
    
    @GET
    @Consumes({"application/xml", "application/json"})
    @Path(value = "contacts")
    public List<Contact> GetContacts(){
        User user = userService.find(1);
        return contactService.findContactsByUser(user);
    }
}
