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

@WebServlet(name = "clienteServlet", value = "/cliente")
public class ClienteServlet extends HttpServlet {

    private List<ClienteDTO> clientes;

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ClassNotFoundException, ServletException, IOException {

        IClienteDAO dao = new ClienteDAOImpl();
        try {
            clientes = dao.getAll();
        } finally {
            dao.close();
        }

        request.setAttribute("clientes", clientes);
        request.getRequestDispatcher("/clientes.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Error al procesar la solicitud del cliente", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
