# Lab12 - Gestión de Clientes con Servlets y MySQL

Este proyecto es un ejemplo de una **aplicación web en Java** que utiliza **Servlets**, **JSP** y **MySQL** para gestionar información de clientes. La aplicación permite listar clientes desde la base de datos y mostrar sus datos en una página web.

---

## 📂 Estructura del proyecto

```src
├── main
│ ├── java
│ │ ├── ClienteServlet.java # Servlet principal
│ │ ├── dao
│ │ │ ├── ClienteDAOImpl.java # Implementación DAO
│ │ │ ├── ConnectionDB.java # Conexión a la base de datos
│ │ │ └── IClienteDAO.java # Interfaz DAO
│ │ └── dto
│ │ └── ClienteDTO.java # DTO de Cliente
│ ├── resources
│ │ └── cliente.sql # Script de creación de tabla y datos
│ └── webapp
│ ├── WEB-INF
│ │ └── web.xml # Configuración del servlet
│ ├── clientes.jsp # Página JSP que lista los clientes
│ ├── css
│ │ └── table.css # Estilos de tabla
│ ├── images # Imágenes usadas en la interfaz
│ └── index.jsp # Página de inicio
└── test
├── java
└── resources
```


---

## ⚙️ Funcionalidad

1. **ClienteDTO**: Clase que representa los datos de un cliente.
2. **IClienteDAO**: Interfaz que define operaciones CRUD (listar, obtener, agregar, actualizar, eliminar).
3. **ClienteDAOImpl**: Implementación de `IClienteDAO` usando JDBC y `ConnectionDB`.
4. **ConnectionDB**: Clase singleton que gestiona la conexión a la base de datos MySQL.
5. **ClienteServlet**: Servlet que maneja las solicitudes HTTP (`GET` y `POST`) y envía la lista de clientes a `clientes.jsp`.
6. **clientes.jsp**: Página que recibe la lista de clientes y la muestra en una tabla HTML.

---

## 🛠️ Configuración de la base de datos

1. Asegúrate de tener MySQL instalado.
2. Crea la base de datos:

```sql
CREATE DATABASE laboratorio;
USE laboratorio;

CREATE TABLE clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    correo VARCHAR(100),
    telefono VARCHAR(20)
);

INSERT INTO clientes(nombre, correo, telefono) VALUES
('Juan Perez', 'juan.perez@mail.com', '987654321'),
('Maria Lopez', 'maria.lopez@mail.com', '987654322');
```

3. Actualiza la conexión en ConnectionDB.java si cambias usuario, contraseña o base de datos:

 ```java
String host = "localhost";   // o "mysql-db" si usas Docker
String port = "3306";
String database = "laboratorio";
String user = "usuario_lab";
String pwd = "pass_lab";

```

