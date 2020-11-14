<%-- 
    Document   : EditTask
    Created on : Sep 23, 2020, 9:40:39 AM
    Author     : andrewroe
--%>


<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@page import="edu.model.InstructorService"%>
<%@page import="edu.model.TaskHandlerBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="today" value="${now}" pattern="yyyy-MM-dd" />
<fmt:parseDate var="currdate" value="${today}" pattern="yyyy-MM-dd"/>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Task entry JSP Page</title>
        <link rel="stylesheet" href="main.css">
    </head>
    <body>

        <div id="topbanner">
            <h1 id="course">User Task List</h1>
            <h2 id="assignment">Assignment 11</h2>
            <h3 id="student">CSCI 2466 - Thursday Session</h3>
        </div>

        <div id="mainbody">
            <h1>Edit Task Entry</h1>

            <p>For task ID = ${task.id} </p>
            <br>
            <p>please make the following desired changes: </p>
            <br>

            <form action="EditTask" method="get">
                <input type="hidden" name="action" value="editinfo">
                <input type="hidden" name="taskid" value=${task.id}>

                <label>Description</label>
                <c:choose> 
                    <c:when test="${empty msgDescription}">
                        <input class="blackonwhite" type="text" name="description" value="${task.description}" size="22" />
                    </c:when>
                    <c:otherwise>
                        <input class="colorit" type="text" name="description" value="${msgDescription}" size="22" />
                    </c:otherwise>
                </c:choose>

                <c:set var= "instr" value = "${task.instructor}"/>

                <label for="instructor">Instructor</label>
                <select class="backonwhite" id="instructor" name="instructor" size="4">
                    <c:choose>
                        <c:when test="${action eq 'enterinfo'}">
                            <c:forEach var="instr" items="${instructors}">
                                <option value="${instr.name}">"${instr.name}"</option>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <option value="${task.instructor}" selected>"${task.instructor}"</option>
                            <c:forEach var="instr" items="${unlist}">
                                <option value="${instr.name}">"${instr.name}"</option>
                            </c:forEach> 
                        </c:otherwise>        
                    </c:choose>
                </select>

                <label for="duedate">DueDate</label>
                <c:choose> 
                    <c:when test="${empty msgDuedate}">

                        <input class="blackonwhite" type="date" id="duedate" name="duedate" 
                               value="${task.duedate}" min="2010-01-01" max="2020-12-31"/>

                    </c:when>
                    <c:otherwise>

                        <input class="blackonwhite" type="date" id="duedate" name="duedate" 
                               value="${msgDuedate}" min="2010-01-01" max="2020-12-31"/>

                    </c:otherwise>
                </c:choose>

                <label>Submitted</label>        
                <c:choose> 
                    <c:when test="${empty msgSubmitted}">
                        <input class="blackonwhite" type="text" name="submitted" value="${task.submitted}" size="22" />
                    </c:when>
                    <c:otherwise>
                        <input class="colorit" type="text" name="submitted" value="${msgSubmitted}" size="22" />
                    </c:otherwise>
                </c:choose>

                <input type="submit" name="submit" value="Do Edit" size="10"/>
            </form>

            <br>
            <p id="colorit">${message}</p>
            <br>
        </div>

    </body>
</html>
