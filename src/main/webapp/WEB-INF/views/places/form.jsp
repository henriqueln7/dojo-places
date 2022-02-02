<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Cadastro de Local</title>
        <meta charset="UTF-8" />
    </head>
    <body>
        <h1>Cadastro de Local</h1>
        <form method="POST">
            <label for="name">Nome</label>
            <input type="text" name="name" id="name" placeholder="Nome" maxlength="100">
            <label for="code">Código</label>
            <input type="text" name="code" id="code" placeholder="Código">
            <label for="district">Bairro</label>
            <input type="text" name="district" id="district" placeholder="Bairro" maxlength="100">
            <label for="city">Cidade</label>
            <input type="text" name="city" id="city" placeholder="Cidade" maxlength="100">
            <input type="submit" value="Cadastrar local" />
        </form>
    </body>
</html>
