<%@page import="sup.sms.entity.Admin"%>
<%@page import="sup.sms.entity.User"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% User userConnected = (User)request.getSession().getAttribute("user"); %>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/app/conversation">SupSMS - app</a>
      </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
          <li><a href="/app/conversation">Conversations</a></li>
          <li><a href="/app/message">New Message</a></li>
          <li><a href="/app/contacts">Contacts</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"><%= userConnected.getFirstname() %> <%= userConnected.getLastname() %> <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
              <li><a href="/app/profile">Profile</a></li>
              <%if( userConnected instanceof Admin ){%>
                  <li><a href="/admin/users">Manage users</a></li>
              <%}%>
              <li class="divider"></li>
              <li><a href="/logout">Logout</a></li>
            </ul>
        </li>
      </ul>
      </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
  </nav>
<div class="content">