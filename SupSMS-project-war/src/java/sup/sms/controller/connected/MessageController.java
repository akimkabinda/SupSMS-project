/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.controller.connected;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sup.sms.entity.User;
import sup.sms.service.ContactService;

/**
 *
 * @author laurent
 */
@WebServlet(name = "MessageController", urlPatterns = {"/app/message"})
public class MessageController extends HttpServlet {

    @EJB
    ContactService contactService;
    
   @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        req.setAttribute("contacts", contactService.findContactsByUser(user));
        req.getRequestDispatcher("/user/message.jsp").forward(req, resp);
    }

}
