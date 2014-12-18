/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.security;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sup.sms.entity.Client;
import sup.sms.entity.User;
import sup.sms.service.InvoiceService;

/**
 *
 * @author laurent
 */
/**
 * Connected page(s) security : if user try to access to an app page 
 * and is not connected as an client or an admin, 
 * he will be redirect on the login page
 * REMARK : we will test for clients that they are already paid the SupSMS service. 
 * If not they will be redirect on the invoice processing page.
 * @author laurent
 */
@WebFilter(urlPatterns="/app/*")
public class ConnectedFilter implements Filter{

    @EJB
    InvoiceService invoiceService;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        User user = (User)httpRequest.getSession().getAttribute("user");
        
        if(user == null) {
            httpResponse.sendRedirect(request.getServletContext().getContextPath() + "/login");
        } else {
            //Invoice test : we detect if the client has already paid his invoice for the current month
            if(user instanceof Client && !invoiceService.invoiceHasBeenPaid(user)){
                httpResponse.sendRedirect(request.getServletContext().getContextPath() + "/invoice");
                return;
            }
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        
    }
    
}
