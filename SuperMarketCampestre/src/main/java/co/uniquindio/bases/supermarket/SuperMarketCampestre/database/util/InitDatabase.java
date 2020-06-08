package co.uniquindio.bases.supermarket.SuperMarketCampestre.database.util;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.exceptions.EntityRepeatedException;

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
	private static AdministratorDelegate a;

	public static void main(String[] args) {
		createDatabase();
		createAllTables();
		a = new AdministratorDelegate();
		addClients();
		addServices();
		addClientsServices();
		addInvoicesServices();
		addJobs();
		addEmployees();
		addPhoneNumbers();
		addInventories();
		addEmployeeInventories();
		addProviders();
		addProducts();

		addProductsProviders();
		addProductsInventories();
		addContractsTypes();
		addContracts();
		addPaymentsTypes();
		addOrders();
		addSales();
		addProductsSales();

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

		createTable(
				"CREATE TABLE Cliente(code int NOT NULL AUTO_INCREMENT,  nombre VARCHAR(80), apellido VARCHAR(80), PRIMARY KEY (code));");
		createTable(
				"CREATE TABLE Servicio(code int NOT NULL AUTO_INCREMENT, detalle VARCHAR(255), nombre VARCHAR(255), PRIMARY KEY (code));");
		createTable(
				"CREATE TABLE Cliente_servicio(code int NOT NULL AUTO_INCREMENT,cliente_code int NOT NULL, servicio_code int NOT NULL, PRIMARY KEY(code, cliente_code, servicio_code),CONSTRAINT fk_service FOREIGN KEY(servicio_code) REFERENCES Servicio(code) ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT fk_cliente FOREIGN KEY(cliente_code)  REFERENCES Cliente(code) ON UPDATE CASCADE ON DELETE CASCADE);");// Al
		createTable(
				"CREATE TABLE Factura_servicio(code int NOT NULL AUTO_INCREMENT, direccion VARCHAR(255), fecha DATE, descripcion VARCHAR(255), cantidad int, totalPago DECIMAL, devuelta DECIMAL, code_servicio int NOT NULL, PRIMARY KEY(code), CONSTRAINT fk_service_Factura FOREIGN KEY(code_servicio) REFERENCES Servicio(code) ON UPDATE CASCADE ON DELETE CASCADE);");
		createTable(
				"CREATE TABLE Cargo(code int NOT NULL AUTO_INCREMENT, nombre VARCHAR(255) NOT NULL, PRIMARY KEY(code))");
		createTable(
				"CREATE TABLE Empleado(cedula VARCHAR(10) NOT NULL, nombre VARCHAR(255) NOT NULL, apellido VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL UNIQUE, direccion VARCHAR(255) NOT NULL, code_cargo int NOT NULL,PRIMARY KEY(cedula), CONSTRAINT fk_cargo_actual FOREIGN KEY(code_cargo) REFERENCES Cargo(code) ON UPDATE CASCADE ON DELETE CASCADE);");
		createTable(
				"CREATE TABLE Telefono(numero BIGINT NOT NULL, empleado_cedula VARCHAR(10) NOT NULL, PRIMARY KEY(numero, empleado_cedula), CONSTRAINT fk_employee FOREIGN KEY(empleado_cedula) REFERENCES Empleado(cedula) ON UPDATE CASCADE ON DELETE CASCADE);");
		createTable("CREATE TABLE Inventario(code int NOT NULL AUTO_INCREMENT, fecha DATE, PRIMARY KEY(code));");
		createTable(
				"CREATE TABLE Empleado_inventario(empleado_cedula VARCHAR(10) NOT NULL, inventario_code int NOT NULL, PRIMARY KEY(empleado_cedula, inventario_code), CONSTRAINT fk_ei FOREIGN KEY(empleado_cedula) REFERENCES Empleado(cedula) ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT fk_ii FOREIGN KEY(inventario_code) REFERENCES Inventario(code) ON UPDATE CASCADE ON DELETE CASCADE);");
		createTable(
				"CREATE TABLE Proveedor(code int NOT NULL AUTO_INCREMENT, email VARCHAR(255) NOT NULL UNIQUE, nombre VARCHAR(255) NOT NULL, direccion VARCHAR(255) NOT NULL, telefono VARCHAR(40) NOT NULL, PRIMARY KEY(code) );");
		createTable(
				"CREATE TABLE Producto(code int NOT NULL AUTO_INCREMENT, cantidad int NOT NULL, nombre VARCHAR(40) NOT NULL, detalle VARCHAR(255) NOT NULL, precio DECIMAL NOT NULL, PRIMARY KEY(code) );");
		createTable(
				"CREATE TABLE Producto_proveedor(code int NOT NULL AUTO_INCREMENT,code_proveedor int NOT NULL, code_producto int NOT NULL,  PRIMARY KEY(code,code_producto, code_proveedor), CONSTRAINT fk_producto FOREIGN KEY(code_producto) REFERENCES Producto(code) ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT fk_proveedor FOREIGN KEY(code_proveedor) REFERENCES Proveedor(code) ON UPDATE CASCADE ON DELETE CASCADE);");
		createTable(
				"CREATE TABLE Producto_inventario(code int NOT NULL AUTO_INCREMENT, code_producto int NOT NULL, code_inventario int NOT NULL, cantidad int NOT NULL, PRIMARY KEY(code,code_producto, code_inventario),CONSTRAINT fk_pi_inventario FOREIGN KEY (code_inventario) REFERENCES Inventario(code) ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT fk_pi_producto FOREIGN KEY (code_producto) REFERENCES Producto(code) ON UPDATE CASCADE ON DELETE CASCADE);");
		createTable(
				"CREATE TABLE TipoContrato(code int NOT NULL AUTO_INCREMENT, descripcion VARCHAR(255) NOT NULL, nombre VARCHAR(255) NOT NULL, PRIMARY KEY(code));");
		createTable(
				"CREATE TABLE Contrato(code int NOT NULL AUTO_INCREMENT, sueldo DECIMAL, fechaInicio DATE, fechaFin DATE, code_tipo int NOT NULL, cedula_empleado VARCHAR(10) NOT NULL, code_cargo int NOT NULL,PRIMARY KEY(code, cedula_empleado), CONSTRAINT fk_empleado FOREIGN KEY(cedula_empleado) REFERENCES Empleado(cedula) ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT fk_tipo FOREIGN KEY(code_tipo) REFERENCES TipoContrato(code) ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT fk_cargo FOREIGN KEY(code_cargo) REFERENCES Cargo(code) ON UPDATE CASCADE ON DELETE CASCADE);");
		createTable(
				"CREATE TABLE TipoPago(code int NOT NULL AUTO_INCREMENT, nombre VARCHAR(10) NOT NULL, descripcion VARCHAR(255) NOT NULL, PRIMARY KEY(code));");
		createTable(
				"CREATE TABLE Domicilio(code int NOT NULL AUTO_INCREMENT, direccion VARCHAR(255) NOT NULL, cedula_empleado VARCHAR(10) NOT NULL, PRIMARY KEY(code), CONSTRAINT fk_domiciliario FOREIGN KEY(cedula_empleado) REFERENCES Empleado(cedula) ON UPDATE CASCADE ON DELETE CASCADE);");
		createTable(
				"CREATE TABLE Venta(code int NOT NULL AUTO_INCREMENT, costo_total DECIMAL NOT NULL, code_cliente int NOT NULL, code_domicilio int, PRIMARY KEY(code), CONSTRAINT fk_cliente_code FOREIGN KEY(code_cliente) REFERENCES Cliente(code) ON UPDATE CASCADE ON DELETE CASCADE,CONSTRAINT fk_domicilio FOREIGN KEY(code_domicilio) REFERENCES Domicilio(code) ON UPDATE CASCADE ON DELETE CASCADE);");
		createTable(
				"CREATE TABLE Producto_venta(code_producto int NOT NULL, code_venta int NOT NULL, PRIMARY KEY(code_venta, code_producto), CONSTRAINT fk_code_producto FOREIGN KEY(code_producto) REFERENCES Producto(code) ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT fk_code_venta FOREIGN KEY(code_venta) REFERENCES Venta(code) ON UPDATE CASCADE ON DELETE CASCADE);");
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
		 * +Si.... los datos del domicilio son los mismos del cliente... ahora si
		 * organizan rutas para los empleados podrían tener una tabla similar a
		 * domicilio pero sin dirección ni telefono.
		 */
	}

	public static void addClients() {

		a.addClient("Carlos", "Martinez");
		a.addClient("Juan", "Arroyave");
		a.addClient("Carmen", "Gonzales");
		a.addClient("Gerardo", "Gutierrez");
		a.addClient("Gonzalo", "Castano");
		a.addClient("Juliana", "Vargas");
		a.addClient("Sofia", "Munoz");
		a.addClient("Carlos", "Aguirre");
		a.addClient("Alba", "Gonzales");
		a.addClient("Andres", "Giraldo");
		a.addClient("Marisol", "Dias");
		a.addClient("Lucero", "Maldonado");
		a.addClient("Jorge", "Garcia");
		a.addClient("Maria", "Fonseca");
		a.addClient("Cristina", "Banol");
		a.addClient("Luis", "Rodriguez");
		a.addClient("Antonio", "Camargo");
		a.addClient("Felipe", "Bedoya");
		a.addClient("Camilo", "Ramirez");
		a.addClient("Amparo", "Naranjo");
		a.addClient("Dora", "Fernandez");
		a.addClient("Marisol", "Dias");
		a.addClient("Lucero", "Maldonado");
		a.addClient("Jorge", "Garcia");
		a.addClient("Maria", "Fonseca");
		a.addClient("Cristina", "Banol");
		a.addClient("Luis", "Rodriguez");
		a.addClient("Antonio", "Camargo");
		a.addClient("Felipe", "Bedoya");
		a.addClient("Camilo", "Ramirez");
		a.addClient("Amparo", "Naranjo");
		a.addClient("Dora", "Fernandez");
	}

	public static void addServices() {
		try {
			a.addService("Recarga", "Recarga");
			a.addService("Pago de recibos", "Pago de recibos");
			a.addService("Bancolombia a la mano", "Bancolombia a la mano");

		} catch (EntityRepeatedException e) {
			e.printStackTrace();
		}
	}

	public static void addClientsServices() {

		for (int i = 1; i < 8; i++) {
			a.addClientService(i, 1);
		}
		for (int i = 8; i < 16; i++) {
			a.addClientService(i, 2);
		}
		for (int i = 16; i < 21; i++) {
			a.addClientService(i, 2);
		}
		for (int i = 21; i < 31; i++) {
			a.addClientService(i, 3);
		}
	}

	public static void addInvoicesServices() {
		for (int i = 1; i < 31; i++) {
			a.addInvoiceService("Supermarket Campestre", "2020-06-01", "Descripcion" + i, 2, 5000, 500, 1);
		}
	}

	public static void addJobs() {
		try {
			a.addJob("Administrador");
			a.addJob("Auxiliar de caja");
			a.addJob("Domicilio");
		} catch (EntityRepeatedException e) {
			e.printStackTrace();
		}
	}

	public static void addEmployees() {
		try {
			a.addEmployee("e1", "Rodolfo", "Moran", "rm@gmail.com", "Casa a", 1);
			a.addEmployee("e2", "Andres", "Castrillon", "ac@hotmail.com", "Casa b", 2);
			a.addEmployee("e3", "Marta", "Marin", "mm@gmail.com", "Casa c", 3);
			a.addEmployee("e4", "Cecilia", "Grajales", "cg@hotmail.com", "Casa d", 1);
			a.addEmployee("e5", "Sandra", "Castano", "sc@gmail.com", "Casa e", 2);
			a.addEmployee("e6", "Mauricio", "Saavedra", "ms@hotmail.com", "Casa f", 3);
			a.addEmployee("e7", "Ana", "Pulgarin", "ap@gmail.com", "Casa g", 1);
			a.addEmployee("e8", "Camila", "Zapata", "cz@hotmail.com", "Casa h", 2);
			a.addEmployee("e9", "Lola", "Guzman", "lg@gmail.com", "Casa i", 3);
			a.addEmployee("e10", "Julian", "Lopez", "jl@gmail.com", "Casa j", 3);
			a.addEmployee("e11", "Carlos", "Martinez", "cm@gmail.com", "Casa a", 1);
			a.addEmployee("e12", "Marleni", "Gutierrez", "mg@hotmail.com", "Casa b", 2);
			a.addEmployee("e13", "Fernando", "Carbajal", "fc@gmail.com", "Casa c", 3);
			a.addEmployee("e14", "Eduardo", "Jaramillo", "ej@hotmail.com", "Casa d", 1);
			a.addEmployee("e15", "Ana", "Castano", "ac@gmail.com", "Casa e", 2);
			a.addEmployee("e16", "Amanda", "Higuita", "ah@hotmail.com", "Casa f", 3);
			a.addEmployee("e17", "Carmen", "Pineda", "cp@gmail.com", "Casa g", 1);
			a.addEmployee("e18", "Nicolas", "Pena", "np@hotmail.com", "Casa h", 2);
			a.addEmployee("e19", "Manuel", "Hernandez", "mh@gmail.com", "Casa i", 3);
			a.addEmployee("e20", "Paula", "Torrez", "pt22@gmail.com", "Casa j", 3);
			a.addEmployee("e21", "Julian", "Lopez", "jl22@gmail.com", "Casa j", 3);
			a.addEmployee("e22", "Carlos", "Martinez", "cm2@gmail.com", "Casa a", 1);
			a.addEmployee("e23", "Marleni", "Gutierrez", "mg2@hotmail.com", "Casa b", 2);
			a.addEmployee("e24", "Camilo", "Jaramillo", "ej2@hotmail.com", "Casa d", 1);
			a.addEmployee("e25", "Mario", "Arango", "ac2@gmail.com", "Casa e", 2);
			a.addEmployee("e26", "Sergio", "Higuita", "ah2@hotmail.com", "Casa f", 3);
			a.addEmployee("e27", "Rosa", "Pineda", "cp2@gmail.com", "Casa g", 1);
			a.addEmployee("e28", "Clarita", "Rendon", "np2@hotmail.com", "Casa h", 2);
			a.addEmployee("e29", "Lucia", "Hernandez", "mh2@gmail.com", "Casa i", 3);
			a.addEmployee("e30", "Miguel", "Torrez", "pt2@gmail.com", "Casa j", 3);

		} catch (EntityRepeatedException e) {
			e.printStackTrace();
		}
	}

	public static void addPhoneNumbers() {
		for (int i = 1; i < 31; i++) {
			try {
				a.addPhoneNumber(7370000 + i, "e" + i);
			} catch (EntityRepeatedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void addInventories() {

		a.addInventory("2018-11-01");
		a.addInventory("2018-12-01");
		a.addInventory("2019-01-01");
		a.addInventory("2019-02-01");
		a.addInventory("2019-03-01");
		a.addInventory("2019-04-01");
		a.addInventory("2019-05-01");
		a.addInventory("2019-06-01");
		a.addInventory("2019-07-01");
		a.addInventory("2019-08-01");
		a.addInventory("2019-09-01");
		a.addInventory("2019-10-01");
		a.addInventory("2019-11-01");
		a.addInventory("2019-12-01");
		a.addInventory("2020-01-01");
		a.addInventory("2020-02-01");
		a.addInventory("2020-03-01");
		a.addInventory("2020-04-01");
		a.addInventory("2020-05-01");
		a.addInventory("2020-06-01");
	}

	public static void addEmployeeInventories() {

		for (int i = 1; i < 21; i++) {

			a.addEmployeeInventory("e"+i, i);

		}
	}

	public static void addProviders() {
		try {
			a.addProvider("colanta@hotmail.com", "Colanta", "Fabrica industrial de Medellín", "764594");
			a.addProvider("colombina@gmail.com", "Colombina", "Fabrica industrial de Medellín", "7645949");
			a.addProvider("huevosoro@gmail.com", "Huevos Oro", "Finca de Huevos Oro", "3146567798");
			a.addProvider("Zenú@hotmail.com", "Zenú", "Fabrica industrial de Medellín", "76459498");
			a.addProvider("aguilacerve@hotmail.com", "Cerveza Águila", "Licorera de Medellín", "7364594");
			a.addProvider("oreo@oreomail.com", "Oreo", "Fabrica de Oreos", "3145646546");
			a.addProvider("ArrozDiana@hotmail.com", "Arroz Diana", "Fabrica industrial de Medellín", "7364594");
			a.addProvider("alpina@hotmail.com", "Alpina", "Fabrica industrial de Arequipe", "732235");
			a.addProvider("nescafe@gmail.com", "Nescafe", "Fabrica industrial de Café", "308073494");
			a.addProvider("Grisly@hotmail.com", "Grisly", "Fabrica industrial de Pereira", "7393594");
			a.addProvider("hit@hotmail.com", "Hit", "Fabrica industrial de jugos", "3229922019");
			a.addProvider("granos@hotmail.com", "Granos del Café", "Fabrica industrial de granos", "3229922019");

		} catch (EntityRepeatedException e) {
			e.printStackTrace();
		}
	}

	public static void addProducts() {
		try {
			a.addProduct(20, "Leche Descremada", "Leche descremada de colanta", 3600);
			a.addProduct(20, "Leche Deslactosada", "Bolsa de Leche deslactosada de Parmalat 35gm", 2600);
			a.addProduct(50, "Huevos de oro", "Huevos de la finca del oro 12 huevos", 8000);
			a.addProduct(40, "Arroz Diana", "Arroz diana de 25mg", 1200);
			a.addProduct(30, "Salchicas zenú", "Paquete de 6 Salchicas Zenú", 7500);
			a.addProduct(50, "Galletas integrales", "Galletas duz integrales 6 unidades", 4500);
			a.addProduct(20, "Galletas saltinas", "Galletas saltinas pack 12", 4500);
			a.addProduct(20, "Galletas Oreo", "Paquete de Galletas Oreo de 6", 1000);
			a.addProduct(20, "Galletas minioreo", "Paquete de galletas minioreo", 2500);
			a.addProduct(20, "Galletas Oreo", "Paquete de galletas Oreo de Vainilla de 4", 700);
			a.addProduct(20, "Cerveza Club Colombia negra", "Pack 6 cervezas", 15000);
			a.addProduct(20, "Cerveza Aguila", "Pack 6 Cervezas", 12500);
			a.addProduct(20, "Queso", "Queso doble crema colanta", 5800);
			a.addProduct(20, "Jamón", "Jamón de animal muerto", 3600);
			a.addProduct(50, "Arequipe", "Arequipe Alpina x2", 10000);
			a.addProduct(40, "Frijol", "Frijol rojo", 2800);
			a.addProduct(30, "Lenteja", "Lentejas la finquita", 2500);
			a.addProduct(50, "Gomitas", "Gomas Grisly por 12 unidades", 2000);
			a.addProduct(20, "Café", "Cafe Nercafé triturado", 7200);
			a.addProduct(20, "Mariz Pira", "Maiz pira Dona sol", 3500);
			a.addProduct(20, "Jugo hit", "Jugo hit sabor salpicón", 5000);

		} catch (EntityRepeatedException e) {
			e.printStackTrace();
		}
	}


	public static void addProductsProviders() {

		a.addProductProvider(1, 1);
		a.addProductProvider(1, 2);
		a.addProductProvider(1, 11);
		a.addProductProvider(2, 6);
		a.addProductProvider(2, 7);
		a.addProductProvider(2, 9);
		a.addProductProvider(2, 10);
		a.addProductProvider(3, 3);
		a.addProductProvider(4, 5);
		a.addProductProvider(4, 14);
		a.addProductProvider(5, 11);
		a.addProductProvider(5, 12);
		a.addProductProvider(6, 8);
		a.addProductProvider(7, 4);
		a.addProductProvider(8, 15);
		a.addProductProvider(9, 19);
		a.addProductProvider(10, 18);
		a.addProductProvider(11, 21);
		a.addProductProvider(12, 8);
		a.addProductProvider(12, 16);
		a.addProductProvider(12, 17);
		a.addProductProvider(12, 20);

	}

	public static void addProductsInventories() {

		for (int i = 1; i < 21; i++) {
			a.addProductInventory(i, i, 20);
		}

	}

	public static void addContractsTypes() {

		try {
			a.addContractType("Con fecha de terminación no definida", "Indefinido");
			a.addContractType("Con fecha de terminación definida", "Definido");
		} catch (EntityRepeatedException e) {
			e.printStackTrace();
		}
	}

	public static void addContracts() {

		try {
			a.addContract(1000000, "2018-06-03", "2020-06-03", 1, "e1", 1);
			a.addContract(1000000, "2018-06-03", "2020-06-03", 1, "e4", 1);
			a.addContract(1000000, "2018-06-03", "2020-06-03", 1, "e7", 1);
			a.addContract(1000000, "2018-06-03", "2020-06-03", 1, "e11", 1);
			a.addContract(1000000, "2018-06-03", "2020-06-03", 1, "e14", 1);
			a.addContract(1000000, "2018-06-03", "2020-06-03", 1, "e17", 1);
			a.addContract(1000000, "2018-06-03", "2020-06-03", 1, "e22", 1);
			a.addContract(1000000, "2018-06-03", "2020-06-03", 1, "e23", 1);
			a.addContract(1000000, "2018-06-03", "2020-06-03", 1, "e26", 1);

		} catch (EntityRepeatedException e) {

			e.printStackTrace();
		}
	}

	public static void addPaymentsTypes() {

		try {
			a.addPaymentType("Tarjeta", "Pago con tarjeta");
			a.addPaymentType("Efectivo", "Pago en efectivo");
		} catch (EntityRepeatedException e) {
			e.printStackTrace();
		}
	}

	public static void addOrders() {
		a.addOrder("Santa María del Pinar", "e2");
		a.addOrder("Santa María del Pinar", "e5");
		a.addOrder("Santa María del Pinar", "e8");
		a.addOrder("Cano Cristales", "e12");
		a.addOrder("Cano Cristales", "e15");
		a.addOrder("Palo de agua", "e18");
		a.addOrder("Palo de agua", "e23");
		a.addOrder("Baru", "e25");
		a.addOrder("Baru", "e28");
		a.addOrder("Baru", "e8");
		a.addOrder("Baru", "e5");
		a.addOrder("Puerta de agua", "e25");

	}

	public static void addSales() {
		for (int i = 1; i < 13; i++) {

			a.addSale(50000, i, i);
		}
	}

	public static void addProductsSales() {
		for (int i = 1; i < 13; i++) {
			a.addProductSale(i, i);
		}

	}

}
