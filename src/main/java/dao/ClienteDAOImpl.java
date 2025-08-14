package dao;

import dto.ClienteDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Esta clase es la que se encarga de hablar con la base de datos
// y hacer todo lo que tiene que ver con los clientes (CRUD: crear, leer, actualizar, borrar)
public class ClienteDAOImpl implements IClienteDAO {

	// Esta variable es como el "teléfono" para hablar con la base de datos
	private Connection connection;

	// Constructor: cuando creamos un ClienteDAOImpl, nos conectamos a la base de datos
	public ClienteDAOImpl() throws SQLException, ClassNotFoundException {
		// ConnectionDB.getInstance() nos da la conexión a la base de datos
		this.connection = ConnectionDB.getInstance().getConnection();
	}

	// MÉTODO 1: Traer todos los clientes de la base de datos
	@Override
	public List<ClienteDTO> getAll() throws SQLException {
		List<ClienteDTO> clientes = new ArrayList<>(); // Aquí guardaremos todos los clientes
		String sql = "SELECT * FROM cliente"; // Esto es lo que le decimos a la base de datos

		// try-with-resources: abre y cierra automáticamente las cosas que usamos
		try (Statement st = connection.createStatement();
			 ResultSet rs = st.executeQuery(sql)) { // rs es como una lista de resultados
			while (rs.next()) { // Recorremos cada fila que nos dio la base de datos
				// Creamos un ClienteDTO con los datos de esa fila y lo agregamos a la lista
				clientes.add(new ClienteDTO(
						rs.getLong("idcliente"),
						rs.getString("appaterno"),
						rs.getString("apmaterno"),
						rs.getString("nombres"),
						rs.getDate("nacimiento"),
						rs.getString("direccion"),
						rs.getString("referencia"),
						rs.getString("genero"),
						rs.getInt("estado")
				));
			}
		}
		return clientes; // Devolvemos todos los clientes
	}

	// MÉTODO 2: Agregar un cliente nuevo a la base de datos
	@Override
	public Long add(ClienteDTO clienteDTO) throws SQLException {
		// Esto es lo que vamos a decirle a la base de datos para agregar un cliente
		String sql = "INSERT INTO cliente (appaterno, apmaterno, nombres, nacimiento, direccion, referencia, genero, estado) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			// Ponemos los datos del cliente en la "plantilla" del SQL
			ps.setString(1, clienteDTO.getAppaterno());
			ps.setString(2, clienteDTO.getApmaterno());
			ps.setString(3, clienteDTO.getNombres());
			ps.setDate(4, clienteDTO.getNacimiento());
			ps.setString(5, clienteDTO.getDireccion());
			ps.setString(6, clienteDTO.getReferencia());
			ps.setString(7, clienteDTO.getGenero());
			ps.setInt(8, clienteDTO.getEstado());

			ps.executeUpdate(); // Ejecutamos la inserción en la base de datos

			// Esto obtiene el ID que la base de datos generó automáticamente
			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					return rs.getLong(1); // Devolvemos el ID del nuevo cliente
				}
			}
		}
		return null; // Si algo sale mal, devolvemos null
	}

	// MÉTODO 3: Borrar un cliente de la base de datos
	@Override
	public void del(Long idCliente) throws SQLException {
		String sql = "DELETE FROM cliente WHERE idcliente = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setLong(1, idCliente); // Le decimos qué cliente borrar usando su ID
			ps.executeUpdate(); // Ejecutamos el borrado
		}
	}

	// MÉTODO 4: Traer un solo cliente de la base de datos (por su ID)
	@Override
	public ClienteDTO get(Long idCliente) throws SQLException {
		String sql = "SELECT * FROM cliente WHERE idcliente = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setLong(1, idCliente); // Ponemos el ID en la consulta
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) { // Si encontramos al cliente
					// Creamos un ClienteDTO con sus datos y lo devolvemos
					return new ClienteDTO(
							rs.getLong("idcliente"),
							rs.getString("appaterno"),
							rs.getString("apmaterno"),
							rs.getString("nombres"),
							rs.getDate("nacimiento"),
							rs.getString("direccion"),
							rs.getString("referencia"),
							rs.getString("genero"),
							rs.getInt("estado")
					);
				}
			}
		}
		return null; // Si no encontramos al cliente, devolvemos null
	}

	// MÉTODO 5: Actualizar los datos de un cliente que ya existe
	@Override
	public void update(ClienteDTO clienteDTO) throws SQLException {
		String sql = "UPDATE cliente SET appaterno=?, apmaterno=?, nombres=?, nacimiento=?, direccion=?, referencia=?, genero=?, estado=? " +
				"WHERE idcliente=?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, clienteDTO.getAppaterno());
			ps.setString(2, clienteDTO.getApmaterno());
			ps.setString(3, clienteDTO.getNombres());
			ps.setDate(4, clienteDTO.getNacimiento());
			ps.setString(5, clienteDTO.getDireccion());
			ps.setString(6, clienteDTO.getReferencia());
			ps.setString(7, clienteDTO.getGenero());
			ps.setInt(8, clienteDTO.getEstado());
			ps.setLong(9, clienteDTO.getIdcliente()); // Le decimos cuál cliente actualizar
			ps.executeUpdate(); // Ejecutamos la actualización
		}
	}

	// MÉTODO 6: Cerrar la conexión con la base de datos
	@Override
	public void close() throws SQLException {
		if (connection != null) {
			connection.close(); // Apagamos el "teléfono" con la base de datos
		}
	}
}
