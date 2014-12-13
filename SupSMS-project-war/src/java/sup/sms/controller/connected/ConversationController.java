/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.controller.connected;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sup.sms.entity.User;
import sup.sms.utils.ConversationFacade;
import java.util.List;
import sup.sms.service.MessageService;

/**
 *
 * @author laurent
 */
@WebServlet(name = "ConversationController", urlPatterns = {"/app/conversation"})
public class ConversationController extends HttpServlet {

    @EJB
    MessageService messageService;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        List<ConversationFacade> conversations = messageService.getConversations(user);
        req.setAttribute("conversations", conversations);
        req.getRequestDispatcher("/user/conversation.jsp").forward(req, resp);
    }

}
