package co.uniquindio.bases.supermarket.SuperMarketCampestre.database.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.exceptions.EntityRepeatedException;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.exceptions.NonexistentEntityException;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.*;

public class AdministratorDelegate extends Conexion implements AdministratorDelegateRemote {

	/**
	 * This method allows to create a query that verify the existence or one
	 * Employee
	 * 
	 * @param code  from {@link Employee}
	 * @param email from {@link Employee}
	 * @return true if the {@link Employee} exist or false if it does not
	 */
	public boolean isEmployeeRepeated(String code, String email) {

		try {
			final String SQL = "SELECT * FROM Empleado WHERE cedula = ? OR email = ?;";
			PreparedStatement query = connection.prepareStatement(SQL);
			query.setString(1, code);
			query.setString(2, email);
			ResultSet resultSet = query.executeQuery();
			return resultSet.first();
		} catch (SQLException e) {
			System.err.println(e.getMessage() + "<------(FROM DELEGATE)");
			return false;
		}
	}

	/**
	 * This method allows to verify if a {@link Contract} is repeated by using the
	 * next parameters
	 * 
	 * @param startDate     from the {@link Contract}
	 * @param endDate       from the {@link Contract}
	 * @param code_employee from the {@link Contract}
	 * @param code_job      from the {@link Contract}
	 * @return true if return some {@link Contract} and false if it does not
	 */
	public boolean isContractRepeated(String startDate, String endDate, String code_employee, int code_job) {
		try {
			final String SQL = "SELECT * FROM Contrato WHERE fechaInicio = ? AND fechaFin = ? AND cedula_empleado = ? AND code_cargo = ?;";
			PreparedStatement query = connection.prepareStatement(SQL);
			query.setDate(1, Date.valueOf(startDate));
			query.setDate(2, Date.valueOf(endDate));
			query.setString(3, code_employee);
			query.setInt(4, code_job);
			ResultSet resultSet = query.executeQuery();
			return resultSet.first();
		} catch (SQLException e) {
			System.err.println(e.getMessage() + "<------(FROM DELEGATE)");
			return false;
		}
	}

	/**
	 * This method allows to verify if name of a {@link ContractType} exist
	 * 
	 * @param name of the {@link ContractType}
	 * @return true if the name exist and false if it does not
	 */
	public boolean isContractTypeRepeated(String name) {
		try {
			final String SQL = "SELECT * FROM TipoContrato WHERE nombre = ?;";
			PreparedStatement query = connection.prepareStatement(SQL);
			query.setString(1, name);
			ResultSet resultSet = query.executeQuery();
			return resultSet.first();
		} catch (SQLException e) {
			System.err.println(e.getMessage() + "<------(FROM DELEGATE)");
			return false;
		}
	}

	/**
	 * This method allows to verify if the name of a {@link Job} exist
	 * 
	 * @param name of the {@link Job}
	 * @return true if the {@link Job} exist and false if it does not
	 */
	public boolean isJobRepeated(String name) {
		try {
			final String SQL = "SELECT * FROM Cargo WHERE nombre = ?;";
			PreparedStatement query = connection.prepareStatement(SQL);
			query.setString(1, name);
			ResultSet resultSet = query.executeQuery();
			return resultSet.first();
		} catch (SQLException e) {
			System.err.println(e.getMessage() + "<------(FROM DELEGATE)");
			return false;
		}
	}

	/**
	 * This method allows to verify if the name of the {@link PaymentType} already
	 * exist
	 * 
	 * @param name of the {@link PaymentType}
	 * @return true if it exist or false if it does not
	 */
	public boolean isPaymentTypeRepeated(String name) {
		try {
			final String SQL = "SELECT * FROM TipoPago WHERE nombre = ?;";
			PreparedStatement query = connection.prepareStatement(SQL);
			query.setString(1, name);
			ResultSet resultSet = query.executeQuery();
			return resultSet.first();
		} catch (SQLException e) {
			System.err.println(e.getMessage() + "<------(FROM DELEGATE)");
			return false;
		}
	}

