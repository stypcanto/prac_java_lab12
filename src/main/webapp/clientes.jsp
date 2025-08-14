<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Lista de profesores</title>
    <link rel="stylesheet" type="text/css" href="css/table.css"/>

</head>
<body>
<table class="navy">
    <caption>Lista de Profesores</caption>
    <thead>
    <tr>
        <th>Id</th>
        <th>Apellido Paterno</th>
        <th>Apellido Materno</th>
        <th>Nombres</th>
        <th>F. Nacimiento</th>
        <th>Direccion</th>
        <th>Referencia</th>
        <th>Genero</th>
        <th>Estado</th>
        <th> <img src="images/ins.png" /> </th>
        <th><img src="images/del.png" /></th>
        <th> <img src="images/upd.png" /> </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="c" items="${clientes}">
        <tr>
            <td>${c.getIdcliente()} </td>
            <td>${c.getAppaterno()} </td>
            <td>${c.getApmaterno()} </td>
            <td>${c.getNombres()} </td>
            <td>${c.getNacimiento()} </td>
            <td>${c.getDireccion()} </td>
            <td>${c.getReferencia()} </td>
            <td>${c.getGenero()} </td>
            <td colspan="2">${c.getEstado()} </td>
            <td><input type="checkbox" name="chk_del" value="${c.getIdcliente()}"/></td>
            <td><input type="radio" name="rad_upd" value="${c.getIdcliente()}"/></td>
        </tr>
    </c:forEach>
    </tbody>
    <tfoot>
    <tr><td colspan="12">
        Listado de clientes</td>
    </tr>

    </tfoot>
</table>


</body>
</html>
