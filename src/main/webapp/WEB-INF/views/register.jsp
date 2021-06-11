<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Zarejestruj się - Shopping List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%@ include file="../fragment/navbar.jspf"%>
<div class="container" style="max-width: 480px; margin: auto">
    <div class="text-center">
        <h1 class="h2 mb-3 mt-3">Zarejestruj się</h1>
        <form action="${pageContext.request.contextPath}/register" method="post" class="user-form">
            <label for="usernameInput" class="sr-only">Nazwa użytkownika:</label>
            <input name="username" class="form-control mb-3" id="usernameInput" placeholder="Nazwa użytkownika" required autofocus>
            <label for="emailInput" class="sr-only">Email:</label>
            <input name="email" class="form-control mb-3" id="emailInput" placeholder="email" type="email" required autofocus>
            <label for="passwordInput" class="sr-only">Hasło:</label>
            <input name="password" class="form-control mb-3" id="passwordInput" placeholder="Hasło" type="password" required autofocus>
            <div class="mt-3 mb-3">
                <button type="submit" class="btn btn-lg btn-primary btn-block">Zarejestruj się</button>
            </div>
        </form>
    </div>



<%--    <%@ include file="" %>--%>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</div>
</body>
</html>