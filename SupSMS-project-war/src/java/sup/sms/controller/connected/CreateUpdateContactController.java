/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.controller.connected;

import java.io.IOException;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sup.sms.entity.Contact;
import sup.sms.entity.User;
import sup.sms.service.ContactService;
import sup.sms.utils.EnumErrorMessage;
import sup.sms.utils.EnumWarningMessage;

/**
 *
 * @author laurent
 */
@WebServlet(name = "CreateUpdateContact", urlPatterns = {"/app/createUpdateContact"})
public class CreateUpdateContactController extends HttpServlet {

    @EJB
    ContactService contactService;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        int id = Integer.parseInt(req.getParameter("id"));
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        
        if(id != 0){
            //Update
            Contact contactToUpdate = contactService.find(id);
            if(!contactToUpdate.getContactFirstname().equals(firstname)){
                contactToUpdate.setContactFirstname(firstname);
            }
            if(!contactToUpdate.getContactLastname().equals(lastname)){
                contactToUpdate.setContactLastname(lastname);
            }
            if(!contactToUpdate.getContactEmail().equals(email)){
                contactToUpdate.setContactEmail(email);
            }
            if(!contactToUpdate.getContactPhone().equals(phone)){
                contactToUpdate.setContactPhone(phone);
            }
            if(!contactToUpdate.getContactAddress().equals(address)){
                contactToUpdate.setContactAddress(address);
            }
            
            String errors = contactService.ValidContactObject(contactToUpdate);

            req.setAttribute("tempContact", contactToUpdate);

            if(errors.length() > 0){
                req.setAttribute("error", errors);
                req.getRequestDispatcher("/user/createUpdateContact.jsp").forward(req, resp);
                return;
            }
            
            try{
                contactService.update(contactToUpdate);
                resp.sendRedirect("/app/contacts");  
            }catch(Exception e){
                req.setAttribute("error", EnumErrorMessage.UnexpectedErrorOccured);
                req.getRequestDispatcher("/user/createUpdateContact.jsp").forward(req, resp);
            }
        }else{
            //Create
            Contact contact = new Contact()
                    .setContactAddress(address)
                    .setContactCreationdate(new Date())
                    .setContactEmail(email)
                    .setContactFirstname(firstname)
                    .setContactLastname(lastname)
                    .setContactPhone(phone)
                    .setContactDeleted(false)
                    .setContactOwner(user);
            
            String errors = contactService.ValidContactObject(contact);

            req.setAttribute("tempContact", contact);

            if(errors.length() > 0){
                req.setAttribute("error", errors);
                req.getRequestDispatcher("/user/createUpdateContact.jsp").forward(req, resp);
                return;
            }
            
            try{
                contactService.save(contact);
                resp.sendRedirect("/app/contacts");  
            }catch(Exception e){
                req.setAttribute("error", EnumErrorMessage.UnexpectedErrorOccured);
                req.getRequestDispatcher("/user/createUpdateContact.jsp").forward(req, resp);
            }
        }
        
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        if(req.getParameter("contactId") != null){
            int contactId = Integer.parseInt(req.getParameter("contactId"));
            Contact contact = contactService.find(contactId);
            if(contact != null){
                req.setAttribute("tempContact", contact);
            }else{
                req.setAttribute("warning", EnumWarningMessage.Update_Contact_Not_Allowed);
            }
            req.getRequestDispatcher("/user/createUpdateContact.jsp").forward(req, resp);
        }else{
            req.getRequestDispatcher("/user/createUpdateContact.jsp").forward(req, resp);
        }        
    }

}
