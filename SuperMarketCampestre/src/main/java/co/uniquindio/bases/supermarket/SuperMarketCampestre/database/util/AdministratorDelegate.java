package co.uniquindio.bases.supermarket.SuperMarketCampestre.database.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.exceptions.EntityRepeatedException;
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
	public void addProduct(int quantity, String name, String details, double price) {
		Product newProduct = new Product();
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

}