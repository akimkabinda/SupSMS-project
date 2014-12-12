<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<% String error = (String)request.getAttribute("error"); %>
<% String warning = (String)request.getAttribute("warning"); %>
<% String info = (String)request.getAttribute("info"); %>
<!-- notifications -->
<%if (error != null) {%>
    <div class="alert alert-danger" role="alert">
        <%= error %>
    </div>
<%}%>
<%if (warning != null) {%>
    <div class="alert alert-warning" role="alert">
        <%= warning %>
    </div>
<%}%>
<%if (info != null) {%>
    <div class="alert alert-info" role="alert">
        <%= info %>
    </div>
<%}%>