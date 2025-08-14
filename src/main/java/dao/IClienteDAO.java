package dao;

import dto.ClienteDTO;
import java.sql.SQLException;
import java.util.List;

// Esto es una INTERFAZ. Piensa en ella como una lista de instrucciones: "tienes que poder hacer estas cosas"
// Pero la interfaz **no dice cómo hacerlo**, solo dice qué métodos existen.
public interface IClienteDAO {

	// 1️⃣ Traer todos los clientes
	// Devuelve una lista de todos los clientes que existen en la base de datos
	List<ClienteDTO> getAll() throws SQLException, ClassNotFoundException;

	// 2️⃣ Agregar un cliente nuevo
	// Recibe un ClienteDTO con los datos del cliente y devuelve el ID que se generó en la base de datos
	Long add(ClienteDTO clienteDTO) throws SQLException, ClassNotFoundException;

	// 3️⃣ Borrar un cliente
	// Recibe el ID de un cliente y lo elimina de la base de datos
	void del(Long idCliente) throws SQLException, ClassNotFoundException;

	// 4️⃣ Traer un solo cliente
	// Devuelve un ClienteDTO con los datos de un cliente específico según su ID
	ClienteDTO get(Long idCliente) throws SQLException, ClassNotFoundException;

	// 5️⃣ Actualizar un cliente existente
	// Recibe un ClienteDTO con los datos nuevos y actualiza el cliente que tenga ese ID
	void update(ClienteDTO clienteDTO) throws SQLException, ClassNotFoundException;

	// 6️⃣ Cerrar la conexión
	// Sirve para desconectarse de la base de datos y liberar recursos
	void close() throws SQLException;
}
