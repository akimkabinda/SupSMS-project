/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sup.sms.business.IUserBusiness;
import sup.sms.entity.Client;
import sup.sms.entity.User;
import sup.sms.utils.EnumErrorMessage;

/**
 *
 * @author laurent
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    @EJB
    IUserBusiness userBusiness;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }
     
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String creditCardNumber = req.getParameter("creditcardnumber");
        String password = req.getParameter("password");

        /*Email don't already exist*/
        if(userBusiness.countByPhone(phone) > 0){
            req.getSession().setAttribute("error", EnumErrorMessage.Register_PhoneAlreadyExist);
            resp.sendRedirect("/register");
            return;
        }
        
        User user = new Client()
                .setFirstname(firstname)
                .setLastname(lastname)
                .setEmail(email)
                .setPhone(phone)
                .setCreditcardnumber(creditCardNumber)
                .setPassword(password)
                .setCreationdate(new Timestamp(new Date().getTime()));

        try{
            User newUser = userBusiness.signIn(user);
            if(newUser.getId() != 0){
                req.getSession().setAttribute("user", user);
                resp.sendRedirect("/app");
            }else{
                req.getSession().setAttribute("error", EnumErrorMessage.Register_UnexpectedErrorOccured);
                resp.sendRedirect("/register");
            }
        }catch(Exception e){
            req.getSession().setAttribute("error", EnumErrorMessage.Register_UnexpectedErrorOccured);
            resp.sendRedirect("/register");
        }
    }
}
