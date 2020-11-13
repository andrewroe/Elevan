<%-- 
    Document   : instructors
    Created on : Nov 3, 2020, 12:33:04 PM
    Author     : andrewroe
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<label for="instructor">Instructor</label>
<select class="backonwhite" id="instructor" name="instructor" size="4">
    <c:choose>
        <c:when test="${task.instructor eq 'Cheng Thao'}">
            <option value="Cheng Thao" selected>Cheng Thao</option>
        </c:when>
        <c:otherwise>
            <option value="Cheng Thao">Cheng Thao</option>
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${task.instructor eq 'James Woodcock'}">
            <option value="James Woodcock" selected>James Woodcock</option>
        </c:when>
        <c:otherwise>
            <option value="James Woodcock">James Woodcock</option>
        </c:otherwise>
    </c:choose>        

    <c:choose>
        <c:when test="${task.instructor eq 'Khiet Nguyan'}">
            <option value="Khiet Nguyen" selected>Khiet Nguyan</option>
        </c:when>
        <c:otherwise>
            <option value="Khiet Nguyen">Khiet Nguyen</option>
        </c:otherwise>
    </c:choose>        

    <c:choose>
        <c:when test="${task.instructor eq 'Susan Metoxen'}">
            <option value="Susan Metoxen" selected>Susan Metoxen</option>
        </c:when>
        <c:otherwise>
            <option value="Susan Metoxen">Susan Metoxen</option>
        </c:otherwise>
    </c:choose>        

    <c:choose>
        <c:when test="${task.instructor eq 'Mary Anderson'}">
            <option value="Mary Anderson" selected>Mary Anderson</option>
        </c:when>
        <c:otherwise>
            <option value="Mary Anderson">Mary Anderson</option>
        </c:otherwise>
    </c:choose>        

    <c:choose>
        <c:when test="${task.instructor eq 'Dr. John Smith'}">
            <option value="Dr. John Smith" selected>Dr. John Smith</option>
        </c:when>
        <c:otherwise>
            <option value="Dr. John Smith">Dr. John Smith</option>
        </c:otherwise>
    </c:choose>        

    <c:choose>
        <c:when test="${task.instructor eq 'First grade teacher'}">
            <option value="First grade teacher" selected>First grade teacher</option>
        </c:when>
        <c:otherwise>
            <option value="First grade teacher">First grade teacher</option>
        </c:otherwise>
    </c:choose>        

    <c:choose>
        <c:when test="${task.instructor eq 'Spouse'}">
            <option value="Spouse" selected>Spouse</option>
        </c:when>
        <c:otherwise>
            <option value="Spouse">Spouse</option>
        </c:otherwise>
    </c:choose>        

    <c:choose>
        <c:when test="${task.instructor eq 'Creditors'}">
            <option value="Creditors" selected>Creditors</option>
        </c:when>
        <c:otherwise>
            <option value="Creditors">Creditors</option>
        </c:otherwise>
    </c:choose>        

    <c:choose>
        <c:when test="${task.instructor eq 'Family'}">
            <option value="Family" selected>Family</option>
        </c:when>
        <c:otherwise>
            <option value="Family">Family</option>
        </c:otherwise>
    </c:choose>        

   <c:choose>
        <c:when test="${task.instructor eq 'Karen E Johnson, phd'}">
            <option value="Karen E Johnson, phd" selected>Karen E Johnson, phd</option>
        </c:when>
        <c:otherwise>
            <option value="Karen E Johnson, phd">Karen E Johnson, phd</option>
        </c:otherwise>
    </c:choose>        

</select>