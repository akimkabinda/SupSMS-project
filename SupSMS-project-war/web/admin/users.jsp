<%@page import="java.util.Date"%>
<%@page import="sup.sms.entity.Invoice"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="sup.sms.entity.Client"%>
<%@page import="java.util.List"%>
<%@page import="sup.sms.entity.User"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:import url="../include/header.jsp">
    <c:param name="title" value="Message" />
</c:import>
<c:import url="../menus/app_menu.jsp"/>
<c:import url="../include/notifications.jsp"/>
    
<% List<User> users = (List<User>)request.getAttribute("users"); %>
<% SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy hh:mm"); %>
<div id="invoice">
    <h1>Manage users</h1>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Identity</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Registration date</th>
                <th>Invoice is paid</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        <% for(User u : users){ %>
            <tr class="<%= u instanceof Client ? "info" : "success" %>">
                <td><%= u.getFirstname() %></br><%= u.getLastname()%></td>
                <td><%= u.getEmail()%></td>
                <td><%= u.getPhone()%></td>
                <td><%= formater.format(u.getCreationdate()) %></td>
                <td class="cellContentCentered">
                    <button type="button" class="btn btn-xs btn-info" data-toggle="popover" title="Invoices paid" 
                            data-content="
                            <% if(u.getInvoices().size() > 0){ %>
                            <% for(Invoice i : u.getInvoices()){ %>
                                <%= formater.format(i.getBeginDate()) %> 
                                - 
                                <% if(i.getEndDate().before(new Date())){ %>
                                    <i class='fa fa-remove'></i> (Finished)
                                <%}else{%>
                                    <i class='fa fa-check'></i> (On air)
                                <%}%>
                                <br/>
                            <%}%>
                            <%}else{%>
                                No invoice found.
                            <%}%>
                            ">
                        Invoices</button>
                </td>
                <td class="cellContentCentered">
                    <form action="" method="POST">
                        <input type="hidden" value="delete" name="type"/>
                        <input type="hidden" value="<%= u.getId()%>" name="id"/>
                        <input type="submit" value="Delete" class="btn btn-danger btn-xs"/>
                    </form>
                </td>
            </tr>
        <%}%>
        </tbody>
    </table>
    <span class="bg-success">
        <i class="fa fa-info"></i>
        Admin
    </span>
    <span class="bg-info">
        <i class="fa fa-info"></i>
        Client
    </span>      
</div>

<c:import url="/include/footer.jsp"/>
 