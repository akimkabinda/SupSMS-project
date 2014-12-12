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
<p class="bg-success">
    ADMIN
</p>
<p class="bg-info">
    CLIENT
</p>
<table class="table table-striped">
    <tr>
        <th>Identity</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Registration date</th>
        <th>Action</th>
    </tr>
    <% for(User u : users){ %>
        <tr class="<%= u instanceof Client ? "info" : "success" %>">
            <td><%= u.getFirstname() %></br><%= u.getLastname()%></td>
            <td><%= u.getEmail()%></td>
            <td><%= u.getPhone()%></td>
            <td><%= formater.format(u.getCreationdate()) %></td>
            <td>
                <form action="" method="POST">
                    <input type="hidden" value="delete" name="type"/>
                    <input type="hidden" value="<%= u.getId()%>" name="id"/>
                    <input type="submit" value="Delete" class="btn btn-danger btn-xs"/>
                </form>
            </td>
        </tr>
    <%}%>
</table>

<c:import url="/include/footer.jsp"/>
 