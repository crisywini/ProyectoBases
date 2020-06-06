package co.uniquindio.bases.supermarket.SuperMarketCampestre.database.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	static final String CONTROLLER = "com.mysql.jdbc.Driver";

	static final String URL_DATABASE = "jdbc:mysql://localhost:3306/super_market_campestre?autoReconnect=true&useSSL=false";
	static final String URL_INIT = "jdbc:mysql://localhost:3306/?autoReconnect=true&useSSL=false";
	static final String USER = "root";
	static final String PASSWORD = "root";
	protected Connection connection = instanceConnection();;

	public Conexion(Connection connection) {
		this.connection = connection;
	}

	public Conexion() {
		super();
	}

	private Connection instanceConnection() {
		try {
			Class.forName(CONTROLLER).newInstance();
			return connection = DriverManager.getConnection(URL_DATABASE, USER, PASSWORD);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}// Carga la clase CONTROLLER
		catch (SQLException e) {
			e.printStackTrace();
		}
		return instanceConnection();
	}
}
