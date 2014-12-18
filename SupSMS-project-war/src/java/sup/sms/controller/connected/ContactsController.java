/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.controller.connected;

import java.io.IOException;
import java.util.List;
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
import sup.sms.utils.EnumInfoMessage;

/**
 *
 * @author laurent
 */
@WebServlet(name = "ContactsController", urlPatterns = {"/app/contacts"})
public class ContactsController extends HttpServlet {

    @EJB
    ContactService contactService;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        List<Contact> contacts = contactService.findContactsByUser(user);
        req.setAttribute("contacts", contacts);
        req.getRequestDispatcher("/user/contacts.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        int id = Integer.parseInt(req.getParameter("id"));
        Contact contact = contactService.find(id);
        
        if(contactService.delete(contact)){
            List<Contact> contacts = contactService.findContactsByUser(user);
            req.setAttribute("contacts", contacts);
            req.setAttribute("info", EnumInfoMessage.Contact_Deleted);
            req.getRequestDispatcher("/user/contacts.jsp").forward(req, resp);
        }else{
            req.setAttribute("error", EnumErrorMessage.UnexpectedErrorOccured);
            req.getRequestDispatcher("/user/contacts.jsp").forward(req, resp);
        }
    }

}
