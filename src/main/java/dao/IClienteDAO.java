package dao;

import dto.ClienteDTO;

import java.sql.SQLException;
import java.util.List;

public interface IClienteDAO
{
	List<ClienteDTO> getAll() throws SQLException, ClassNotFoundException;
	
	Long add(ClienteDTO clienteDTO) throws SQLException, ClassNotFoundException;
	
	void del(Long idCliente) throws SQLException, ClassNotFoundException;
	
	ClienteDTO get(Long idCliente) throws SQLException, ClassNotFoundException;
	
	void update(ClienteDTO clienteDTO) throws SQLException, ClassNotFoundException;
	
	void close() throws SQLException;
}
