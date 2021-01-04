<%--
  Created by IntelliJ IDEA.
  User: szele
  Date: 2020. 12. 01.
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Kutyáink</title>
</head>
<body>
<jsp:include page="../navbar.jsp" />

<c:if test="${!empty kutyak}">
    <table frame="border" rules="all">
        <tr><th>Azonosító</th><th>Név</th><th>Születési Dátum</th><th>Fajta</th></tr>
        <c:forEach items="${kutyak}" var="kutya">
            <tr><td><a href="${pageContext.servletContext.contextPath}
            /kutya/${kutya.id}">${kutya.id}</a></td><td>${kutya.nev}</td><td>${kutya.szuletesIdeje}</td><td>${kutya.fajta}</td></tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${empty kutyak}">
    <c:out value="Nincs ilyen fajta kutya"/></c:if>

<form action="${pageContext.servletContext.contextPath}/addKutya">
    <input type="submit" value="Kutya hozzáadása"/>
</form>
</body>
</html>
