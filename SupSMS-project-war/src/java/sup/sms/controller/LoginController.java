/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.controller;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sup.sms.entity.User;
import sup.sms.service.UserService;
import sup.sms.utils.EnumErrorMessage;

/**
 *
 * @author laurent
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    @EJB
    UserService userService;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
     
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        User user = userService.logIn(phone, password);
        if(user != null){
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/app/conversation");
        }else{
            req.setAttribute("error", EnumErrorMessage.Login_BadCredentials);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

}
