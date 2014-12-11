<%-- 
    Document   : register
    Created on : 11 déc. 2014, 08:45:18
    Author     : laurent
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <%@include file="/menus/index_menu.jspf" %>
    <body>
        <!--Menu-->
        <%@include file="/include/header.jspf" %>
        <!--menu-->
        
        
        <div class="content">
            <!-- notifications -->
            <%if (request.getSession().getAttribute("error") != null) {%>
                <div class="alert alert-danger" role="alert">
                    <%= request.getSession().getAttribute("error") %>
                </div>
            <%}%>
            <!-- notifications -->
            <h1>Registration</h1>
            <br/>
            <form class="form-horizontal" role="form" action="" method="POST" name="signinForm" novalidate>
              <div class="form-group">
                <div class="input-group">
                  <div class="input-group-addon"><span class="glyphicon glyphicon-pushpin"></span></div>
                  <input type="text"
                         autofocus="autofocus"
                         required
                         class="form-control" 
                         name="lastname" 
                         id="lastname" 
                         placeholder="Lastname"/>
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
                          placeholder="Firstname"/>
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
                          placeholder="Email">
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
                          placeholder="Phone number"/>
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
                          placeholder="Credit Card Number">
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
                  <button type="submit" 
                          class="btn btn-lg btn-default"
                          ng-disabled="signinForm.$invalid">Sign In</button>
                </div>
              </div>
            </form>
            
        </div>
        
        <%@include file="/include/footer.jspf" %>
    </body>
</html>
