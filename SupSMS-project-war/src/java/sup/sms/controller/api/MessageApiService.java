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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sup.sms.entity.Message;
import sup.sms.entity.User;
import sup.sms.service.MessageService;
import sup.sms.service.UserService;
import sup.sms.utils.MessageFacade;

/**
 *
 * @author laurent
 */
@Path("/message/{id}")
public class MessageApiService {
    @EJB
    MessageService messagesService;
    
    @EJB
    UserService userService;
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path(value = "/{interlocutor}")
    public List<Message> getConversation(@PathParam("id") long userId, @PathParam("interlocutor") String interlocutor){
        User user = userService.find(userId);
        return messagesService.getConversation(user, interlocutor); 
    }
    
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Path(value = "/{interlocutor}")
    public Message sendMessage(MessageFacade message, @PathParam("id") long userId, @PathParam("interlocutor") String interlocutor){
        return messagesService.sendMessage(userId, interlocutor, message);
    }
}
