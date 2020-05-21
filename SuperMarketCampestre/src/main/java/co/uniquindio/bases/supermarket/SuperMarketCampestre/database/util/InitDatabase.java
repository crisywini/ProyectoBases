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
		String createClientTable = "CREATE TABLE Client ( code int NOT NULL AUTO_INCREMENT, code_service_invoice int NOT NULL, name VARCHAR(20), last_name VARCHAR(10), address VARCHAR(40), phone_number VARCHAR(10), PRIMARY KEY (code), FOREIGN KEY(code_service_invooice) REFERENCES Service_invoice (code) );";
		String createEmployeeTable = "CREATE TABLE Employee ( code VARCHAR(10) NOT NULL, code_job int NOT NULL, name VARCHAR(20) NOT NULL, last_name VARCHAR(20) NOT NULL, email VARCHAR(40) NOT NULL UNIQUE, address VARCHAR(50) NOT NULL, PRIMARY KEY (code), FOREIGN KEY(code_job) REFERENCES Job (code) );";
		String createPhoneNumerTable = "CREATE TABLE Phone_number (number VARCHAR(10) NOT NULL, code_employee VARCHAR(10) NOT NULL, PRIMARY KEY(number, code_employee), FOREIGN KEY(code_employee) REFERENCES Employee (code) );";
		String createServiceTable = "CREATE TABLE Service ( code int NOT NULL AUTO_INCREMENT, details VARCHAR(40) NOT NULL, name VARCHAR(40) NOT NULL, PRIMARY KEY(code) );";

		String createServiceInvoiceTable = "CREATE TABLE Invoice_service ( code int NOT NULL AUTO_INCREMENT, code_service int NOT NULL, address VARCHAR(20) NOT NULL, date VARVHAR(20) NOT NULL, description VARCHAR(40), total_payment VARCHAR(20) NOT NULL, PRIMARY KEY (code), FOREIGN KEY(code_service) REFERENCES Service (code) )"; // cantidad,devuelta??_pago_varchar_double?
		String createProviderTable = "CREATE TABLE Provier ( code int NOT NULL AUTO_INCREMENT, name VARCHAR(20) NOT NULL, address VARCHAR(20) NOT NULL, email VARCHAR(20), phone_number VARCHAR(10), PRIMARY KEY (code) )"; // code_auto??
		String createProductTable = "CREATE TABLE Product ( code int NOT NULL AUTO_INCREMENT, code_provider int NOT NULL, name VARCHAR(20) NOT NULL, cantidad int NOT NULL, detalles VARCHAR(40), PRIMARY KEY(code), FOREIGN KEY(code_provider) REFERENCES Provider (code) )";
		String createInventoryTable = "CREATE TABLE Inventory ( code int NOT NULL AUTO_INCREMENT, date VARCHAR(20) NOT NULL, PRIMARY KEY(code) )";
		String createContractTable = "CREATE TABLE Contract ( code int NOT NULL AUTO_INCREMENT, code_employee int NOT NULL, code_contract_type int NOT NULL, salary DOUBLE NOT NULL, start_date VARCHAR(20) NOT NULL, end_date VARCHAR(20) NOT NULL, PRIMARY KEY (code, code_employee), FOREIGN KEY(code_employee) REFERENCES Employee (code), FOREIGN KEY(code_contract_type) REFERENCES Contract_type (code) )"; // salario_double?
		String createCotractTypeTable = "CREATE TABLE Contract_type ( code int NOT NULL AUTO_INCREMENT, name VARCHAR(20) NOT NULL, desciption VARCHAT(40), code_contract int NOT NULL, PRIMARY KEY (code), FOREIGN KEY(code_contract) REFERENCES Contract (code) )";
		String createJobTable = "CREATE TABLE Job ( code int NOT NULL AUTO_INCREMENT, name VARCHAR(20) NOT NULL, code_employee VARCHAR(10) NOT NULL, code_contract int NOT NULL, PRIMARY KEY(code), FOREIGN KEY(code_employee) REFERENCES Employee (code), FOREIGN KEY(code_contract) REFERENCES Contract (code) )";
		String createSaleTable = "CREATE TABLE Sale ( code int NOT NULL AUTO_INCREMENT, code_order int NOT NULL, code_client int NOT NULL, code_payment_type int NOT NULL, cost double NOT NULL, order boolean NOT NULL, PRIMARY KEY(code), FOREIGN KEY(code_order) REFERENCES Order (code), FOREIGN KEY(code_client) REFERENCES Client (code) )"; // cost_double?
		String createOrderTable = "CREATE TABLE Order ( code int NOT NULL, code_employee VARCHAR(10) NOT NULL, PRIMARY KEY(code), FOREIGN KEY(code_employee) REFERENCES Employee (code) )";
		String createPaymentTypeTable = "CREATE TABLE Payment_type ( code int NOT NULL, code_sale int NOT NULL, name VARCHAR(20) NOT NULL, description VARCHAR(20), PRIMARY KEY(code, code_sale), FOREIGN KEY(code_sale) REFERENCES Sale (code) )";
		String createEmployeeInventaryTable = "CREATE TABLE Employee_inventary ( code_employee VARCHAR(10) NOT NULL, code_inventary int NOT NULL, PRIMARY KEY(code_employee, code_inventary), FOREIGN KEY(code_employee) REFERENCES Employee (code), FOREIGN KEY(code_inventary) REFERENCES Inventary (code), FOREIGN KEY(code_payment_type) REFERENCES Payment_type (code) )";
		String createProductInventaryTable = "CREATE TABLE Product_inventari ( code_product int NOT NULL, code_inventary int NOT NULL, quantity int NOT NULL, FOREIGN KEY(code_product) REFERENCES Product (code), FOREIGN KEY(code_inventary) REFERENCES Inventary (code) )";
		String createProductSaleTable = "CREATE TABLE Product_sale ( code_product int NOT NULL, code_sale int NOT NULL, quantity int NOT NULL, FOREIGN KEY(code_product) REFERENCES Product (code), FOREIGN KEY(code_sale) REFERENCES Sale (code) )";
		String createClientServiceTable = "CREATE TABLE Client_service ( code_client int NOT NULL, code_service int NOT NULL, FOREIGN KEY(code_client) REFERENCES Client (code), FOREIGN KEY(code_service) REFERENCES Service (code) )";
		String createProductProviderTable = "CREATE TABLE Product_provider ( code_product int NOT NULL, code_provider int NOT NULL, PRIMARY KEY(code_product, code_provider), FOREIGN KEY(code_product) REFERENCES Product (code), FOREIGN KEY(code_provider) REFERENCES Provider (code) )";

		createTable(createClientTable);
		createTable(createEmployeeTable);
		createTable(createPhoneNumerTable);
		createTable(createServiceTable);
		createTable(createServiceInvoiceTable);
		createTable(createProviderTable);
		createTable(createProductTable);
		createTable(createInventoryTable);
		createTable(createContractTable);
		createTable(createCotractTypeTable);
		createTable(createJobTable);
		createTable(createSaleTable);
		createTable(createOrderTable);
		createTable(createPaymentTypeTable);

		createTable(createEmployeeInventaryTable);
		createTable(createProductInventaryTable);
		createTable(createProductSaleTable);
		createTable(createClientServiceTable);
		createTable(createProductProviderTable);

		/**
		 * + La relación entre venta y pago es de 1 a N (un medio de pago puede estar en
		 * varias ventas) ?????
		 * 
		 * 
		 * + Revisar las relaciones entre cliente, cliente-servicio, servicio y
		 * factura-servicio. El patron de este tipo de tablas sería: Un cliente tiene
		 * varias Factura-Servicio, una factura servicio tiene detalle servicio y un
		 * servicio puede estar en varios detalles de servicio. ????
		 * 
		 * 
		 * + cargo_actual es un grupo repetido + A empleado-Inventario y
		 * producto-Inventario le hace falta la llave primaria + Es posible que un
		 * producto sea suminitrado por varios proveedores, de esta forma faltaría una
		 * tabla intermedia + En producto-venta falta el atributo cantidad + La relación
		 * contrato y tipoContrato es al contrario es decir un tipo de contrato puede
		 * estar en varios contratos. Lo mismo que cargo, en un cargo pueden haber
		 * varios empleados + La tabla Domicilio es como la ruta que sea crea (supongo),
		 * no entiendo porque tiene el atributo dirección y teléfono?
		 */
	}
}
