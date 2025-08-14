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

		// Si corres el backend en el mismo docker-compose, usa "mysql-db" como host
		String host = "mysql-db";
		String port = "3306";
		String database = "laboratorio";
		String user = "usuario_lab";
		String pwd = "pass_lab";

		// JDBC URL con parámetros para evitar problemas de SSL y Public Key Retrieval
		String url = String.format(
				"jdbc:mysql://%s:%s/%s?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false",
				host, port, database
		);

		Class.forName(driver);
		return DriverManager.getConnection(url, user, pwd);
	}
}
