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
            <label for="name">Código</label>
            <input type="text" name="name" id="code" placeholder="Código" maxlength="100">
            <label for="name">Bairro</label>
            <input type="text" name="district" id="name" placeholder="Bairro" maxlength="100">
            <label for="name">Cidade</label>
            <input type="text" name="city" id="name" placeholder="Cidade" maxlength="100">
            <input type="submit" value="Cadastrar local" />
        </form>
    </body>
</html>
