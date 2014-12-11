<%-- 
    Document   : login
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
        
        <!-- notifications -->
        <%if (request.getParameter("loginError") != null) {%>
            <div class="alert alert-danger" role="alert">
                Bad credentials. 
            </div>
        <%}%>
        <!-- notifications -->
        
        <div class="content">
            <h1>Authentication</h1>
            <br/>
            <form class="form-horizontal" role="form" action="" method="POST" >
              <div class="form-group">
                <div class="input-group">
                  <div class="input-group-addon"><span class="glyphicon glyphicon-phone"></span></div>
                  <input type="tel" class="form-control" name="phone" autofocus id="phone" placeholder="Phone number"/>
                </div>
              </div>
              <div class="form-group">
                <div class="input-group">
                  <div class="input-group-addon"><span class="glyphicon glyphicon-flash"></span></div>
                  <input type="password" class="form-control" name="password" id="password" placeholder="Password">
                </div>
              </div>
              <div class="form-group">
                <div>
                  <button type="submit" class="btn btn-lg btn-default">Log In</button>
                </div>
              </div>
            </form>
        </div>
        
        
        <%@include file="/include/footer.jspf" %>
    </body>
</html>
