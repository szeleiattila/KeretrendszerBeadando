<%--
  Created by IntelliJ IDEA.
  User: szele
  Date: 2020. 12. 02.
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>${kutya.id}</title>
</head>
<body>
<jsp:include page="../navbar.jsp" />
<table>
    <tr><td>Név:</td><td>${kutya.nev}</td></tr>
    <tr><td>Születési Dátum:</td><td>${kutya.szuletesIdeje}</td></tr>
    <tr><td>Fajta:</td><td>${kutya.fajta}</td></tr>
    <tr><td>Kilogramm</td><td>${kutya.kilogramm}</td></tr>

</table>
<form action="${pageContext.servletContext.contextPath}/">
    <input type="submit" value="Home">
</form>
</body>
</html>
