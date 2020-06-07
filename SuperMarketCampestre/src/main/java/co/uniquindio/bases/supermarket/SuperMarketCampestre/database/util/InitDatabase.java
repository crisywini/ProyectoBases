package co.uniquindio.bases.supermarket.SuperMarketCampestre.database.util;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.*;

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
		
//		Client client = new Client("Juan", "Salazar");
//		Service service = new Service("Detalles del servicio recarga", "Recarga");
//		Client_Service clientService = new Client_Service(1, 1);
//		Invoice_Service invoService = new Invoice_Service("Por la 19", "2020-06-03", "Descripcion", 2, 5000, 500, 1);
//		Job job = new Job("Auxiliar de Caja");		
//		Employee e = new Employee("01", "Carlos", "Rojas", "cr@gmail.com", "casa", 1);
//		PhoneNumber phone = new PhoneNumber(7471234, "01");
//		Inventory inventory = new Inventory("2020-06-03");
//		Employee_Inventory emploInventory = new Employee_Inventory("01", 1);
//		Provider provider = new Provider("pro@gmail.com", "Proveedor1", "Fabrica1", "7971919");
//		Product product = new Product(2, "Papas", "Papas fritas", 3000, 1);
//		Product_provider pp = new Product_provider(1, 1);
//		Product_Inventory productInventory = new Product_Inventory(1, 1, 2);
//		ContractType contractType = new ContractType("a 6 meses", "Definido");
//		Contract contract = new Contract(900000, "2018-06-03", "2020-06-03", 1, "01", 1);
//		PaymentType paymentType = new PaymentType("Efectivo", "Pago en efectivo");
//		Order order = new Order("Santa Maria del Pinar", "01");
//		Sale sale = new Sale(5000, 1, 1);
//		Product_Sale productSale = new Product_Sale(1, 1);

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
	/**
	 * This method allows to create all the needed tables
	 */
	public static void createAllTables() {

		createTable("CREATE TABLE Cliente(code int NOT NULL AUTO_INCREMENT,  nombre VARCHAR(80), apellido VARCHAR(80), PRIMARY KEY (code));");
		createTable("CREATE TABLE Servicio(code int NOT NULL AUTO_INCREMENT, detalle VARCHAR(255), nombre VARCHAR(255), PRIMARY KEY (code));");
		createTable("CREATE TABLE Cliente_servicio(code int NOT NULL AUTO_INCREMENT,cliente_code int NOT NULL, servicio_code int NOT NULL, PRIMARY KEY(code, cliente_code, servicio_code),CONSTRAINT fk_service FOREIGN KEY(servicio_code) REFERENCES Servicio(code) ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT fk_cliente FOREIGN KEY(cliente_code)  REFERENCES Cliente(code) ON UPDATE CASCADE ON DELETE CASCADE);");//Al momento de cambiar un atributo en llave foranea de la tabla a la cual pertenece, se cambia en todas las referencias en las que esté
		createTable("CREATE TABLE Factura_servicio(code int NOT NULL AUTO_INCREMENT, direccion VARCHAR(255), fecha DATE, descripcion VARCHAR(255), cantidad int, totalPago DECIMAL, devuelta DECIMAL, code_servicio int NOT NULL, PRIMARY KEY(code), CONSTRAINT fk_service_Factura FOREIGN KEY(code_servicio) REFERENCES Servicio(code) ON UPDATE CASCADE ON DELETE CASCADE);");
		createTable("CREATE TABLE Cargo(code int NOT NULL AUTO_INCREMENT, nombre VARCHAR(255) NOT NULL, PRIMARY KEY(code))");
		createTable("CREATE TABLE Empleado(cedula VARCHAR(10) NOT NULL, nombre VARCHAR(255) NOT NULL, apellido VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL UNIQUE, direccion VARCHAR(255) NOT NULL, code_cargo int NOT NULL,PRIMARY KEY(cedula), CONSTRAINT fk_cargo_actual FOREIGN KEY(code_cargo) REFERENCES Cargo(code) ON UPDATE CASCADE ON DELETE CASCADE);");
		createTable("CREATE TABLE Telefono(numero BIGINT NOT NULL, empleado_cedula VARCHAR(10) NOT NULL, PRIMARY KEY(numero, empleado_cedula), CONSTRAINT fk_employee FOREIGN KEY(empleado_cedula) REFERENCES Empleado(cedula) ON UPDATE CASCADE ON DELETE CASCADE);");
		createTable("CREATE TABLE Inventario(code int NOT NULL AUTO_INCREMENT, fecha DATE, PRIMARY KEY(code));");
		createTable("CREATE TABLE Empleado_inventario(empleado_cedula VARCHAR(10) NOT NULL, inventario_code int NOT NULL, PRIMARY KEY(empleado_cedula, inventario_code), CONSTRAINT fk_ei FOREIGN KEY(empleado_cedula) REFERENCES Empleado(cedula) ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT fk_ii FOREIGN KEY(inventario_code) REFERENCES Inventario(code) ON UPDATE CASCADE ON DELETE CASCADE);");
		createTable("CREATE TABLE Proveedor(code int NOT NULL AUTO_INCREMENT, email VARCHAR(255) NOT NULL UNIQUE, nombre VARCHAR(255) NOT NULL, direccion VARCHAR(255) NOT NULL, telefono VARCHAR(40) NOT NULL, PRIMARY KEY(code) );");
		createTable("CREATE TABLE Producto(code int NOT NULL AUTO_INCREMENT, cantidad int NOT NULL, nombre VARCHAR(40) NOT NULL, detalle VARCHAR(255) NOT NULL, precio DECIMAL NOT NULL, PRIMARY KEY(code) );");
		createTable("CREATE TABLE Producto_proveedor(code int NOT NULL AUTO_INCREMENT,code_proveedor int NOT NULL, code_producto int NOT NULL,  PRIMARY KEY(code,code_producto, code_proveedor), CONSTRAINT fk_producto FOREIGN KEY(code_producto) REFERENCES Producto(code) ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT fk_proveedor FOREIGN KEY(code_proveedor) REFERENCES Proveedor(code) ON UPDATE CASCADE ON DELETE CASCADE);");
		createTable("CREATE TABLE Producto_inventario(code int NOT NULL AUTO_INCREMENT, code_producto int NOT NULL, code_inventario int NOT NULL, cantidad int NOT NULL, PRIMARY KEY(code,code_producto, code_inventario),CONSTRAINT fk_pi_inventario FOREIGN KEY (code_inventario) REFERENCES Inventario(code) ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT fk_pi_producto FOREIGN KEY (code_producto) REFERENCES Producto(code) ON UPDATE CASCADE ON DELETE CASCADE);");
		createTable("CREATE TABLE TipoContrato(code int NOT NULL AUTO_INCREMENT, descripcion VARCHAR(255) NOT NULL, nombre VARCHAR(255) NOT NULL, PRIMARY KEY(code));");
		createTable("CREATE TABLE Contrato(code int NOT NULL AUTO_INCREMENT, sueldo DECIMAL, fechaInicio DATE, fechaFin DATE, code_tipo int NOT NULL, cedula_empleado VARCHAR(10) NOT NULL, code_cargo int NOT NULL,PRIMARY KEY(code, cedula_empleado), CONSTRAINT fk_empleado FOREIGN KEY(cedula_empleado) REFERENCES Empleado(cedula) ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT fk_tipo FOREIGN KEY(code_tipo) REFERENCES TipoContrato(code) ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT fk_cargo FOREIGN KEY(code_cargo) REFERENCES Cargo(code) ON UPDATE CASCADE ON DELETE CASCADE);");
		createTable("CREATE TABLE TipoPago(code int NOT NULL AUTO_INCREMENT, nombre VARCHAR(10) NOT NULL, descripcion VARCHAR(255) NOT NULL, PRIMARY KEY(code));");
		createTable("CREATE TABLE Domicilio(code int NOT NULL AUTO_INCREMENT, direccion VARCHAR(255) NOT NULL, cedula_empleado VARCHAR(10) NOT NULL, PRIMARY KEY(code), CONSTRAINT fk_domiciliario FOREIGN KEY(cedula_empleado) REFERENCES Empleado(cedula) ON UPDATE CASCADE ON DELETE CASCADE);");
		createTable("CREATE TABLE Venta(code int NOT NULL AUTO_INCREMENT, costo_total DECIMAL NOT NULL, code_cliente int NOT NULL, code_domicilio int NOT NULL, PRIMARY KEY(code), CONSTRAINT fk_cliente_code FOREIGN KEY(code_cliente) REFERENCES Cliente(code) ON UPDATE CASCADE ON DELETE CASCADE,CONSTRAINT fk_domicilio FOREIGN KEY(code_domicilio) REFERENCES Domicilio(code) ON UPDATE CASCADE ON DELETE CASCADE);");
		createTable("CREATE TABLE Producto_venta(code_producto int NOT NULL, code_venta int NOT NULL, PRIMARY KEY(code_venta, code_producto), CONSTRAINT fk_code_producto FOREIGN KEY(code_producto) REFERENCES Producto(code) ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT fk_code_venta FOREIGN KEY(code_venta) REFERENCES Venta(code) ON UPDATE CASCADE ON DELETE CASCADE);");
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
		 * 
		 * +Si.... los datos del domicilio son los mismos del cliente...
		 *  ahora si organizan rutas para los empleados podrían tener 
		 *  una tabla similar a domicilio pero sin dirección ni telefono.
		 */
	}
}
