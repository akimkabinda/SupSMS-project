/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.security;

import java.io.IOException;
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

/**
 *
 * @author laurent
 */
/**
 * Admin page(s) security : if user try to access to an admin page 
 * and is not connected as an admin, 
 * he will be redirect on the login page
 * @author laurent
 */
@WebFilter(urlPatterns="/admin/*")
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        User user = (User)httpRequest.getSession().getAttribute("user");
        
        if(user == null || user instanceof Client) {
            httpResponse.sendRedirect(request.getServletContext().getContextPath() + "/login");            
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        
    }
    
}
