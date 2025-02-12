<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Cadastro de Local</title>
    <meta charset="UTF-8" />
</head>
<body>
<h1>Atualização de Local</h1>

<form:form method="POST" modelAttribute="editPlaceForm">
    <label for="name">Nome</label>
    <form:input type="text" path="name" id="name" placeholder="Nome" maxlength="100" value="${placeEditView.name}" />
    <form:errors path="name" />
    <label for="code">Código</label>
    <form:input type="text" path="code" id="code" placeholder="Código" value="${placeEditView.code}" />
    <form:errors path="code" />
    <label for="district">Bairro</label>
    <form:input type="text" path="district" id="district" placeholder="Bairro" maxlength="100" value="${placeEditView.district}"/>
    <form:errors path="district" />
    <label for="city">Cidade</label>
    <form:input type="text" path="city" id="city" placeholder="Cidade" maxlength="100" value="${placeEditView.city}"/>
    <form:errors path="city" />
    <input type="submit" value="Cadastrar local" />
</form:form>
</body>
</html>
