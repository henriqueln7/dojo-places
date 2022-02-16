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
        <th>Editar</th>
        <th>Excluir</th>
    </thead>
    <tbody>
        <c:forEach items="${places}" var="place">
            <tr class="table-line">
                <td>${place.id}</td>
                <td>${place.name}</td>
                <td>${place.code}</td>
                <td>${place.formattedCreatedAt}</td>
                <td>${place.daysSinceLastUpdate}</td>
                <td> <a href="/places/${place.id}/edit">Editar </a> </td>
                <td><button class="delete-place-button" data-place-id="${place.id}" type="button">Excluir </button></td>
            </tr>

        </c:forEach>
    </tbody>
</table>

<script>
    let deletePlaceButton = document.querySelectorAll(".delete-place-button");
    deletePlaceButton.forEach(d => d.addEventListener("click", (e) => {
        let placeId = d.dataset.placeId;
        if (confirm("Deseja realmente deletar o lugar?")) {
            fetch('/places/'+ placeId, {method: 'DELETE'})
                .then(() => {
                    const tableLine = d.closest('.table-line');
                    tableLine.style.display = 'none';
                })
                .catch(err => console.log("PANE NO SISTEMA ALGUÉM ME DESCONFIGUROU " + err))
        }

    }))
</script>

</body>
</html>
