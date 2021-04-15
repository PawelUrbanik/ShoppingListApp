<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<%@ include file="../fragment/navbar_user.jspf" %>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col">Nazwa</th>
        <th scope="col">Kupione</th>
        <th scope="col">Dodane przez</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${requestScope.products}">
        <tr>
            <th scope="row">${product.id}</th>
            <td>${product.name}</td>
            <td>${product.bought}</td>
            <td>${product.addedBy}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<form action="/addProduct" method="get">
    <input type="hidden" name="listId" value="${param.get("listId")}">
    <input type="submit" value="Dodaj produkt">
</form>
</body>
</html>
