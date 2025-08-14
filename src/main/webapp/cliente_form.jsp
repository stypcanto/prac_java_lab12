<%@ taglib prefix="c" uri="http://jakarta.ee/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="dto.ClienteDTO" %>
<%
    String action = request.getParameter("action");
    ClienteDTO cliente = (ClienteDTO) request.getAttribute("cliente");
    boolean isEdit = "edit".equals(action) && cliente != null;
%>
<html>
<head>
    <title><%= isEdit ? "Editar Cliente" : "Nuevo Cliente" %></title>
    <link rel="stylesheet" type="text/css" href="css/table.css"/>
</head>
<body>

<h2 style="text-align:center; color:#0C4163;">
    <%= isEdit ? "Editar Cliente" : "Nuevo Cliente" %>
</h2>

<form method="post" action="ClienteServlet">
    <input type="hidden" name="action" value="<%= action %>"/>
    <c:if test="${isEdit}">
        <input type="hidden" name="idcliente" value="${cliente.idcliente}"/>
    </c:if>

    <label>Apellido Paterno:</label>
    <input type="text" name="appaterno" value="<c:out value='${cliente.appaterno}'/>" required/><br/>

    <label>Apellido Materno:</label>
    <input type="text" name="apmaterno" value="<c:out value='${cliente.apmaterno}'/>" required/><br/>

    <label>Nombres:</label>
    <input type="text" name="nombres" value="<c:out value='${cliente.nombres}'/>" required/><br/>

    <label>Fecha de Nacimiento:</label>
    <input type="date" name="nacimiento" value="<c:out value='${cliente.nacimiento}'/>"/><br/>

    <label>Dirección:</label>
    <input type="text" name="direccion" value="<c:out value='${cliente.direccion}'/>"/><br/>

    <label>Referencia:</label>
    <input type="text" name="referencia" value="<c:out value='${cliente.referencia}'/>"/><br/>

    <label>Género:</label>
    <select name="genero" required>
        <option value="M" <c:if test="${cliente.genero=='M'}">selected</c:if>>Masculino</option>
        <option value="F" <c:if test="${cliente.genero=='F'}">selected</c:if>>Femenino</option>
    </select><br/>

    <label>Estado:</label>
    <input type="number" name="estado" min="0" max="1" value="<c:out value='${cliente.estado}'/>"/><br/><br/>

    <div style="text-align:center;">
        <button type="submit"><%= isEdit ? "Actualizar" : "Agregar" %></button>
        <a href="clientes.jsp">Cancelar</a>
    </div>
</form>

</body>
</html>
