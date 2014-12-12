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
import sup.sms.business.IInvoiceBusiness;
import sup.sms.business.InvoiceBusiness;
import sup.sms.entity.Client;
import sup.sms.entity.User;

/**
 *
 * @author laurent
 */
@WebFilter(urlPatterns="/app/*")
public class ConnectedFilter implements Filter{

    @EJB
    IInvoiceBusiness invoiceBusiness;
    
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
            if(user instanceof Client && !invoiceBusiness.invoiceHasBeenPaid(user)){
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