	/**
	 * This method allows to verify if a {@link PhoneNumber} exist
	 * 
	 * @param number        of the {@link PhoneNumber}
	 * @param code_employee of the {@link PhoneNumber}
	 * @return if the {@link PhoneNumber} exist or if it does not
	 */
	public boolean isPhoneNumberRepeated(long number, String code_employee) {
		try {
			final String SQL = "SELECT * FROM Telefono WHERE numero = ? AND empleado_cedula = ? ;";
			PreparedStatement query = connection.prepareStatement(SQL);
			query.setLong(1, number);
			query.setString(2, code_employee);
			ResultSet resultSet = query.executeQuery();
			return resultSet.first();
		} catch (SQLException e) {
			System.err.println(e.getMessage() + "<------(FROM DELEGATE)");
			return false;
		}
	}

	/**
	 * This method allows to verify if the Provider's email exist
	 * 
	 * @param email of the {@link Provider}
	 * @return true if the email exist or false if it does not
	 */
	public boolean isProviderRepeated(String email) {
		try {
			final String SQL = "SELECT * FROM Proveedor WHERE email = ?;";
			PreparedStatement query = connection.prepareStatement(SQL);
			query.setString(1, email);
			ResultSet resultSet = query.executeQuery();
			return resultSet.first();
		} catch (SQLException e) {
			System.err.println(e.getMessage() + "<------(FROM DELEGATE)");
			return false;
		}
	}

	/**
	 * This method allows to verify if the name of a {@link Service} exist
	 * 
	 * @param name of the {@link Service}
	 * @return true if it exist or false if it does not
	 */
	public boolean isServiceRepeated(String name) {
		try {
			final String SQL = "SELECT * FROM Servicio WHERE nombre = ?;";
			PreparedStatement query = connection.prepareStatement(SQL);
			query.setString(1, name);
			ResultSet resultSet = query.executeQuery();
			return resultSet.first();
		} catch (SQLException e) {
			System.err.println(e.getMessage() + "<------(FROM DELEGATE)");
			return false;
		}
	}

	/**
	 * This method allows to verify the name and the details for the {@link Product}
	 * if them exist
	 * 
	 * @param name    of the {@link Product}
	 * @param details of the {@link Product}
	 * @return true if both of them exist and false if does not
	 */
	public boolean isProductRepeated(String name, String details) {
		try {
			final String SQL = "SELECT * FROM Producto WHERE nombre = ? AND detalle = ?;";
			PreparedStatement query = connection.prepareStatement(SQL);
			query.setString(1, name);
			query.setString(2, details);
			ResultSet resultSet = query.executeQuery();
			return resultSet.first();
		} catch (SQLException e) {
			System.err.println(e.getMessage() + "<------(FROM DELEGATE)");
			return false;
		}

	}

	@Override
	public void addClient(String name, String lastName) {
		Client newClient = new Client();
		newClient.saveClient(name, lastName, connection);
	}

	@Override
	public void addContract(double salary, String startDate, String endDate, int code_type, String code_employee,
			int code_job) throws EntityRepeatedException {
		Contract newContract = new Contract();
		if (isContractRepeated(startDate, endDate, code_employee, code_job))
			throw new EntityRepeatedException("El contrato: " + startDate + ", " + endDate + ", " + code_employee + ", "
					+ code_job + ", ya se encuentra registrado");
		newContract.saveContract(salary, startDate, endDate, code_type, code_employee, code_job, connection);
	}

	@Override
	public void addContractType(String description, String name) throws EntityRepeatedException {
		ContractType newContractType = new ContractType();
		if (isContractTypeRepeated(name))
			throw new EntityRepeatedException("El tipo contrato: " + name + " ya se encuentra registrado");
		newContractType.saveType(description, name, connection);
	}

	@Override
	public void addEmployee(String code, String name, String last_name, String email, String address, int code_job)
			throws EntityRepeatedException {
		Employee newEmployee = new Employee();
		if (isEmployeeRepeated(code, email))
			throw new EntityRepeatedException("El empleado: " + code + ", " + email + " ya se encuentra registrado");
		newEmployee.saveEmployee(code, name, last_name, email, address, code_job, connection);
	}

