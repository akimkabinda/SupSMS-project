/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.controller;

import java.io.IOException;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sup.sms.entity.Invoice;
import sup.sms.entity.User;
import sup.sms.service.InvoiceService;
import sup.sms.utils.EnumErrorMessage;
import sup.sms.utils.EnumWarningMessage;

/**
 *
 * @author laurent
 */
@WebServlet(name = "InvoiceController", urlPatterns = {"/invoice"})
public class InvoiceController extends HttpServlet {

    @EJB
    InvoiceService invoiceService;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        if(user == null){
            resp.sendRedirect("/login");
        }
        if(invoiceService.invoiceHasBeenPaid(user)){
            resp.sendRedirect("/app/conversation");
        }
        req.getRequestDispatcher("/invoice.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        Boolean checked = (req.getParameter("agreement")!=null && req.getParameter("agreement").contentEquals("ok"))?true:null;
        if(checked == null || !checked){
            req.setAttribute("warning", EnumWarningMessage.Invoice_PleaseCheck);
            req.getRequestDispatcher("/invoice.jsp").forward(req, resp);
            return;
        }
        
        Invoice invoice = new Invoice()
                .setBeginDate(new Date())
                .setOwner(user);
        
        try{
            invoiceService.save(invoice);
            user.getInvoices().add(invoice);
            resp.sendRedirect("/app/conversation");
        }catch(Exception e){
            req.setAttribute("error", EnumErrorMessage.UnexpectedErrorOccured);
            req.getRequestDispatcher("/invoice.jsp").forward(req, resp);
        }
        
    }
}
