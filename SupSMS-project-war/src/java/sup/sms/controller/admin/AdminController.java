/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.controller.admin;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sup.sms.service.UserService;
import sup.sms.utils.EnumErrorMessage;
import sup.sms.utils.EnumInfoMessage;

/**
 *
 * @author laurent
 */
@WebServlet(name = "AdminController", urlPatterns = {"/admin/users"})
public class AdminController extends HttpServlet{
    
    @EJB
    UserService userService;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", userService.findAll());
        req.getRequestDispatcher("/admin/users.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        int id = Integer.parseInt(req.getParameter("id"));
        
        if(type.equals("delete")){
            if(userService.delete(id)){
                req.setAttribute("info", EnumInfoMessage.Admin_User_Deleted);
                req.getRequestDispatcher("/admin/users.jsp").forward(req, resp);
            }else{
                req.setAttribute("error", EnumErrorMessage.UnexpectedErrorOccured);
                req.getRequestDispatcher("/admin/users.jsp").forward(req, resp);
            }
        }
    }
}