	@Override
	public void addEmployeeInventory(String code_employee, int code_inventory) {
		Employee_Inventory newEmployee_Inventory = new Employee_Inventory();
		newEmployee_Inventory.saveEmployeeInventory(code_employee, code_inventory, connection);
	}

	@Override
	public void addInventory(String date) {
		Inventory newInventory = new Inventory();
		newInventory.saveInventory(date, connection);
	}

	@Override
	public void addInvoiceService(String address, String date, String description, int quantity, double total_payment,
			double exchange, int code_service) {
		Invoice_Service newInvoice_Service = new Invoice_Service();
		newInvoice_Service.saveInvoice_Service(address, date, description, quantity, total_payment, exchange,
				code_service, connection);
	}

	@Override
	public void addJob(String name) throws EntityRepeatedException {
		Job newJob = new Job();
		if (isJobRepeated(name))
			throw new EntityRepeatedException("El cargo: " + name + " ya se encuentra registrado");
		newJob.saveJob(name, connection);
	}

	@Override
	public void addOrder(String address, String code_employee) {
		Order newOrder = new Order();
		newOrder.saveOrder(address, code_employee, connection);
	}

	@Override
	public void addPaymentType(String name, String description) throws EntityRepeatedException {
		PaymentType newPaymentType = new PaymentType();
		if (isPaymentTypeRepeated(name))
			throw new EntityRepeatedException("El tipo pago: " + name + " ya se encuentra registrado");
		newPaymentType.saveType(name, description, connection);
	}

	@Override
	public void addPhoneNumber(long number, String code_employee) throws EntityRepeatedException {
		PhoneNumber newPhoneNumber = new PhoneNumber();
		if (isPhoneNumberRepeated(number, code_employee))
			throw new EntityRepeatedException(
					"El número: " + number + ", " + code_employee + " ya se encuentra registrado.");
		newPhoneNumber.savePhone(number, code_employee, connection);
	}

	@Override
	public void addProductInventory(int code_product, int code_inventory, int quantity) {
		Product_Inventory newInventory = new Product_Inventory();
		newInventory.saveProductInventory(code_product, code_inventory, quantity, connection);
	}

	@Override
	public void addProductProvider(int code_provider, int code_product) {
		Product_provider newProduct_provider = new Product_provider();
		newProduct_provider.saveProductProvider(code_provider, code_product, connection);
	}

	@Override
	public void addProductSale(int code_product, int code_sale) {
		Product_Sale newProduct_Sale = new Product_Sale();
		newProduct_Sale.saveProductSale(code_product, code_sale, connection);
	}

	@Override
	public void addProduct(int quantity, String name, String details, double price) throws EntityRepeatedException {
		Product newProduct = new Product();
		if (isProductRepeated(name.trim(), details.trim()))
			throw new EntityRepeatedException("El producto: " + name + ", " + details + " ya se encuentra registrado");

		newProduct.saveProduct(quantity, name, details, price, connection);
	}

	@Override
	public void addProvider(String email, String name, String address, String phone) throws EntityRepeatedException {
		Provider newProvider = new Provider();
		if (isProviderRepeated(email))
			throw new EntityRepeatedException("El proveedor: " + email + " ya se encuentra registrado");
		newProvider.saveProvider(email, name, address, phone, connection);
	}

	@Override
	public void addSale(double total_payment, int code_client, int code_order) {
		Sale newSale = new Sale();
		newSale.saveSale(total_payment, code_client, code_order, connection);
	}

	@Override
	public void addService(String detail, String name) throws EntityRepeatedException {
		Service newService = new Service();
		if (isServiceRepeated(name))
			throw new EntityRepeatedException("El servicio: " + name + " ya se encuentra registrado");
		newService.saveService(detail, name, connection);
	}

	@Override
	public void addClientService(int code_client, int code_service, Connection connection) {
		Client_Service newClient_Service = new Client_Service();
		newClient_Service.saveClientService(code_client, code_service, connection);
	}

	@Override
	public boolean removeContract(int code) throws NonexistentEntityException {
		if (!isContract(code))
			throw new NonexistentEntityException("El contrato: " + code + " no existe");
		try {
			PreparedStatement query = connection.prepareStatement("DELETE FROM Contrato WHERE code = ?;");
			query.setInt(1, code);
			int rows = query.executeUpdate();
			System.out.println("Filas modificadas: " + rows);
			return rows == 1;
		} catch (SQLException e) {
			System.err.println(e.getMessage() + "<------(FROM DELEGATE)");
		}
		return false;
	}

