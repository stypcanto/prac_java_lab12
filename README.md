# Lab12 - Gestión de Clientes con Servlets y MySQL

Este proyecto es un ejemplo de aplicación web Java usando **Servlets**, **JSP**, **JSTL**, y **MySQL**. Permite listar clientes desde una base de datos y mostrarlos en una tabla HTML.

---

## 📂 Estructura del proyecto

```src
src
├── main
│   ├── java
│   │   ├── dao
│   │   │   ├── ClienteDAOImpl.java        ← Implementación de operaciones BD
│   │   │   ├── ConnectionDB.java          ← Conexión a MySQL
│   │   │   └── IClienteDAO.java           ← Interfaz con métodos CRUD
│   │   ├── dto
│   │   │   └── ClienteDTO.java            ← Modelo de Cliente (POJO)
│   │   └── servlet
│   │       └── ClienteServlet.java       ← Servlet que maneja acciones del usuario
│   ├── resources
│   │   └── cliente.sql                     ← Script SQL para crear/llenar tabla
│   └── webapp
│       ├── WEB-INF
│       │   └── web.xml                     ← Configuración de servlet
│       ├── cliente_form.jsp               ← Formulario para agregar/editar
│       ├── clientes.jsp                   ← Tabla con lista de clientes
│       ├── css
│       │   └── table.css                  ← Estilos de tabla y formulario
│       ├── images
│       │   ├── del.png                     ← Icono eliminar
│       │   ├── ins.png                     ← Icono agregar
│       │   ├── pi_cap.gif                  ← Fondo cabecera tabla
│       │   ├── pi_hf.gif                   ← Fondo header/footer
│       │   └── upd.png                     ← Icono editar
│       └── index.jsp                       ← Página principal
└── test
    ├── java
    └── resources


```


---


## Descripción de los archivos

### `ClienteServlet.java`
- Es un **Servlet** que maneja las solicitudes HTTP (`GET` y `POST`) para la ruta `/cliente`.
- Su función principal es **obtener la lista de clientes desde la base de datos** usando la capa DAO y enviarla a la vista (`clientes.jsp`) mediante `request.setAttribute`.
- Método clave: `processRequest(HttpServletRequest, HttpServletResponse)`.

---

### `dao/IClienteDAO.java`
- **Interfaz DAO** que define las operaciones de acceso a datos para la entidad `Cliente`.
- **Qué hace:** Define las operaciones que se pueden hacer sobre los clientes en la base de datos.
- **Operaciones:**
    - `getAll()` → Traer todos los clientes.
    - `get(id)` → Traer un cliente específico.
    - `add(cliente)` → Agregar un cliente.
    - `update(cliente)` → Modificar un cliente existente.
    - `del(id)` → Eliminar un cliente.
    - `close()` → Cerrar la conexión a la base de datos.
- **Para qué sirve:** Sirve como un **contrato**: cualquier clase que implemente esta interfaz debe cumplir estas funciones.
- **Analogía:** Es como una **lista de tareas que alguien debe cumplir**.


---

### `dao/ClienteDAOImpl.java`
- **Implementación concreta de `IClienteDAO`**.
- **Qué hace:** Implementa las funciones definidas en `IClienteDAO`.
- **Funciones principales:**
    - Conecta a la base de datos usando `ConnectionDB`.
    - Ejecuta consultas SQL para listar, agregar, editar y eliminar clientes.
    - Devuelve o actualiza los datos usando `ClienteDTO`.
- **Para qué sirve:** Es quien **realmente hace todo el trabajo con la base de datos**.
- **Analogía:** Si `IClienteDAO` es la lista de tareas, esta clase es la **persona que hace las tareas de verdad**.

---

### `dao/ConnectionDB.java`
- Clase que **gestiona la conexión a la base de datos** MySQL usando el patrón Singleton.
- Método principal: `getConnection()` retorna un objeto `Connection`.
- Centraliza la configuración de conexión (`URL`, `usuario`, `password`) para toda la aplicación.
- **Analogía:** Es el **puente que conecta tu aplicación con la base de datos**.

---

### `dto/ClienteDTO.java`
- **Data Transfer Object** para la entidad `Cliente`.
- Contiene atributos que corresponden a las columnas de la tabla `cliente` en la base de datos.
- Permite transportar datos entre la capa DAO y la capa de presentación (Servlet/JSP).
- **Qué hace:** Contiene los datos de un cliente.
- **Campos:** `idcliente`, `appaterno`, `apmaterno`, `nombres`, `nacimiento`, `direccion`, `referencia`, `genero`, `estado`.
- **Para qué sirve:** Transporta los datos del cliente entre la base de datos y la aplicación sin mezclar lógica.
- **Analogía:** Una **cajita que guarda toda la información de un cliente**.

---

### `resources/cliente.sql`
- Script SQL para crear la tabla `cliente` en MySQL y agregar registros de ejemplo.
- Contiene:
    - Creación de la base de datos y tabla.
    - Inserción de 15 registros de prueba.

---

### `webapp/WEB-INF/web.xml`
- Archivo de configuración de **Servlets** y seguridad.
- Define la configuración básica del Servlet `ClienteServlet` si no se usa anotación `@WebServlet`.
- En este proyecto, se usa principalmente la anotación `@WebServlet`, por lo que `web.xml` solo cumple funciones de compatibilidad.

---

### `webapp/clientes.jsp`
- Página JSP que **muestra la lista de clientes en una tabla HTML**.
- Usa **JSTL (`<c:forEach>`)** para iterar sobre la lista de clientes recibida desde el Servlet.
- Incluye referencias a CSS e imágenes para dar formato y estilo a la tabla.

---

## Flujo de la aplicación

1. El usuario accede a `http://localhost:8080/cliente`.
2. `ClienteServlet` se activa y llama a `ClienteDAOImpl` para obtener la lista de clientes.
3. La lista de clientes se pasa a `clientes.jsp` mediante `request.setAttribute`.
4. `clientes.jsp` muestra los clientes en una tabla HTML usando JSTL.

---

## Requisitos

- Java 17
- Apache Tomcat 10
- MySQL
- Maven


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

## Cómo ejecutar

1. Crear la base de datos y la tabla ejecutando `resources/cliente.sql` en MySQL.
2. Configurar los datos de conexión en `ConnectionDB.java`.
3. Construir el proyecto con Maven:

## mvn clean package

```less
4. Copiar el WAR generado a Tomcat o usar Docker para levantar el contenedor.
5. Acceder a `http://localhost:8080/cliente` para ver la lista de clientes.

```
