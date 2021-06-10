<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Udostępnianie listy</title>
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
        <th scope="col">Udostępniona dla</th>
        <th scope="col">Modyfikacja listy</th>
        <th scope="col">Dodawanie produktów</th>
        <th scope="col">Modyfikacja produktów</th>
        <th scope="col">Zmiana stanu produktu</th>
        <th scope="col">Usuwanie produktów</th>
        <th scope="col">Zmień prawa</th>
        <th scope="col">Cofniij udsotępnianie</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach var="shared" items="${requestScope.sharedList}" varStatus="counterStatus">
        <tr>
            <th scope="row">${counterStatus.count}</th>
            <td>${shared.username}</td>
            <td><c:if test="${shared.updateList == 'true'}"><input type="button" disabled class="btn btn-success"
                                                                value="TAK"></c:if>
                <c:if test="${shared.updateList == 'false'}"><input type="button" disabled class="btn btn-danger"
                                                                 value="NIE"></c:if></td>
            <td><c:if test="${shared.addingProducts == 'true'}"><input type="button" disabled class="btn btn-success"
                                                                       value="TAK"></c:if>
                <c:if test="${shared.addingProducts == 'false'}"><input type="button" disabled class="btn btn-danger"
                                                                        value="NIE"></c:if></td>
            <td><c:if test="${shared.updateProducts == 'true'}"><input type="button" disabled class="btn btn-success"
                                                                           value="TAK"></c:if>
                <c:if test="${shared.updateProducts == 'false'}"><input type="button" disabled class="btn btn-danger"
                                                                            value="NIE"></c:if></td>
            <td><c:if test="${shared.changingState == 'true'}"><input type="button" disabled class="btn btn-success"
                                                                           value="TAK"></c:if>
                <c:if test="${shared.changingState == 'false'}"><input type="button" disabled class="btn btn-danger"
                                                                            value="NIE"></c:if></td>
            <td><c:if test="${shared.deleteProducts == 'true'}"><input type="button" disabled class="btn btn-success"
                                                                           value="TAK"></c:if>
                <c:if test="${shared.deleteProducts == 'false'}"><input type="button" disabled class="btn btn-danger"
                                                                            value="NIE"></c:if></td>
            <td width="50px">
                <button class="btn btn-outline-secondary" data-toggle="modal" data-target="#updateSharedModal"
                        data-id="${shared.id}" data-update_list="${shared.updateList}" data-add_product="${shared.addingProducts}"
                data-update_product="${shared.updateProducts}" data-change_state="${shared.changingState}" data-delete_product="${shared.deleteProducts}"
                    data-username="${shared.username}">
                    Zmień
                </button>
            </td>
            <td>
                <button class="btn btn-danger" data-toggle="modal" data-target="#deleteProductModal"
                        data-name="${product.name}" data-product_id="${product.id}">Usuń
                </button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<div class="container">
    <form class="form-signin" action="/shareList" method="post">
        <h2 class="form-signin-heading">Udostępnij listę</h2>
        <label for="name">Nazwa użytkownika:</label>
        <input id="name" name="inputName" type="text" class="form-control" placeholder="Nazwa użytkownika" required
               autofocus><br>
        <input type="checkbox" id="updateList" name="updateList" checked>
        <label for="updateList">Modyfikacja listy</label><br>
        <input type="checkbox" id="addProduct" name="addProduct" checked>
        <label for="addProduct">Dodawanie produktów</label><br>
        <input type="checkbox" id="updateProduct" name="updateProduct" checked>
        <label for="updateProduct">Modyfikacja produktów</label><br>
        <input type="checkbox" id="changeState" name="changeState" checked>
        <label for="changeState">Zmiana stanu</label><br>
        <input type="checkbox" id="deleteProduct" name="deleteProduct" checked>
        <label for="deleteProduct">Usuwanie produktów</label><br>
        <input name="listId" type="hidden" value="${requestScope.listId}">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Udostępnij listę</button>
    </form>
    <form action="/myLists" method="get">
        <input type="submit" value="Powrót">
    </form>
</div>

<!-- Update Product Modal -->
<div class="modal fade" id="updateSharedModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                </button>
            </div>

            <div class="modal-body">
                <form method="POST" action="/updateSharedList">

                    <input type="hidden" name="list_id_m" id="list_id_m" value="${requestScope.listId}">
                    <input type="hidden" name="shared_list_id_m" id="shared_list_id_m">
                    <input type="checkbox" id="updateList_m" name="updateList_m">
                    <label for="updateList_m">Modyfikacja listy</label><br>
                    <input type="checkbox" id="addProduct_m" name="addProduct_m">
                    <label for="addProduct_m">Dodawanie produktów</label><br>
                    <input type="checkbox" id="updateProduct_m" name="updateProduct_m">
                    <label for="updateProduct_m">Modyfikacja produktów</label><br>
                    <input type="checkbox" id="changeState_m" name="changeState_m">
                    <label for="changeState_m">Zmiana stanu</label><br>
                    <input type="checkbox" id="deleteProduct_m" name="deleteProduct_m">
                    <label for="deleteProduct_m">Usuwanie produktów</label><br>
                    <input name="listId" type="hidden" value="${requestScope.listId}">
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
    $('#updateSharedModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var id = button.data('id') // Extract info from data-* attributes
        var username = button.data('username')

        var modal = $(this)

        modal.find('.modal-title').text('Zmiana uprawnień dla użytkownika '  +username)
        document.getElementById("shared_list_id_m").value = id;
    })
</script>
</body>
</html>