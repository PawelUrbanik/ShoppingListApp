<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Moje listy</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>

</head>
<body>
<%@ include file="../fragment/navbar_user.jspf"%>
<c:forEach var="list" items="${requestScope.sharedLists}">

    <div class="card mt-5 mb-3" style="max-width: 800px; margin: auto">
        <h5 class="card-header"><a href="${pageContext.request.contextPath}/showOneSharedList?listId=${list.id}&error=false">${list.name}</a></h5>
        <div class="card-body">
            <h5 class="card-title">Opis</h5>
            <p class="card-text">${list.description}</p>
            <div class="text-center">
                <a href="${pageContext.request.contextPath}/showOneSharedList?listId=${list.id}&error=false" class="btn btn-primary ">Otwórz listę</a>
            </div>
        </div>
    </div>
</c:forEach>


<!-- Update List Modal -->
<div class="modal fade" id="updateListModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                </button>
            </div>

            <div class="modal-body">
                <form method="POST" action="${pageContext.request.contextPath}/updateList">

                    <input type="hidden" name="list_id_m" id="list_id_m">
                    <label for="list_name_m" >Nazwa listy:</label><br>
                    <input type="text" id="list_name_m" name="list_name_m" required autofocus><br>
                    <label for="list_desc_m" >Opis:</label><br>
                    <input type="text" id="list_desc_m" name="list_desc_m" required autofocus>
                    <br>
                    <br>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Anuluj</button>
                    <button type="submit" class="btn btn-primary">Zapisz zmiany</button>
                </form>
            </div>
        </div>
    </div>
</div>


<!-- Delete List Modal -->
<div class="modal fade" id="deleteListModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Czy chcesz usunąć listę o nazwie </h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                </button>
            </div>
            <div class="modal-body">
                <div class="public_list_info">

                </div>
            </div>

            <div class="modal-footer">
                <form method="POST" action="${pageContext.request.contextPath}/deleteList">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Nie</button>
                    <input type="hidden" name="list_id" id="list_id">
                    <input type="hidden" name="list_type" id="list_type">
                    <button type="submit" class="btn btn-danger">Tak, usuń listę</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    $('#updateListModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var name = button.data('name') // Extract info from data-* attributes
        var list_id = button.data('list_id') // Extract info from data-* attributes
        var list_desc = button.data('list_desc')
        var modal = $(this)
        modal.find('.modal-title').text('Modyfikacja listy ' + name )
        document.getElementById("list_id_m").value = list_id;
        document.getElementById("list_name_m").value = name;
        document.getElementById("list_desc_m").value = list_desc;
    })

    $('#deleteListModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var name = button.data('name') // Extract info from data-* attributes
        var list_id = button.data('list_id') // Extract info from data-* attributes
        var list_type  = button.data('list_type')
        var modal = $(this)
        modal.find('.modal-title').text('Czy chcesz usunąć listę ' + name + ' i wszystkie znajdujące się na niej produkty?')
        document.getElementById("list_id").value = list_id;
        document.getElementById("list_type").value = list_type;
        console.log(list_type)
        if ('publ' === list_type)
        {
            modal.find('.modal-body').text('Probujesz usunąć listę publiczną. Jeśli lista została udostępniona innym użytkownikom' +
                ' to po usunięciu utracą do niej dostęp.\n' +
                'Uwaga: usunięcie listy jest operacją nieodwracalną!')
        }else  if ('priv' === list_type)
        {
            modal.find('.modal-body').text('Uwaga: usunięcie listy jest operacją nieodwracalną!')
        } else {
            modal.find('.modal-body').text('')
        }
    })
</script>
</body>
</html>