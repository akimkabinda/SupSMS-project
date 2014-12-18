<%@page import="sup.sms.entity.Contact"%>
<%@page import="sup.sms.entity.User"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:import url="../include/header.jsp">
    <c:param name="title" value="Message" />
</c:import>
<c:import url="../menus/app_menu.jsp"/>
<c:import url="../include/notifications.jsp"/>

<% String warning = (String)request.getAttribute("warning"); %>
<% int contactToUpdate = request.getParameter("contactId") != null ? Integer.parseInt(request.getParameter("contactId")) : 0; %>
<% Contact tempContact = (Contact)request.getAttribute("tempContact"); %>

<h1>Contact</h1>
<br/>
<%if (warning == null) {%>
<form class="form-horizontal" role="form" action="" method="POST" name="contactForm">
  <input type="hidden" name="id" value="<%= contactToUpdate %>"/>
  <div class="form-group">
    <div class="input-group">
      <div class="input-group-addon"><span class="glyphicon glyphicon-pushpin"></span></div>
      <input type="text"
             autofocus="autofocus"
             required
             class="form-control" 
             name="lastname" 
             id="lastname" 
             placeholder="Lastname"
             value="<%= tempContact != null ? tempContact.getLastname() : "" %>"/>
    </div>
  </div>
  <div class="form-group">
    <div class="input-group">
      <div class="input-group-addon"><span class="glyphicon glyphicon-pushpin"></span></div>
      <input type="text" 
              required
              class="form-control" 
              name="firstname" 
              id="firstname" 
              placeholder="Firstname"
              value="<%= tempContact != null ? tempContact.getFirstname(): "" %>"/>
    </div>
  </div>
  <div class="form-group">
    <div class="input-group">
      <div class="input-group-addon"><span class="glyphicon glyphicon-globe"></span></div>
      <input type="email" 
              required
              class="form-control" 
              name="email" 
              id="email" 
              placeholder="Email"
              value="<%= tempContact != null ? tempContact.getEmail() : "" %>">
    </div>
  </div>
  <div class="form-group">
    <div class="input-group">
      <div class="input-group-addon"><span class="glyphicon glyphicon-phone"></span></div>
      <input type="text" 
              required
              data-phone-validation
              class="form-control" 
              name="phone" 
              id="phone" 
              placeholder="Phone number"
              value="<%= tempContact != null ? tempContact.getPhone() : "" %>"/>
    </div>
  </div>
  <div class="form-group">
    <div class="input-group">
      <div class="input-group-addon"><span class="fa fa-home"></span></div>
      <input type="text" 
              required
              class="form-control" 
              name="address" 
              id="creditcardnumber" 
              placeholder="Address"
              value="<%= tempContact != null ? tempContact.getAddress() : ""%>">
    </div>
  </div>
  <div class="form-group">
    <div>
      <input type="submit" 
             class="btn btn-lg btn-default" value="<%= contactToUpdate != 0 ? "Update" : "Create" %> contact" />
    </div>
  </div>
</form>
<%}%>
<c:import url="/include/footer.jsp"/>
