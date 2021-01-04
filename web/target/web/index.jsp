<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script
        src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
        crossorigin="anonymous"></script>
<html>
<body>
<jsp:include page="WEB-INF/navbar.jsp" />

    <c:if test="${empty kutyak}">
        <c:out value="Nincs kutya"/></c:if>

<form id="form1" action="">
    <label for="id">Add meg a kutya azonositoját</label>
    <input id="id" />
    <input type="submit" value="Keres" id="btn_ok" />
</form>

<form id="form2" action="">
    <label for="id2">Add meg a kutya fajtáját</label>
    <input id="id2" />
    <input type="submit" value="Keres" id="btn_ok2" />
</form>

<script>
    $(document).ready(function () {
       var inputField = $('#id');
        var inputField2 = $('#id2');


        inputField.change(function () {
           var id = inputField.val();
           console.log(id);
           $('#form1').attr('action', 'kutya/' + id);
       });

        inputField2.change(function () {
            var fajta = inputField2.val();
            console.log(fajta);
            $('#form2').attr('action', 'kutyak/' + fajta);
        });
    });
</script>
</body>
</html>