	/**
	 * This method allows to verify if a {@link Contract} exists by its code
	 * 
	 * @param code from the {@link Contract}
	 * @return true if it exist or false if it does not
	 */
	public boolean isContract(int code) {
		try {
			final String SQL = "SELECT * FROM Contrato WHERE code = ? ;";
			PreparedStatement query = connection.prepareStatement(SQL);
			query.setInt(1, code);
			ResultSet resultSet = query.executeQuery();
			return resultSet.first();
		} catch (SQLException e) {
			System.err.println(e.getMessage() + "<------(FROM DELEGATE)");
			return false;
		}
	}

	@Override
	public boolean removeEmployee(String code) throws NonexistentEntityException {
		if (!isEmployee(code))
			throw new NonexistentEntityException("El empleado: " + code + " no se encuentra registrado");
		try {
			PreparedStatement query = connection.prepareStatement("DELETE FROM Empleado WHERE cedula = ?;");
			query.setString(1, code);
			int rows = query.executeUpdate();
			System.out.println("Filas modificadas: " + rows);
			return rows == 1;
		} catch (SQLException e) {
			System.err.println(e.getMessage() + "<------(FROM DELEGATE)");
		}
		return false;
	}

	/**
	 * This method allows to verify if an {@link Employee} exist by its code
	 * 
	 * @param code from the {@link Employee}
	 * @return true if it exists or false if it does not
	 */
	public boolean isEmployee(String code) {
		try {
			final String SQL = "SELECT * FROM Empleado WHERE cedula = ? ;";
			PreparedStatement query = connection.prepareStatement(SQL);
			query.setString(1, code);
			ResultSet resultSet = query.executeQuery();
			return resultSet.first();
		} catch (SQLException e) {
			System.err.println(e.getMessage() + "<------(FROM DELEGATE)");
			return false;
		}
	}

	@Override
	public boolean removeProduct(int code) throws NonexistentEntityException {
		if (!isProduct(code))
			throw new NonexistentEntityException("El producto: " + code + " no se encuentra registrado");
		try {
			PreparedStatement query = connection.prepareStatement("DELETE FROM Producto WHERE code = ?;");
			query.setInt(1, code);
			int rows = query.executeUpdate();
			System.out.println("Filas modificadas: " + rows);
			return rows == 1;
		} catch (SQLException e) {
			System.err.println(e.getMessage() + "<------(FROM DELEGATE)");
		}
		return false;
	}

	public boolean isProduct(int code) {
		try {
			final String SQL = "SELECT * FROM Producto WHERE code = ? ;";
			PreparedStatement query = connection.prepareStatement(SQL);
			query.setInt(1, code);
			ResultSet resultSet = query.executeQuery();
			return resultSet.first();
		} catch (SQLException e) {
			System.err.println(e.getMessage() + "<------(FROM DELEGATE)");
			return false;
		}

	}

	@Override
	public boolean removeProvider(int code) throws NonexistentEntityException {
		if (!isProvider(code))
			throw new NonexistentEntityException("El proveedor: " + code + " no se encuentra registrado");
		try {
			PreparedStatement query = connection.prepareStatement("DELETE FROM Proveedor WHERE code = ?;");
			query.setInt(1, code);
			int rows = query.executeUpdate();
			System.out.println("Filas modificadas: " + rows);
			return rows == 1;
		} catch (SQLException e) {
			System.err.println(e.getMessage() + "<------(FROM DELEGATE)");
		}
		return false;
	}

	public boolean isProvider(int code) {
		try {
			final String SQL = "SELECT * FROM Proveedor WHERE code = ? ;";
			PreparedStatement query = connection.prepareStatement(SQL);
			query.setInt(1, code);
			ResultSet resultSet = query.executeQuery();
			return resultSet.first();
		} catch (SQLException e) {
			System.err.println(e.getMessage() + "<------(FROM DELEGATE)");
			return false;
		}
	}

