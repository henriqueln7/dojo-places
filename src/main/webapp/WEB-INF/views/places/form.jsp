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

        <h1>Cadastro de Local</h1>
        <form:form method="POST" action="/places" modelAttribute="createPlaceForm">

            <h3>Preencher por CEP: </h3>
            <label>
                Cep:
                <input type="text" id="cep" />
            </label>

            <br />
            <label for="name">Nome</label>
            <form:input type="text" path="name" id="name" placeholder="Nome" maxlength="100" />
            <form:errors path="name" />
            <label for="code">Código</label>
            <form:input type="text" path="code" id="code" placeholder="Código" />
            <form:errors path="code" />
            <label for="district">Bairro</label>
            <form:input type="text" path="district" id="district" placeholder="Bairro" maxlength="100"/>
            <form:errors path="district" />
            <label for="city">Cidade</label>
            <form:input type="text" path="city" id="city" placeholder="Cidade" maxlength="100"/>
            <form:errors path="city" />
            <input type="submit" value="Cadastrar local" />
        </form:form>
    </body>

<script>
    const cepInput = document.getElementById("cep");
    cepInput.addEventListener("input", (e) => {
        e.preventDefault();
        const cep = cepInput.value;
        if (cep.length == 8) {
            fetch('https://viacep.com.br/ws/'+ cep +'/json/')
                .then(cep => cep.json())
                .then(cep => {
                    document.getElementById('city').value = cep.localidade;
                    document.getElementById('district').value = cep.logradouro;
                }).catch(err => console.log(err));
        }

    })
</script>
</html>
