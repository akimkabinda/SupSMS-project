<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:import url="/include/header.jsp">
    <c:param name="title" value="Log In" />
</c:import>
<c:import url="/menus/index_menu.jsp"/>
<c:import url="/include/notifications.jsp"/>

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

<c:import url="/include/footer.jsp"/>
