<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:import url="/include/header.jsp">
    <c:param name="title" value="Home" />
</c:import>
<c:import url="/menus/index_menu.jsp"/>
<c:import url="/include/notifications.jsp"/>

<% String countUsers = (String)request.getAttribute("countUsers"); %>
<% String countMessages = (String)request.getAttribute("countMessages"); %>

<div id="index">
    <!-- User already log in -->
    <%if (request.getSession().getAttribute("user") != null) {%>
        <div id="alreadyConnected">
            <div class="jumbotron">
                <p class="lead">You are connected.</p>
                <p>
                    <a class="btn btn-lg btn-default" href="/app/conversation" role="button">Application Access</a>
                    <a class="btn btn-lg btn-default" href="/logout" role="button">Log out</a>
                </p>
            </div>
        </div>
    <%}%>
    <!-- User alreday log in -->

    <h1>Welcome to SupSMS</h1>
    <div class="jumbotron">
        <div class="row stats">
            <div class="col-md-6">
                <div class="icon">
                    <i class="fa fa-group"></i>
                </div>
                <div class="count">
                    <%= countUsers %>
                </div>
                <div class="desc">
                    Users registered !
                </div>
            </div>
            <div class="col-md-6">
                <div class="icon">
                    <i class="fa fa-send"></i>
                </div>
                <div class="count">
                    <%= countMessages %>
                </div>
                <div class="desc">
                    Messages sent !
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="/include/footer.jsp"/>