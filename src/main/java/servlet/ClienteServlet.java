package servlet;

import dao.ClienteDAOImpl;
import dao.IClienteDAO;
import dto.ClienteDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/*
    Esto le dice a Tomcat que cuando alguien abra
    /clienteServlet, este servlet se encargará de procesarlo.
*/
@WebServlet(name = "ClienteServlet", value = "/clienteServlet")
public class ClienteServlet extends HttpServlet {

    /**
     * Este método es como el "cerebro" del servlet.
     * Recibe todas las solicitudes (agregar, editar, borrar, listar)
     * y decide qué hacer con cada una.
     *
     * @param request lo que nos envía el navegador
     * @param response lo que vamos a devolver al navegador
     * @throws ServletException, IOException si algo sale mal
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        IClienteDAO dao = null; // nuestro "teléfono" para hablar con la base de datos

        try {
            dao = new ClienteDAOImpl(); // nos conectamos a la base de datos

            // Leemos qué acción quiere hacer el usuario
            String action = request.getParameter("action");
            if (action == null) action = "list"; // si no dice nada, mostramos la lista

            switch (action) {

                case "list":
                    // 1️⃣ Listar todos los clientes
                    List<ClienteDTO> clientes = dao.getAll(); // pedimos todos los clientes
                    request.setAttribute("clientes", clientes); // guardamos la lista para el JSP
                    request.getRequestDispatcher("/clientes.jsp").forward(request, response); // vamos a la lista
                    break;

                case "add":
                    // 2️⃣ Abrir formulario vacío para agregar un nuevo cliente
                    request.setAttribute("cliente", new ClienteDTO()); // cliente vacío
                    request.setAttribute("isEdit", false); // no es edición
                    request.getRequestDispatcher("/cliente_form.jsp").forward(request, response);
                    break;

                case "edit":
                    // 3️⃣ Abrir formulario con datos del cliente para editar
                    Long idEdit = Long.parseLong(request.getParameter("id")); // leemos el id
                    ClienteDTO clienteEdit = dao.get(idEdit); // pedimos los datos de la base de datos

                    if (clienteEdit == null) {
                        // Si no encontramos al cliente, volvemos a la lista
                        response.sendRedirect("clienteServlet");
                        return;
                    }

                    request.setAttribute("cliente", clienteEdit); // guardamos para el formulario
                    request.setAttribute("isEdit", true); // es edición
                    request.getRequestDispatcher("/cliente_form.jsp").forward(request, response);
                    break;

                case "delete":
                    // 4️⃣ Borrar un cliente
                    Long idDel = Long.parseLong(request.getParameter("id"));
                    dao.del(idDel); // borramos en la base de datos
                    response.sendRedirect("clienteServlet"); // volvemos a la lista
                    break;

                case "save":
                    // 5️⃣ Guardar cliente (nuevo o editado)
                    String idStr = request.getParameter("idcliente");
                    Long idCliente = (idStr == null || idStr.isEmpty()) ? null : Long.parseLong(idStr);

                    // Creamos un objeto ClienteDTO con todos los datos del formulario
                    ClienteDTO clienteSave = new ClienteDTO();
                    clienteSave.setIdcliente(idCliente); // importante para update
                    clienteSave.setAppaterno(request.getParameter("appaterno"));
                    clienteSave.setApmaterno(request.getParameter("apmaterno"));
                    clienteSave.setNombres(request.getParameter("nombres"));

                    // Validamos fecha: si viene vacía, ponemos null
                    String fecha = request.getParameter("nacimiento");
                    if (fecha != null && !fecha.isEmpty()) {
                        try {
                            clienteSave.setNacimiento(java.sql.Date.valueOf(fecha));
                        } catch (IllegalArgumentException e) {
                            clienteSave.setNacimiento(null);
                        }
                    }

                    clienteSave.setDireccion(request.getParameter("direccion"));
                    clienteSave.setReferencia(request.getParameter("referencia"));
                    clienteSave.setGenero(request.getParameter("genero"));

                    // Validamos estado: si viene vacío, ponemos 0
                    String estadoStr = request.getParameter("estado");
                    clienteSave.setEstado((estadoStr == null || estadoStr.isEmpty()) ? 0 : Integer.parseInt(estadoStr));

                    if (idCliente == null) {
                        // ✅ Si no tiene ID, es nuevo -> agregamos
                        dao.add(clienteSave);
                    } else {
                        // ✅ Si tiene ID, ya existe -> actualizamos
                        // Esto evita duplicados porque solo hace UPDATE
                        dao.update(clienteSave);
                    }

                    response.sendRedirect("clienteServlet"); // volvemos a la lista
                    break;

                default:
                    // Acción desconocida -> mostramos la lista
                    response.sendRedirect("clienteServlet");
            }

        } catch (SQLException | ClassNotFoundException e) {
            // Si algo sale mal, avisamos con detalle
            throw new ServletException("Error al procesar la solicitud del cliente", e);
        } finally {
            // Cerramos la conexión aunque ocurra un error
            if (dao != null) {
                try { dao.close(); } catch (SQLException ignored) {}
            }
        }
    }

    /**
     * @Override significa: "Estoy sobrescribiendo un método de HttpServlet"
     * doGet se ejecuta cuando alguien abre una página (GET)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response); // llamamos al método central
    }

    /**
     * @Override significa: "Estoy sobrescribiendo un método de HttpServlet"
     * doPost se ejecuta cuando alguien envía un formulario (POST)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // importante para acentos y ñ
        processRequest(request, response); // llamamos al método central
    }
}
