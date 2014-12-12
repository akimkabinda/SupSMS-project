/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.controller;

import java.io.IOException;
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
import sup.sms.business.IUserBusiness;
import sup.sms.entity.User;
import sup.sms.utils.EnumErrorMessage;
import sup.sms.utils.EnumInfoMessage;

/**
 *
 * @author laurent
 */
@WebServlet(name = "ProfileController", urlPatterns = {"/app/profile"})
public class ProfileController extends HttpServlet {
    
    @EJB
    IUserBusiness userBusiness;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/user/profile.jsp").forward(req, resp);
    }
     
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String creditCardNumber = req.getParameter("creditcardnumber");

        User user = (User)req.getSession().getAttribute("user");
        
        if(!user.getFirstname().equals(firstname)){
            user.setFirstname(firstname);
        }
        if(!user.getLastname().equals(lastname)){
            user.setLastname(lastname);
        }
        if(!user.getEmail().equals(email)){
            user.setEmail(email);
        }
        if(!user.getCreditcardnumber().equals(creditCardNumber)){
            user.setCreditcardnumber(creditCardNumber);
        }
        if(!user.getFirstname().equals(firstname)){
            user.setFirstname(firstname);
        }
        
        /*VALIDATIONS*/
        ValidatorFactory factory = 
		Validation.buildDefaultValidatorFactory();

        Validator validator = factory.getValidator();

        Set<ConstraintViolation<User>> constraintViolations; 
        constraintViolations = validator.validate(user);
        
        StringBuilder errors = new StringBuilder();
        
        if(constraintViolations.size() > 0){
            for (ConstraintViolation<User> constraintViolation : constraintViolations) {
                errors.append(constraintViolation.getMessage()).append("<br/>");
            }
        }
        
        req.setAttribute("tempUser", user);
        
        if(errors.toString().length() > 0){
            req.setAttribute("error", errors.toString());
            req.getRequestDispatcher("/user/profile.jsp").forward(req, resp);
            return;
        }

        try{
            User newUser = userBusiness.update(user);
            req.getSession().setAttribute("user", newUser);
            req.setAttribute("info", EnumInfoMessage.Profile_Updated);
            req.getRequestDispatcher("/user/profile.jsp").forward(req, resp);
        }catch(Exception e){
            req.setAttribute("error", EnumErrorMessage.UnexpectedErrorOccured);
            req.getRequestDispatcher("/user/profile.jsp").forward(req, resp);
            return;
        }
    }

}
