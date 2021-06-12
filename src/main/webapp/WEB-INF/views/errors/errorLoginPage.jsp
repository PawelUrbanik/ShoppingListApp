<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Błąd logowania</title>
</head>
<body>
<%@ include file="../../fragment/navbar.jspf" %>
<div class="text-center mt-2">
    <div class="alert alert-danger d-inline-block" role="alert">
        <p>Podano nieprawidłową nazwę użytkownika lub hasło. Spróbuj ponownie!</p>
    </div>
</div>
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
                <p>Nie masz konta? <a href="${pageContext.request.contextPath}/register">Zarejestruj się</a></p>
            </div>

        </form>
    </div>


</body>
</html>