package dao;

import dto.ClienteDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements IClienteDAO {

	private Connection connection;

	public ClienteDAOImpl() throws SQLException, ClassNotFoundException {
		this.connection = ConnectionDB.getInstance().getConnection();
	}

	@Override
	public List<ClienteDTO> getAll() throws SQLException {
		List<ClienteDTO> clientes = new ArrayList<>();
		String sql = "SELECT * FROM cliente";
		try (Statement st = connection.createStatement();
			 ResultSet rs = st.executeQuery(sql)) {
			while (rs.next()) {
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
		return clientes;
	}

	@Override
	public Long add(ClienteDTO clienteDTO) throws SQLException {
		String sql = "INSERT INTO cliente (appaterno, apmaterno, nombres, nacimiento, direccion, referencia, genero, estado) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, clienteDTO.getAppaterno());
			ps.setString(2, clienteDTO.getApmaterno());
			ps.setString(3, clienteDTO.getNombres());
			ps.setDate(4, clienteDTO.getNacimiento());
			ps.setString(5, clienteDTO.getDireccion());
			ps.setString(6, clienteDTO.getReferencia());
			ps.setString(7, clienteDTO.getGenero());
			ps.setInt(8, clienteDTO.getEstado());
			ps.executeUpdate();

			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					return rs.getLong(1);
				}
			}
		}
		return null;
	}

	@Override
	public void del(Long idCliente) throws SQLException {
		String sql = "DELETE FROM cliente WHERE idcliente = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setLong(1, idCliente);
			ps.executeUpdate();
		}
	}

	@Override
	public ClienteDTO get(Long idCliente) throws SQLException {
		String sql = "SELECT * FROM cliente WHERE idcliente = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setLong(1, idCliente);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
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
		return null;
	}

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
			ps.setLong(9, clienteDTO.getIdcliente());
			ps.executeUpdate();
		}
	}

	@Override
	public void close() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}
}
