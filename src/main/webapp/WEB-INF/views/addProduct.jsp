<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodaj produkt do listy</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<%@ include file="../fragment/navbar_user.jspf"%>
<div class="container">
    <form class="form-signin" action="${pageContext.request.contextPath}/addProduct" method="post">
        <h2 class="form-signin-heading">Dodaj Produkt</h2>
        <label for="name" >Nazwa :</label>
        <input id="name" name="inputName" type="text" class="form-control" placeholder="Nazwa" required autofocus><br>
        <label for="count" >Ilość:</label>
        <input name="count" id="count" type="number" class="form-control"  value="1" placeholder="Ilość" min="1" max="50" required autofocus>
        <input name="listId" type="hidden" value="${requestScope.listId}">
        <input type="hidden" name="sharedReq" value="true">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Dodaj</button>
    </form>
</div>
</body>
</html>
