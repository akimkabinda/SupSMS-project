/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sup.sms.service.StatsService;

/**
 *
 * @author laurent
 */
@WebServlet(name = "HomeController", urlPatterns = {"/index"})
public class HomeController extends HttpServlet {

    @EJB
    StatsService statsService;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("countUsers", String.valueOf(statsService.countUsers()));
        req.setAttribute("countMessages", String.valueOf(statsService.countMessages()));
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    
}
