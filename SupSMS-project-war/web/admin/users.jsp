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

<c:import url="/include/footer.jsp"/>
 