package co.uniquindio.bases.supermarket.SuperMarketCampestre.database.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InitDatabase {
	/**
	 * This Class will create the database and will create the tables and
	 * relationships to get started.
	 */
	static final String CONTROLLER = "com.mysql.jdbc.Driver";

	static final String URL_DATABASE = "jdbc:mysql://localhost:3306/super_market_campestre?autoReconnect=true&useSSL=false";
	static final String URL_INIT = "jdbc:mysql://localhost:3306/?autoReconnect=true&useSSL=false";
	static final String USER = "root";
	static final String PASSWORD = "root";
	private static Connection connection;

	public static void main(String[] args) {
		createDatabase();
		createAllTables();
	}

	public static String createDatabase(String nameDatabase) {
		return "CREATE DATABASE " + nameDatabase;
	}

	public static String dropDataBase(String nameDatabase) {
		return "DROP DATABASE " + nameDatabase;
	}

	public static void createDatabase() {
		Statement statement;
		try {
			Class.forName(CONTROLLER).newInstance();// Carga la clase CONTROLLER
			connection = DriverManager.getConnection(URL_INIT, USER, PASSWORD);// Establece la conexión a la base de
																				// datos
			System.out.println("Conexión a la base de datos establecida...  ");
			statement = connection.createStatement();// Crea el Statement para la consulta
			String nameDatabase = "super_market_campestre";
			System.out.println("Creando base de datos...");
			statement.executeUpdate(createDatabase(nameDatabase) + ";");// Aquí va la consulta update -> DDL
			System.out.println("Base de datos: " + nameDatabase + " creada");
		} catch (SQLException exception) {
			if (exception.getMessage().contains("database exists")) {
				System.out.println("La base de datos ya existe!");
			} else {
				exception.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			System.out.println("error controlador");
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static void createTable(String tableDetails) {
		Statement statement;
		try {
			Class.forName(CONTROLLER).newInstance();// Carga la clase CONTROLLER
			connection = DriverManager.getConnection(URL_DATABASE, USER, PASSWORD);// Establece la conexión a la base de
																					// datos
			statement = connection.createStatement();// Crea el Statement para la consulta
			System.out.println("Creando tabla...");
			statement.executeUpdate(tableDetails);// Aquí va la consulta
			System.out.println("Tabla: " + tableDetails + " creada");
		} catch (SQLException exception) {
			if (exception.getMessage().contains("already exists")) {
				System.out.println(exception.getMessage());
			} else {
				exception.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error controlador");
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static void createAllTables() {
		String createClientTable = "CREATE TABLE Client ( code int NOT NULL AUTO_INCREMENT, name VARCHAR(20), last_name VARCHAR(10), address VARCHAR(40), phone_number VARCHAR(10), PRIMARY KEY (code) );";
		String createEmployeeTable = "CREATE TABLE Employee ( code VARCHAR(10) NOT NULL, name VARCHAR(20) NOT NULL, last_name VARCHAR(20) NOT NULL, email VARCHAR(40) NOT NULL UNIQUE, address VARCHAR(50) NOT NULL, actual_job VARCHAR(40) NOT NULL, PRIMARY KEY (code) );"; // creoqueseborra_actualjob
		String createPhoneNumerTable = "CREATE TABLE Phone_number (number VARCHAR(10) NOT NULL, code_employee VARCHAR(10) NOT NULL, PRIMARY KEY(number, code_employee), FOREIGN KEY(code_employee) REFERENCES Employee (code) );";
		String createServiceTable = "CREATE TABLE Service ( code int NOT NULL AUTO_INCREMENT, details VARCHAR(40) NOT NULL, name VARCHAR(40) NOT NULL, PRIMARY KEY(code) );";

		String createServiceInvoice = "CREATE TABLE Invoice_service ( code int NOT NULL AUTO_INCREMENT, code_service int NOT NULL, address VARCHAR(20) NOT NULL, date VARVHAR(20) NOT NULL, description VARCHAR(40), total_payment VARCHAR(20) NOT NULL, PRIMARY KEY (code), FOREIGN KEY(code_service) REFERENCES Service (code) )"; // cantidad,devuelta??_pago_varchar_double?
		String createProviderTable = "CREATE TABLE Provier ( code int NOT NULL, name VARCHAR(20) NOT NULL, address VARCHAR(20) NOT NULL, email VARCHAR(20), phone_number VARCHAR(10), PRIMARY KEY (code) )"; // code_auto??
		String createProductTable = "CREATE TABLE Product ( code int NOT NULL AUTO_INCREMENT, code_provider int NOT NULL, name VARCHAR(20) NOT NULL, cantidad int NOT NULL, detalles VARCHAR(40), PRIMARY KEY(code), FOREIGN KEY(code_provider) REFERENCES Provider (code) )";
		String createInventoryTable = "CREATE TABLE Inventory ( code int NOT NULL AUTO_INCREMENT, date VARCHAR(20) NOT NULL, PRIMARY KEY(code) )";
		String createContractTable = "CREATE TABLE Contract ( code int NOT NULL AUTO_INCREMENT, code_employee int NOT NULL, salary DOUBLE NOT NULL, start_date VARCHAR(20) NOT NULL, end_date VARCHAR(20) NOT NULL, PRIMARY KEY (code, code_employee), FOREIGN KEY(code_employee) REFERENCES Employee (code) )"; // salario_double?
		String createCotractTypeTable = "CREATE TABLE Contract_type ( code int NOT NULL AUTO_INCREMENT, name VARCHAR(20) NOT NULL, desciption VARCHAT(40), code_contract int NOT NULL, code_contract_employee VARCHAR(10) NOT NULL, PRIMARY KEY (code, code_contract_employee), FOREIGN KEY(code_contract, code_contract_employee) REFERENCES Contract (code) )"; // code_contract_employee??
		String createJobTable = "CREATE TABLE Job ( code int NOT NULL AUTO_INCREMENT, name VARCHAR(20) NOT NULL, code_employee VARCHAR(10) NOT NULL, code_contract int NOT NULL, code_contract_employee VARCHAR(10) NOT NULL, PRIMARY KEY(code, code_contract_employee), FOREIGN KEY(code_employee, code_contract) REFERENCES Employee (code) )"; // code_contract_employee??
		String createSaleTable = "CREATE TABLE Sale ( code int NOT NULL AUTO_INCREMENT, code_order int NOT NULL, code_client int NOT NULL, cost double NOT NULL, order boolean NOT NULL, PRIMARY KEY(code), FOREIGN KEY(code_order, code_client) REFERENCES Order (code) )";
		String createOrderTable = "CREATE TABLE Order ( code int NOT NULL, phone_number VARCHAR(10) NOT NULL, address VARCHAR(20) NOT NULL, code_employee VARCHAR(10) NOT NULL, PRIMARY KEY(code), FOREIGN KEY(code_employee) REFERENCES Employee (code) )";

		createTable(createClientTable);
		createTable(createEmployeeTable);
		createTable(createPhoneNumerTable);
		createTable(createServiceTable);
		createTable(createServiceInvoice);
		createTable(createProviderTable);
		createTable(createProductTable);
		createTable(createInventoryTable);
		createTable(createContractTable);
		createTable(createCotractTypeTable);
		createTable(createJobTable);
		createTable(createSaleTable);
		createTable(createOrderTable);
	}
}
