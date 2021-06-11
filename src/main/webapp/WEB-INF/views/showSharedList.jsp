<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
            integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
            integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
            crossorigin="anonymous"></script>

</head>
<body>


<%@ include file="../fragment/navbar_user.jspf" %>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">Lp.</th>
        <th scope="col">Nazwa</th>
        <th scope="col">Ilość</th>
        <th scope="col">Kupione</th>
        <th scope="col">Dodane przez</th>
<c:if test="${requestScope.privileges.changingState == 'true'}">
    <th scope="col" width="50px">Oznacz jako</th> </c:if>
<c:if test="${requestScope.privileges.updateProducts == 'true'}">
    <th scope="col" width="50px">Modyfikacja</th></c:if>
<c:if test="${requestScope.privileges.deleteProducts == 'true'}">
    <th scope="col">Usuwanie</th> </c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${requestScope.products}" varStatus="counterStatus">
        <tr>
            <th scope="row">${counterStatus.count}</th>
            <td>${product.name}</td>
            <td>${product.count}</td>
            <td><c:if test="${product.bought == 'true'}"><input type="button" disabled class="btn btn-success"
                                                                value="TAK"
            </c:if>
                <c:if test="${product.bought == 'false'}"><input type="button" disabled class="btn btn-danger"
                                                                 value="NIE"></c:if></td>
            <td>${product.addedBy}</td>
            <c:if test="${requestScope.privileges.changingState == 'true'}">
            <td width="50px">
                <form action="${pageContext.request.contextPath}/changeProductStatus" method="post">
                    <input type="hidden" name="product_id" value="${product.id}">
                    <input type="hidden" name="bought" value="${product.bought}">
                    <input type="hidden" name="list_id" value="${requestScope.listId}">
                    <input type="hidden" name="sharedReq" value="true">
                    <c:if test="${product.bought == 'false'}"><input type="submit"
                                                                     class="btn btn-outline-success btn-block"
                                                                     value="Zakupiony"></c:if>
                    <c:if test="${product.bought == 'true'}"><input type="submit"
                                                                    class="btn btn-outline-danger btn-block"
                                                                    value="Niezakupiony"></c:if>
                </form>
            </td></c:if>
            <c:if test="${requestScope.privileges.updateProducts == 'true'}">
            <td width="50px">
                <button class="btn btn-outline-secondary" data-toggle="modal" data-target="#updateProductModal"
                        data-name="${product.name}" data-product_id="${product.id}" data-count="${product.count}">
                    Modyfikuj
                </button>
            </td></c:if>
            <c:if test="${requestScope.privileges.deleteProducts == 'true'}">
            <td>
                <button class="btn btn-danger" data-toggle="modal" data-target="#deleteProductModal"
                        data-name="${product.name}" data-product_id="${product.id}">Usuń
                </button>
            </td></c:if>

        </tr>
    </c:forEach>
    </tbody>
</table>

<form action="${pageContext.request.contextPath}/addProduct" method="get">
    <input type="hidden" name="listId" value="${param.get("listId")}">
    <input type="submit" value="Dodaj produkt">
</form>

<!-- Delete Product Modal -->
<div class="modal fade" id="deleteProductModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Czy chcesz usunąć produkt o nazwie </h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                </button>
            </div>

            <div class="modal-footer">
                <form method="POST" action="${pageContext.request.contextPath}/deleteProduct">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Nie</button>
                    <input type="hidden" name="list_id" id="list_id" value="${requestScope.listId}">
                    <input type="hidden" name="sharedReq" value="true">
                    <input class="hidden-id" type="hidden" name="product_id" id="product_id">
                    <button type="submit" class="btn btn-danger">Tak, usuń produkt</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Update Product Modal -->
<div class="modal fade" id="updateProductModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                </button>
            </div>

            <div class="modal-body">
                <form method="POST" action="${pageContext.request.contextPath}/updateProduct">

                    <input type="hidden" name="list_id_m" id="list_id_m" value="${requestScope.listId}">
                    <input class="hidden-id" type="hidden" name="product_id_m" id="product_id_m">
                    <input type="hidden" name="sharedReq" value="true">
                    <label for="product_name_m">Nazwa produktu:</label><br>
                    <input type="text" id="product_name_m" name="product_name_m"><br>
                    <label for="product_count_m">Ilość:</label><br>
                    <input type="number" id="product_count_m" name="product_count_m" min="1" max="50">
                    <br>
                    <br>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Anuluj</button>
                    <button type="submit" class="btn btn-primary">Zapisz zmiany</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    $('#updateProductModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var name = button.data('name') // Extract info from data-* attributes
        var product_id = button.data('product_id') // Extract info from data-* attributes
        var count = button.data('count')
        console.log(name)
        console.log(product_id)
        var modal = $(this)
        modal.find('.modal-title').text('Modyfikacja produktu ' + name)
        document.getElementById("product_id_m").value = product_id;
        document.getElementById("product_name_m").value = name;
        document.getElementById("product_count_m").value = count;
    })
    $('#deleteProductModal').on('show.bs.modal', function (event) {
        var docmodel = document.getElementById('deleteProductModal')
        var button = $(event.relatedTarget) // Button that triggered the modal
        var name = button.data('name') // Extract info from data-* attributes
        var product_id = button.data('product_id') // Extract info from data-* attributes
        console.log(name)
        console.log(product_id)
        var modal = $(this)
        modal.find('.modal-title').text('Czy chcesz usunąć produkt ' + name + '?')
// modal.find('.modal-body').text(product_id)
        document.getElementById("product_id").value = product_id;
    })
</script>


</body>
</html>
