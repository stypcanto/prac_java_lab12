<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lab12 - Panel de Administración</title>
    <link rel="stylesheet" type="text/css" href="css/table.css">
    <style>
        body {
            font-family: Verdana, Arial, Tahoma;
            background-color: #f4f7f9;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #0C4163;
            color: #f4f7f9;
            padding: 20px;
            text-align: center;
        }
        main {
            padding: 40px;
            text-align: center;
        }
        .button {
            display: inline-block;
            padding: 12px 25px;
            margin: 10px;
            background-color: #0C4163;
            color: #f4f7f9;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
            transition: 0.3s;
        }
        .button:hover {
            background-color: #145a8d;
        }
        footer {
            margin-top: 40px;
            text-align: center;
            color: #444;
            font-size: 0.9em;
        }
    </style>
</head>
<body>
<header>
    <h1>Panel de Administración de Clientes</h1>
    <p>Accede a las opciones para gestionar la base de datos</p>
</header>

<main>
    <a class="button" href="clienteServlet?action=list">Ver Listado de Clientes</a>
    <a class="button" href="clienteServlet?action=add">Agregar Cliente</a>
    <a class="button" href="resources/cliente.sql" target="_blank">Ver Script de Base de Datos</a>
</main>

<footer>
    <p>Lab12 - Proyecto de Gestión de Clientes</p>
</footer>

</body>
</html>
