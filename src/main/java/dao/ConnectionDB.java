package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	private static ConnectionDB connectionDB;

	private ConnectionDB() {
	}

	public static ConnectionDB getInstance() {
		if (connectionDB == null) {
			connectionDB = new ConnectionDB();
		}
		return connectionDB;
	}

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		String driver = "com.mysql.cj.jdbc.Driver";

		// Datos de conexión para Docker Compose
		String host = "mysql-db";       // nombre del servicio en docker-compose
		String port = "3306";
		String database = "laboratorio";
		String user = "usuario_lab";
		String password = "pass_lab";

		// Cargar driver
		Class.forName(driver);

		// URL JDBC
		String url = String.format(
				"jdbc:mysql://%s:%s/%s?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false",
				host, port, database
		);

		// Retornar conexión
		return DriverManager.getConnection(url, user, password);
	}
}

