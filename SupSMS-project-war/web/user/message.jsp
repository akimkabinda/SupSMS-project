<%@page import="sup.sms.entity.User"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:import url="../include/messageHeader.jsp">
    <c:param name="title" value="Message" />
</c:import>
<c:import url="../menus/app_menu.jsp"/>
<c:import url="../include/notifications.jsp"/>

<% User user = (User)request.getSession().getAttribute("user"); %>
<% String interlocutorPhoneNumber = request.getParameter("interlocutor") != null ? request.getParameter("interlocutor") : ""; %>

<div data-ng-app="sup.sms.message">
    <div id="message" data-ng-controller="messageCtrl">
        <h1>Message</h1>
        {{error}}
        <div class="contact">
            <label for="contact">
                <select class="form-control" id="contact">
                    <option>Laurent</option>
                    <option>Agathe</option>
                    <option>Timothée</option>
                </select>
            </label>
        </div>
        <div class="messages" data-scroll-bottom="messages">
            <!--iamReceiver-->
            <div class="message" data-ng-repeat="message in messages">
                <div data-ng-class="receiverOrTransmitter(message)">
                    {{message.message}}
                </div>
            </div>
        </div>
        <div class="input">
            <form class="form-inline" role="form" data-ng-submit="sendMessage()">
                <div class="row">
                    <div class="col-md-9">
                        <div class="form-group">
                            <input type="text" class="form-control" ng-model="messageToSend" id="message" placeholder="Enter your message...">
                        </div>
                    </div>
                    <div class="col-md-3">
                        <button type="submit" class="btn btn-default btn-lg">Send</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    window.interlocuror = '<%= interlocutorPhoneNumber %>';
    window.userId = <%= user.getId() %>;
</script>
<c:import url="/include/messageFooter.jsp"/>
 
