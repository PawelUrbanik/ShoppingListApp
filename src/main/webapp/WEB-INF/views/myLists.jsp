<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Moje listy</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%@ include file="../fragment/navbar_user.jspf"%>
 <c:forEach var="list" items="${requestScope.lists}">

     <div class="card mt-5 mb-3" style="max-width: 800px; margin: auto">
         <h5 class="card-header"><a href="/showList?listId=${list.id}">${list.name}</a></h5>
         <div class="card-body">
             <h5 class="card-title">Opis</h5>
             <p class="card-text">${list.description}</p>
             <div class="text-center">
                 <a href="/showList?listId=${list.id}" class="btn btn-primary ">Otwórz listę</a>
                 <a href="/modify?listId=${list.id}" class="btn btn-warning">Modyfikuj listę</a>
                 <a href="/delete?listId=${list.id}" class="btn btn-danger">Usuń listę</a>
                 <a href="/share?listId=${list.id}" class="btn btn-light">Udostępnij listę</a>
             </div>
         </div>
     </div>

<%--        <div class="container">--%>
<%--            <div class="row">--%>
<%--                <div class="col-xs-12 col-sm-8 main well">--%>
<%--                    <div class="blog-post">--%>
<%--                        <h2 class="blog-post-title"><a href="/showList?listId=${list.id}"> ${list.name}</a> </h2>--%>
<%--                        <p>Opis: ${list.description}</p>--%>
<%--                        <a href="/showList?listId=${list.id}" class="btn btn-default">Otwórz i przeglądaj</a>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
    </c:forEach>
<%--</c:if>--%>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
