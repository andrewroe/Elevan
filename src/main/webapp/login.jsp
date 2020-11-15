<%-- 
    Document   : login
    Created on : Oct 9, 2020, 10:05:55 PM
    Author     : andrewroe
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="edu.model.User"%>
<%@page import="edu.data.DBuser"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login JSP Page</title>
        <link rel="stylesheet" href="main.css" type="text/css">
    </head>
    <body>

        <div id="topbanner">
            <h1 id="course">User Task List</h1>
            <h2 id="assignment">Assignment 11</h2>
            <h3 id="student">CSCI 2466 - Thursday Session</h3>
        </div>

        <h1>Please Login</h1>

        <%--
        We need to determine if variable user is available, not null or empty.
        If not, then check if session cookie has an email match with a user,
        which would be due to a prior "rememberme" action. If so we can use that 
        user.
        Otherwise, we can create a temporary "prompt" user for this JSP page
        to use.
        
        <link rel="stylesheet" href="main.css" type="text/css">
        <c:set var = "user" scope = "request" value = "${theuser}"/>
        --%>

        <%
            log("login.jsp: request = " + request);
            User theuser = (User) request.getAttribute("user");
            log("login.jsp: initial user = " + theuser);
            if (theuser == null) {
                User userobj = new User();
                theuser = userobj.fetchKnownUser(request);
            }
            log("login.jsp: rememberme check, user = " + theuser);
            if (theuser == null) {
                theuser = new User(0, "", "Email", "Password");
            }
            log("login.jsp: final value of user = " + theuser);
            request.setAttribute("user", theuser);
        %>


        <form action="LoginServlet" method="post">
            <input type="hidden" name="action" value="loginUser">
            <input type="email" name="email" value="${user.email}" required>
            <span class="red-text">${messages[0]}</span><br>
            <input type="text" name="password" value="${user.password}" required>
            <span class="red-text">${messages[1]}</span><br>
            <input type="radio" name="rememberMe">Remember Me<br>
            <input type="submit" value="Login">
        </form>

    <p class="centerit">No Account? <a href="RegisterUser?action=register"> Register</a> 
    </p>
</body>
</html>
