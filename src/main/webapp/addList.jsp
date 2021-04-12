<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodaj nową listę</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <form class="form-signin" action="/addList" method="post">
        <h2 class="form-signin-heading">Dodaj nową listę</h2>
        <label for="name" >Nazwa listy:</label>
        <input id="name" name="inputName" type="text" class="form-control" placeholder="Nazwa" required autofocus><br>
        <label for="type">Rodzaj: </label><br>
        <input id="type" name="inputType" type="radio" value="cat" checked> Publiczna <br>
        <input name="inputType" type="radio" value="dog"> Prywatna <br>
        Opis:<br>
        <textarea rows="4" cols="50" name="inputDescription" class="form-control" placeholder="Opis" required></textarea><br>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Dodaj</button>
    </form>
</div>
</body>
</html>
