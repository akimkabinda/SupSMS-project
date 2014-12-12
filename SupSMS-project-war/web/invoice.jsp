<%@page import="sup.sms.entity.User"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:import url="/include/header.jsp">
    <c:param name="title" value="Invoice" />
</c:import>
<c:import url="/menus/index_menu.jsp"/>
<c:import url="/include/notifications.jsp"/>

<% User user=(User)session.getAttribute("user"); %>

<div id="index">
    <%if(user != null) {%>
        <div id="alreadyConnected">
            <div class="jumbotron">
                <p class="lead">Invoice not already paid... :( Please proceed to your payment :</p>
                <p>
                    Your Credit card number :<br/>
                    <%= user.getCreditcardnumber() %>
                </p>
                <div>
                    <form action="" method="POST">
                        <input type="submit" class="btn btn-lg btn-default" role="button" value="Proceed to payment"/>
                    </form>

                    <a class="btn btn-lg btn-default" href="/logout" role="button">Logout</a>
                </div>
            </div>
        </div>
    <%}%>
</div>

<c:import url="/include/footer.jsp"/>
