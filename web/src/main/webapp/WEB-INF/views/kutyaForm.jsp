<%--
  Created by IntelliJ IDEA.
  User: szele
  Date: 2020. 12. 02.
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Kutya hozzáadás</title>
</head>
<body>
<jsp:include page="../navbar.jsp" />
<form:form method="post" action="addKutya" modelAttribute="kutya">
    <form:label path="nev">Név</form:label>
    <form:input path="nev"/>
    <form:label path="szuletesIdeje">Születési Dátum</form:label>
    <form:input type="date" path="szuletesIdeje"/>
    <form:label path="kilogramm">Kilogramm</form:label>
    <form:input path="kilogramm"/>
    <form:label path="fajta">Fajta</form:label>
    <form:select path="fajta">
        <form:options items="${fajtak}"/>
    </form:select>
    <input type="submit" value="Küldés"/>
</form:form>
</body>
</html>
