<%@page import="sup.sms.entity.User"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:import url="/include/header.jsp">
    <c:param name="title" value="Invoice" />
</c:import>
<c:import url="/menus/index_menu.jsp"/>
<c:import url="/include/notifications.jsp"/>

<% User user=(User)session.getAttribute("user"); %>

<div id="invoice">
    <div class="jumbotron">
        <div class="lead">
            <div>Invoice not already paid... :(</div>
            <div>Please proceed to your payment :</div>
        </div>
        <div class="creditCard" >
            <div>Your Credit card number :</div>
            <div><%= user.getCreditcardnumber() %></div>
        </div>
        <form action="" method="POST">
            <label for="accept">
                <input type="checkbox" id="accept" name="agreement" value="ok"/>
                I have read and I accept the terms of SupSMS.
            </label>
            <div>
                <input type="submit" class="btn btn-lg btn-default" role="button" value="Proceed to payment"/>
                <a class="btn btn-lg btn-default" href="/logout" role="button">Logout</a>
            </div>
        </form>

    </div>
</div>
    
<c:import url="/include/footer.jsp"/>
