<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--
    Esta página sirve para agregar un cliente nuevo o editar uno existente.
    El servlet nos pasa la variable "cliente" y "isEdit" que nos dice si es edición o nuevo.
--%>

<html>
<head>
    <title>
        <c:choose>
            <c:when test="${isEdit}">Editar Cliente</c:when>
            <c:otherwise>Nuevo Cliente</c:otherwise>
        </c:choose>
    </title>
    <link rel="stylesheet" type="text/css" href="css/table.css"/>
</head>
<body>

<h2 style="text-align:center; color:#0C4163;">
    <c:choose>
        <c:when test="${isEdit}">Editar Cliente</c:when>
        <c:otherwise>Nuevo Cliente</c:otherwise>
    </c:choose>
</h2>

<form method="post" action="clienteServlet">
    <%-- Guardamos la acción: si es edición, enviamos "save" --%>
    <c:choose>
        <c:when test="${isEdit}">
            <input type="hidden" name="action" value="save"/>
            <input type="hidden" name="idcliente" value="${cliente.idcliente}"/>
        </c:when>
        <c:otherwise>
            <input type="hidden" name="action" value="save"/>
        </c:otherwise>
    </c:choose>

    <!-- Campos del formulario -->
    <label>Apellido Paterno:</label>
    <input type="text" name="appaterno" value="${cliente.appaterno}" required/><br/>

    <label>Apellido Materno:</label>
    <input type="text" name="apmaterno" value="${cliente.apmaterno}" required/><br/>

    <label>Nombres:</label>
    <input type="text" name="nombres" value="${cliente.nombres}" required/><br/>

    <label>Fecha de Nacimiento:</label>
    <input type="date" name="nacimiento" value="${cliente.nacimiento}"/><br/>

    <label>Dirección:</label>
    <input type="text" name="direccion" value="${cliente.direccion}"/><br/>

    <label>Referencia:</label>
    <input type="text" name="referencia" value="${cliente.referencia}"/><br/>

    <label>Género:</label>
    <select name="genero" required>
        <option value="M" <c:if test="${cliente.genero=='M'}">selected</c:if>>Masculino</option>
        <option value="F" <c:if test="${cliente.genero=='F'}">selected</c:if>>Femenino</option>
    </select><br/>

    <label>Estado:</label>
    <input type="number" name="estado" min="0" max="1" value="${cliente.estado}"/><br/><br/>

    <!-- Botones de enviar o cancelar -->
    <div style="text-align:center;">
        <button type="submit">
            <c:choose>
                <c:when test="${isEdit}">Actualizar</c:when>
                <c:otherwise>Agregar</c:otherwise>
            </c:choose>
        </button>
        <a href="clientes.jsp">Cancelar</a>
    </div>
</form>

</body>
</html>
