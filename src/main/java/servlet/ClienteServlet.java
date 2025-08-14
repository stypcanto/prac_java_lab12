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

// Esto le dice a Tomcat que cuando alguien entre a /clienteServlet
// este servlet debe manejar la solicitud
@WebServlet(name = "ClienteServlet", value = "/clienteServlet")
public class ClienteServlet extends HttpServlet {

    // Este método procesa todas las solicitudes
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {

        // Creamos nuestro objeto DAO para hablar con la base de datos
        IClienteDAO dao = new ClienteDAOImpl();

        // Leemos la acción que el usuario quiere hacer (list, add, edit, delete, save)
        String action = request.getParameter("action");
        if (action == null) {
            action = "list"; // Si no viene acción, mostramos la lista
        }

        switch (action) {
            case "list":
                // Listar todos los clientes
                List<ClienteDTO> clientes = dao.getAll();
                request.setAttribute("clientes", clientes);
                request.getRequestDispatcher("/clientes.jsp").forward(request, response);
                break;

            case "add":
                // Abrir formulario vacío para agregar un nuevo cliente
                request.setAttribute("cliente", new ClienteDTO());
                request.getRequestDispatcher("/cliente_form.jsp").forward(request, response);
                break;

            case "edit":
                // Abrir formulario con datos del cliente para editar
                Long idEdit = Long.parseLong(request.getParameter("id"));
                ClienteDTO clienteEdit = dao.get(idEdit);
                request.setAttribute("cliente", clienteEdit);
                request.getRequestDispatcher("/cliente_form.jsp").forward(request, response);
                break;

            case "delete":
                // Eliminar un cliente
                Long idDel = Long.parseLong(request.getParameter("id"));
                dao.del(idDel);
                response.sendRedirect("clienteServlet"); // Volvemos a la lista
                break;

            case "save":
                // Guardar cliente (nuevo o editado)
                String idStr = request.getParameter("idcliente");
                Long idCliente = (idStr == null || idStr.isEmpty()) ? null : Long.parseLong(idStr);

                ClienteDTO clienteSave = new ClienteDTO();
                clienteSave.setIdcliente(idCliente);
                clienteSave.setAppaterno(request.getParameter("appaterno"));
                clienteSave.setApmaterno(request.getParameter("apmaterno"));
                clienteSave.setNombres(request.getParameter("nombres"));
                // Convertimos fecha (simple, puede mejorarse con DateFormat)
                String fecha = request.getParameter("nacimiento");
                clienteSave.setNacimiento((fecha == null || fecha.isEmpty()) ? null : java.sql.Date.valueOf(fecha));
                clienteSave.setDireccion(request.getParameter("direccion"));
                clienteSave.setReferencia(request.getParameter("referencia"));
                clienteSave.setGenero(request.getParameter("genero"));
                String estadoStr = request.getParameter("estado");
                clienteSave.setEstado((estadoStr == null || estadoStr.isEmpty()) ? 0 : Integer.parseInt(estadoStr));

                if (idCliente == null) {
                    // Si no tiene ID, es nuevo -> agregamos
                    dao.add(clienteSave);
                } else {
                    // Si tiene ID, ya existe -> actualizamos
                    dao.update(clienteSave);
                }

                // Volvemos a la lista de clientes
                response.sendRedirect("clienteServlet");
                break;

            default:
                // Acción desconocida, mostramos la lista
                response.sendRedirect("clienteServlet");
        }

        // Cerramos la conexión al final
        dao.close();
    }

    // GET maneja solicitudes de solo leer (como listar, abrir formulario)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Error al procesar la solicitud del cliente", e);
        }
    }

    // POST maneja solicitudes que envían datos (como guardar)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // Redirigimos a processRequest para simplificar
    }
}
