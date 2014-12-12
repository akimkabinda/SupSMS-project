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
import sup.sms.business.UserBusiness;
import sup.sms.entity.User;
import sup.sms.utils.EnumErrorMessage;
import sup.sms.utils.EnumInfoMessage;

/**
 *
 * @author laurent
 */
@WebServlet(name = "AdminAppController", urlPatterns = {"/admin/users"})
public class AdminAppController extends HttpServlet{
    
    @EJB
    IUserBusiness userBusiness;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", userBusiness.findAll());
        req.getRequestDispatcher("/admin/users.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        int id = Integer.parseInt(req.getParameter("id"));
        
        if(type.equals("delete")){
            if(userBusiness.delete(id)){
                req.setAttribute("info", EnumInfoMessage.Admin_User_Deleted);
                resp.sendRedirect("/admin/users");
            }else{
                req.setAttribute("error", EnumErrorMessage.UnexpectedErrorOccured);
                req.getRequestDispatcher("/admin/users.jsp").forward(req, resp);
            }
        }

        
    }
}
