<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Visualizar locais</title>
    <meta charset="UTF-8" />
</head>
<body>
<h1>Listagem de locais</h1>

<table>
    <thead>
        <th>ID</th>
        <th>Nome</th>
        <th>Código</th>
        <th>Data de criação</th>
        <th>Dias desde a última atualização</th>
    </thead>
    <tbody>
        <c:forEach items="${places}" var="place">
            <tr>
                <td>${place.id}</td>
                <td>${place.name}</td>
                <td>${place.code}</td>
                <td>${place.formattedCreatedAt}</td>
                <td>-</td>
            </tr>

        </c:forEach>
    </tbody>
</table>

</body>
</html>
