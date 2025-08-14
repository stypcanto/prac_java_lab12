<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--
    Esta página muestra la lista de clientes que el servlet nos pasa en la variable "clientes".
    Usamos JSTL para recorrer la lista y mostrar cada cliente en una tabla.
--%>

<html>
<head>
    <title>Gestión de Clientes</title>
    <link rel="stylesheet" type="text/css" href="css/table.css"/>
</head>
<body>

<h2 style="text-align:center; color:#0C4163;">Gestión de Clientes</h2>

<!-- Botón para agregar un cliente nuevo -->
<div style="text-align:center; margin-bottom:15px;">
    <a href="clienteServlet?action=add">
        <button>Nuevo Cliente</button>
    </a>
</div>

<table class="navy">
    <caption>Lista de Clientes</caption>
    <thead>
    <tr>
        <th>Id</th>
        <th>Apellido Paterno</th>
        <th>Apellido Materno</th>
        <th>Nombres</th>
        <th>F. Nacimiento</th>
        <th>Dirección</th>
        <th>Referencia</th>
        <th>Género</th>
        <th>Estado</th>
        <th>Editar</th>
        <th>Eliminar</th>
    </tr>
    </thead>
    <tbody>
    <%-- Recorremos cada cliente con JSTL --%>
    <c:forEach var="c" items="${clientes}">
        <tr>
            <td>${c.idcliente}</td>
            <td>${c.appaterno}</td>
            <td>${c.apmaterno}</td>
            <td>${c.nombres}</td>
            <td>${c.nacimiento}</td>
            <td>${c.direccion}</td>
            <td>${c.referencia}</td>
            <td>${c.genero}</td>
            <td>${c.estado}</td>
            <!-- Botón para editar el cliente -->
            <td>
                <a href="clienteServlet?action=edit&id=${c.idcliente}">
                    <img src="images/upd.png" alt="Editar"/>
                </a>
            </td>
            <!-- Botón para eliminar el cliente -->
            <td>
                <a href="clienteServlet?action=delete&id=${c.idcliente}" onclick="return confirm('¿Eliminar este cliente?');">
                    <img src="images/del.png" alt="Eliminar"/>
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
