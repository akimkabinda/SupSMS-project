/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.controller.api;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import sup.sms.entity.Contact;
import sup.sms.service.ContactService;

/**
 *
 * @author laurent
 */
@Path("/user/{id}")
public class ClientService {
    
    @EJB
    ContactService contactService;
    
    @GET
    @Path(value = "contacts")
    public List<Contact> GetContacts(@PathParam("id") long clientId){
        return contactService.getClientContacts(clientId);
    }
}
