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
        <th scope="col">Lp.</th>
        <th scope="col">Nazwa</th>
        <th scope="col">Dodane przez</th>
        <th scope="col">Kupione</th>
        <th scope="col" width="50px">Oznacz jako</th>
        <th scope="col" width="50px">Modyfikacja</th>
        <th scope="col">Usuwanie</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${requestScope.products}" varStatus="counterStatus">
        <tr>
            <th scope="row">${counterStatus.count}</th>
            <td>${product.name}</td>
            <td>${product.addedBy}</td>
            <td><c:if test="${product.bought == 'true'}"><input type="button" disabled class="btn btn-success" value="TAK"></c:if>
            <c:if test="${product.bought == 'false'}"><input type="button" disabled class="btn btn-danger" value="NIE"></c:if></td>
            <td width="50px">
                <c:if test="${product.bought == 'false'}"><input type="button" class="btn btn-outline-success btn-block" value="Zakupiony"></c:if>
                <c:if test="${product.bought == 'true'}"><input type="button" class="btn btn-outline-danger btn-block" value="Niezakupiony"></c:if>
            </td>
            <td width="50px">
                <input type="button" class="btn btn-outline-secondary" value="Modyfikuj">
            </td>
            <td>
                <input type="button" class="btn btn-danger" value="Usuń">
            </td>
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