	@Override
	public List<Contract> getAllContracts() {
		List<Contract> contractList = new ArrayList<Contract>();
		try {
			final String SQL = "SELECT * FROM Contrato;";
			PreparedStatement query = connection.prepareStatement(SQL);
			ResultSet resultSet = query.executeQuery();
			while (resultSet.next()) {
//				System.out.println(
//						resultSet.getString(1) + ", " + resultSet.getString(2) + ", " + resultSet.getString(3));
				contractList.add(new Contract(resultSet.getInt(1), resultSet.getDouble(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6), resultSet.getInt(7)));
//				System.out.println(contractList);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage() + "<------(FROM DELEGATE)");
		}
		return contractList;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employeesList = new ArrayList<Employee>();
		try {
			final String SQL = "SELECT * FROM Empleado;";
			PreparedStatement query = connection.prepareStatement(SQL);
			ResultSet resultSet = query.executeQuery();
			while (resultSet.next()) {
				employeesList.add(new Employee(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6)));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage() + "<------(FROM DELEGATE)");
		}
		return employeesList;
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> productList = new ArrayList<Product>();
		try {
			final String SQL = "SELECT * FROM Producto;";
			PreparedStatement query = connection.prepareStatement(SQL);
			ResultSet resultSet = query.executeQuery();
			while (resultSet.next()) {
				productList.add(new Product(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getDouble(5)));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage() + "<------(FROM DELEGATE)");
		}
		return productList;
	}

	@Override
	public List<Provider> getAllProviders() {
		List<Provider> providerList = new ArrayList<Provider>();
		try {
			final String SQL = "SELECT * FROM Proveedor;";
			PreparedStatement query = connection.prepareStatement(SQL);
			ResultSet resultSet = query.executeQuery();
			while (resultSet.next()) {
				providerList.add(new Provider(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5)));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage() + "<------(FROM DELEGATE)");
		}
		return providerList;
	}

	@Override
	public List<Product> getProductsWithTheHighestPrice() {
		List<Product> productList = new ArrayList<Product>();
		try {
			final String SQL = "SELECT * FROM Producto ORDER BY precio DESC LIMIT 5;";
			PreparedStatement query = connection.prepareStatement(SQL);
			ResultSet resultSet = query.executeQuery();
			while (resultSet.next()) {
				System.out.println(resultSet.getDouble(5));
				productList.add(new Product(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getDouble(5)));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage() + "<------(FROM DELEGATE)");
		}
		return productList;
	}

	@Override
	public List<Provider> getProvidersGmail() {
		List<Provider> providerList = new ArrayList<Provider>();
		try {
			final String SQL = "SELECT * FROM Proveedor WHERE email like '%@gmail%';";
			PreparedStatement query = connection.prepareStatement(SQL);
			ResultSet resultSet = query.executeQuery();
			while (resultSet.next()) {
				providerList.add(new Provider(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5)));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage() + "<------(FROM DELEGATE)");
		}
		return providerList;
	}

	@Override
	public Employee loggin(String email, String id) throws NonexistentEntityException {
		Employee employee =null;
		if(!isEmployee(id, email))
			throw new NonexistentEntityException("El empleado: "+email+" no se encuentra registrado");
		try {
			final String SQL = "SELECT * FROM Empleado WHERE email = ? AND cedula = ?;";
			PreparedStatement query = connection.prepareStatement(SQL);
			query.setString(1, email);
			query.setString(2, id);
			ResultSet resultSet = query.executeQuery();
			while (resultSet.next()) {
				employee = new Employee(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage() + "<------(FROM DELEGATE)");
		}
		return employee;
	}
	public boolean isEmployee(String code, String email) {
		try {
			final String SQL = "SELECT * FROM Empleado WHERE cedula = ?  AND email = ?;";
			PreparedStatement query = connection.prepareStatement(SQL);
			query.setString(1, code);
			query.setString(2, email);
			ResultSet resultSet = query.executeQuery();
			return resultSet.first();
		} catch (SQLException e) {
			System.err.println(e.getMessage() + "<------(FROM DELEGATE)");
			return false;
		}
	}
	
	
}