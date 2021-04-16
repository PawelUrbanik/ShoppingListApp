<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Zaloguj się - Shopping List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%@ include file="../fragment/navbar.jspf" %>
<div class="container" style="max-width: 480px">

    <div class="text-center">
        <h1 class="h2 mb-3 mt-3">Zaloguj się</h1>
        <form action="j_security_check" method="post" class="user-form">
            <label for="usernameInput" class="sr-only">Nazwa użytkownika:</label>
            <input type="text" id="usernameInput" class="form-control mb-3" name="j_username" placeholder="Nazwa użytkownika" required autofocus>
            <label for="passwordInput" class="sr-only">Hasło:</label>
            <input type="password" id="passwordInput" class="form-control" name="j_password" placeholder="Hasło"  required autofocus>
            <div class="mt-3 mb-3">
                <button class="btn btn-lg btn-primary btn-block">Zaloguj się</button>
                <p>Nie masz konta? <a href="/register">Zarejestruj się</a></p>
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