<%@page import="sup.sms.entity.User"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:import url="../include/header.jsp">
    <c:param name="title" value="Message" />
</c:import>
<c:import url="../menus/app_menu.jsp"/>
<c:import url="../include/notifications.jsp"/>
    
<% User user = (User)request.getSession().getAttribute("user"); %>
<% User tempUser = (User)request.getAttribute("tempUser"); %>

<h1>Profile</h1>
<br/>
<form class="form-horizontal" role="form" action="" method="POST" name="profileForm">
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
             value="<%= tempUser != null ? tempUser.getLastname() : user.getLastname() %>"/>
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
              value="<%= tempUser != null ? tempUser.getFirstname(): user.getFirstname()%>"/>
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
              value="<%= tempUser != null ? tempUser.getEmail(): user.getEmail()%>">
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
              disabled="disabled"
              placeholder="Phone number"
              value="<%= user.getPhone()%>"/>
    </div>
  </div>
  <div class="form-group">
    <div class="input-group">
      <div class="input-group-addon"><span class="glyphicon glyphicon-credit-card"></span></div>
      <input type="text" 
              required
              class="form-control" 
              name="creditcardnumber" 
              id="creditcardnumber" 
              placeholder="Credit Card Number"
              value="<%= tempUser != null ? tempUser.getCreditcardnumber() : user.getCreditcardnumber()%>">
    </div>
  </div>
  <div class="form-group">
    <div>
      <input type="submit" 
             class="btn btn-lg btn-default" value="Update profile" />
    </div>
  </div>
</form>

<c:import url="/include/footer.jsp"/>
