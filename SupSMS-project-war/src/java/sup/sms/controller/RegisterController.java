/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import sup.sms.entity.Client;
import sup.sms.entity.User;
import sup.sms.service.UserService;
import sup.sms.utils.EnumErrorMessage;

/**
 *
 * @author laurent
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    @EJB
    UserService userService;
    
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

        User user = new Client()
                .setFirstname(firstname)
                .setLastname(lastname)
                .setEmail(email)
                .setPhone(phone)
                .setCreditcardnumber(creditCardNumber)
                .setPassword(password)
                .setCreationdate(new Timestamp(new Date().getTime()))
                .setDeleted(false);
        
        /*VALIDATIONS*/
        ValidatorFactory factory = 
		Validation.buildDefaultValidatorFactory();

        Validator validator = factory.getValidator();

        Set<ConstraintViolation<User>> constraintViolations; 
        constraintViolations = validator.validate(user);
        
        StringBuilder errors = new StringBuilder();
        
        /*Phone number don't already exist*/
        if(userService.countByPhone(phone) > 0){
            errors.append(EnumErrorMessage.Register_PhoneAlreadyExist).append("<br/>");
        }

        if(constraintViolations.size() > 0){
            for (ConstraintViolation<User> constraintViolation : constraintViolations) {
                errors.append(constraintViolation.getMessage()).append("<br/>");
            }
        }
        req.setAttribute("tempUser", user);
        
        if(errors.toString().length() > 0){
            req.setAttribute("error", errors.toString());
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        try{
            User newUser = userService.save(user);
            if(newUser.getId() != 0){
                //We get the new user directly in database to have the right context (else error occured during registration)
                User currentUser = userService.logIn(newUser.getPhone(), newUser.getPassword());
                req.getSession().setAttribute("user", currentUser);
                resp.sendRedirect("/app/conversation");
                return;
            }else{
                req.setAttribute("error", EnumErrorMessage.UnexpectedErrorOccured);
                req.getRequestDispatcher("/register.jsp").forward(req, resp);
                return;
            }
        }catch(Exception e){
            req.setAttribute("error", EnumErrorMessage.UnexpectedErrorOccured);
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }
    }
    
}
