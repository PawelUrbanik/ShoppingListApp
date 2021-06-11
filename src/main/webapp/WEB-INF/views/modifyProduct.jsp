<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodaj nową listę</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<%@ include file="../fragment/navbar_user.jspf"%>
<form class="form-signin" action="${pageContext.request.contextPath}/modifyProduct" method="PUT">
    <h2 class="form-signin-heading">Modyfikuj Produkt</h2>
    <label for="name" >Nazwa :</label>
    <input id="name" name="inputName" type="text" class="form-control" placeholder="Nazwa" required autofocus value="${product}"><br>
<%--    <input name="listId" type="hidden" value="${requestScope.listId}">--%>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Zatwierdź zmiany</button>
</form>
</body>
</html>