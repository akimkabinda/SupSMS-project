/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.controller.connected;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sup.sms.business.IInvoiceBusiness;
import sup.sms.business.InvoiceBusiness;
import sup.sms.entity.Invoice;
import sup.sms.entity.User;
import sup.sms.utils.EnumErrorMessage;

/**
 *
 * @author laurent
 */
@WebServlet(name = "InvoiceController", urlPatterns = {"/invoice"})
public class InvoiceController extends HttpServlet {

    @EJB
    IInvoiceBusiness invoiceBusiness;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/invoice.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        
        Invoice invoice = new Invoice()
                .setBeginDate(new Date())
                .setOwner(user);
        
        try{
            invoiceBusiness.save(invoice);
            user.getInvoices().add(invoice);
            resp.sendRedirect("/app/conversation");
        }catch(Exception e){
            req.setAttribute("error", EnumErrorMessage.UnexpectedErrorOccured);
        }
        
    }
}
