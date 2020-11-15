<%-- 
    Document   : register
    Created on : Oct 9, 2020, 6:09:42 PM
    Author     : andrewroe
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="edu.model.User"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="main.css"  type="text/css">
        <style>
            h1{ text-align:center;}
        </style>
    </head>
    <body>
              
        <div id="topbanner">
            <h1 id="course">User Task List</h1>
            <h2 id="assignment">Assignment 10</h2>
            <h3 id="student">CSCI 2466 - Thursday Session</h3>
        </div>

        <h1>Please Register</h1>
        <form action="RegisterServlet" method="post">
            <input type="hidden" name="action" value="registerUser"><br>
            <input type="text" name="username" value="${user.username}" required>
            <span class="red-text">${messages[0]}</span><br>
            <input type="email" name="email" value="${user.email}" required>
            <span class="red-text">${messages[1]}</span><br>
            <input type="password" name="password" value="${user.password}" required>
            <span class="red-text">${messages[2]}</span><br>
            <input type="password" name="confirmpwd" value="Confirm Password" required>
            <span class="red-text">${messages[3]}</span><br>
            
            <input type="submit" value="Register">
        </form>
    </body>
</html>
