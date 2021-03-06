<%@page import="sup.sms.entity.User"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:import url="/include/header.jsp">
    <c:param name="title" value="Register" />
</c:import>
<c:import url="/menus/index_menu.jsp"/>
<c:import url="/include/notifications.jsp"/>

<% User tempUser = (User)request.getAttribute("tempUser"); %>

<h1>Registration</h1>
<br/>
<form class="form-horizontal" role="form" action="" method="POST" name="signinForm">
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
             value="<%= tempUser == null ? "" : tempUser.getLastname() %>"/>
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
              value="<%= tempUser == null ? "" : tempUser.getFirstname()%>"/>
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
              value="<%= tempUser == null ? "" : tempUser.getEmail()%>">
    </div>
  </div>
  <div class="form-group">
    <div class="input-group">
      <div class="input-group-addon"><span class="glyphicon glyphicon-phone"></span></div>
      <input type="text" 
              required
              class="form-control" 
              name="phone" 
              id="phone" 
              placeholder="Phone number"
              value="<%= tempUser == null ? "" : tempUser.getPhone()%>"/>
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
              value="<%= tempUser == null ? "" : tempUser.getCreditcardnumber()%>">
    </div>
  </div>
  <div class="form-group">
    <div class="input-group">
      <div class="input-group-addon"><span class="glyphicon glyphicon-flash"></span></div>
      <input type="password" 
              required
              class="form-control" 
              name="password" 
              id="password" 
              placeholder="Password">
    </div>
  </div>
  <div class="form-group">
    <div>
      <input type="submit" 
             class="btn btn-lg btn-default" value="Sign In" />
    </div>
  </div>
</form>
                  
<c:import url="/include/footer.jsp"/>
 
