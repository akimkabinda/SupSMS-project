/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.controller.api;

import javax.ejb.EJB;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import sup.sms.entity.User;

/**
 *
 * @author laurent
 */

@Path("/user")
public class UserService {

    @EJB
    UserService userService;
    
    @GET
    @Path(value = "login")
    public User logIn(
            @DefaultValue("") @QueryParam("phone") String phone,
            @DefaultValue("") @QueryParam("password") String password
    ){
        return userService.logIn(phone, password);
    }
}
