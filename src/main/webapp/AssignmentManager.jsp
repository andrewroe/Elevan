<%-- 
    Document   : AssignmentManager
    Created on : Sep 18, 2020, 11:12:20 AM
    Author     : andrewroe
--%>


<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@page import="edu.model.InstructorService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="today" value="${now}" pattern="yyyy-MM-dd" />
<fmt:parseDate var="currdate" value="${today}" pattern="yyyy-MM-dd"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Task Assignment Manager</title>
        <link rel="stylesheet" href="main.css">
    </head>
    <body>

        <div id="topbanner">
            <h1 id="course">User Task List</h1>
            <h2 id="assignment">Assignment 11</h2>
            <h3 id="student">CSCI 2466 - Thursday Session</h3>
        </div>

        <div id="mainbody">
            <div>
                <span class="textleft" >
                    <p>Welcome "${user.username}"</p>
                </span>
                <span class="textright" >
                    <p><a href="Logoff?action=logoff">Logout</a>
                        Note: Logout will clear all cookies for this user.</p>
                </span>
            </div>
                
                   

            <h1>Task List</h1>

            <form action="AddTask" method="get">
                <label>Description</label>
                <c:choose>
                    <c:when test="${empty message}">
                        <input class="blackonwhite" type="text" name="description" size="22" />

                        <%--
                            <c:import url="/includes/instructors.jsp" />
                        --%> 
                        <label for="instructor">Instructor</label>
                        <select class="backonwhite" id="instructor" name="instructor" size="4">

                            <c:forEach var="instr" items="${instructors}">
                                <option value="${instr.name}">"${instr.name}"</option>
                            </c:forEach>
                        </select>

                        <label for="duedate">DueDate</label>
                        <input class="blackonwhite" type="date" id="duedate" name="duedate" 
                               value="${today}" min="2010-01-01" max="2020-12-31"/>

                    </c:when>
                    <c:otherwise>
                        <input class="blackonwhite" type="text" name="description" value="${msgDescription}" size="22" />

                        <%--
                            <c:import url="/includes/instructors.jsp" />
                        --%>
                        <label for="instructor">Instructor</label>
                        <select class="backonwhite" id="instructor" name="instructor" size="4">

                            <c:forEach var="instr" items="${instructors}">
                                <option value="${instr.name}">"${instr.name}"</option> 
                            </c:forEach>
                        </select>

                        <label for="duedate">DueDate</label>
                        <input class="blackonwhite" type="date" id="duedate" name="duedate" 
                               value="${msgDuedate}" min="2010-01-01" max="2021-12-31"/>

                    </c:otherwise>
                </c:choose>
   
                <input type="hidden" name="user" value="${user}">
                <input type="hidden" name="id" value="${user.id}">
                <input type="submit" name="add" value="add" size="3"/>
            </form>
            <br>
            <p class="colorit">${message}</p>
            <br>

            <table>
                <tr>
                    <th class="id">ID</th>
                    <th class="description">Description</th>
                    <th class="instructor">Instructor</th>
                    <th class="duedate">DueDate</th>
                    <th class="submitted">Submitted</th>
                    <th class="actions">Actions</th>
                </tr>

                <c:forEach var="task" items="${tasks}">
                    <fmt:parseDate var="entryduedate" value="${task.duedate}" pattern="yyyy-MM-dd"/>
                    <c:choose> 
                        <c:when test="${task.submitted}"> 
                            <tr id="lightgreen">
                            </c:when> 
                            <c:when test="${currdate gt entryduedate}"> 
                            <tr id="redback">
                            </c:when> 
                            <c:otherwise> 
                            <tr id="white">
                            </c:otherwise>
                        </c:choose>
                        <td class="id"> <c:out value="${task.id}" /></td>
                        <td class="description"> <c:out value="${task.description}" /></td>
                        <td class="instructor"> <c:out value="${task.instructor}" /></td>                   
                        <td class="duedate"> <c:out value="${task.duedate}" /></td>

                        <td class="submitted"><c:out value="${task.submitted}"/></td> 

                        <td class="actions">
                            <a href="RemoveTask?action=delete&user=${user}&taskid=${task.id}">X</a>
                            <a href="SubmitTask?action=done&user=${user}&taskid=${task.id}">S</a>
                            <a href="EditTask?action=enterinfo&user=${user}&taskid=${task.id}">E</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>