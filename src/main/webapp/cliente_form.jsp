<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>
        <%-- Si estamos editando, ponemos "Editar Cliente"; si es nuevo, "Nuevo Cliente" --%>
        <c:choose>
            <c:when test="${isEdit}">Editar Cliente</c:when>
            <c:otherwise>Nuevo Cliente</c:otherwise>
        </c:choose>
    </title>
    <link rel="stylesheet" type="text/css" href="css/table.css"/>
</head>
<body>

<h2 style="text-align:center; color:#0C4163;">
    <%-- Título grande igual que arriba, según si es edición o nuevo --%>
    <c:choose>
        <c:when test="${isEdit}">Editar Cliente</c:when>
        <c:otherwise>Nuevo Cliente</c:otherwise>
    </c:choose>
</h2>

<form method="post" action="clienteServlet">
    <%-- Siempre enviamos la acción como "save" para que el servlet sepa que queremos guardar --%>
    <input type="hidden" name="action" value="save"/>

    <%--
        Aquí guardamos el ID del cliente.
        - Si estamos editando, enviamos el ID real para que el servlet haga UPDATE.
        - Si es nuevo, dejamos vacío para que el servlet haga ADD.
        Esto evita que se creen duplicados cuando actualizamos.
    --%>
    <c:choose>
        <c:when test="${isEdit}">
            <input type="hidden" name="idcliente" value="${cliente.idcliente}"/>
        </c:when>
        <c:otherwise>
            <input type="hidden" name="idcliente" value=""/>
        </c:otherwise>
    </c:choose>

    <%-- Campos del formulario --%>
    <label>Apellido Paterno:</label>
    <input type="text" name="appaterno" value="${cliente.appaterno}" required/><br/>

    <label>Apellido Materno:</label>
    <input type="text" name="apmaterno" value="${cliente.apmaterno}" required/><br/>

    <label>Nombres:</label>
    <input type="text" name="nombres" value="${cliente.nombres}" required/><br/>

    <%-- Fecha de nacimiento: solo la mostramos si existe --%>
    <label>Fecha de Nacimiento:</label>
    <input type="date" name="nacimiento"
           value="<c:if test='${not empty cliente.nacimiento}'>
                      <fmt:formatDate value='${cliente.nacimiento}' pattern='yyyy-MM-dd'/>
                  </c:if>"/><br/>
    <%-- Esto asegura que si es nuevo, el campo queda vacío y no da error --%>

    <label>Dirección:</label>
    <input type="text" name="direccion" value="${cliente.direccion}"/><br/>

    <label>Referencia:</label>
    <input type="text" name="referencia" value="${cliente.referencia}"/><br/>

    <%-- Género: seleccionamos automáticamente según el cliente, si existe --%>
    <label>Género:</label>
    <select name="genero" required>
        <option value="">Seleccione</option> <%-- Opción por defecto para forzar elección --%>
        <option value="M" <c:if test="${cliente.genero=='M'}">selected</c:if>>Masculino</option>
        <option value="F" <c:if test="${cliente.genero=='F'}">selected</c:if>>Femenino</option>
    </select><br/>

    <%-- Estado: si no tiene valor, ponemos 0 por defecto --%>
    <label>Estado:</label>
    <input type="number" name="estado" min="0" max="1"
           value="<c:out value='${cliente.estado != null ? cliente.estado : 0}'/>"/><br/><br/>

    <%-- Botones de enviar o cancelar --%>
    <div style="text-align:center;">
        <button type="submit">
            <%-- Cambiamos el texto del botón según si es edición o nuevo --%>
            <c:choose>
                <c:when test="${isEdit}">Actualizar</c:when>
                <c:otherwise>Agregar</c:otherwise>
            </c:choose>
        </button>
        <a href="clientes.jsp">Cancelar</a> <%-- Volver a la lista sin guardar --%>
    </div>
</form>

</body>
</html>
