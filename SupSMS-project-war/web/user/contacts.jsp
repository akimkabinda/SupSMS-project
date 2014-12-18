<%@page import="java.util.List"%>
<%@page import="sup.sms.entity.Contact"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:import url="../include/header.jsp">
    <c:param name="title" value="Contacts" />
</c:import>
<c:import url="../menus/app_menu.jsp"/>
<c:import url="../include/notifications.jsp"/>

<% List<Contact> contacts = (List<Contact>)request.getAttribute("contacts"); %>
<div id="contacts">
    <h1>Contacts</h1>
    <a href="/app/createUpdateContact" class="btn btn-default btn-lg">New contact</a>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Identity</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Address</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        <% for(Contact c : contacts){ %>
        <tr class="contact">
                <td><%= c.getFirstname() %></br><%= c.getLastname()%></td>
                <td><%= c.getEmail()%></td>
                <td class="phone"><%= c.getPhone()%></td>
                <td class="address"><%= c.getAddress()%></td>
                <td class="cellContentCentered">
                    <form action="" method="POST">
                        <input type="hidden" value="delete" name="type"/>
                        <input type="hidden" value="<%= c.getId()%>" name="id"/>
                        <input type="submit" value="Delete" class="btn btn-danger btn-xs"/>
                    </form>
                    <a class="btn btn-warning btn-xs" href="/app/createUpdateContact?contactId=<%= c.getId()%>">Update</a>
                </td>
            </tr>
        <%}%>
        </tbody>
    </table>
</div>
    
<c:import url="../include/footer.jsp"/>
 
