package co.uniquindio.bases.supermarket.SuperMarketCampestre.database.util;

import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.exceptions.EntityRepeatedException;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.*;

public class AdministratorDelegate extends Conexion implements AdministratorDelegateRemote{

	
	@Override
	public void addClient(String name, String lastName) throws EntityRepeatedException {
		Client newClient = new Client();
		newClient.saveClient(name, lastName, connection);
	}

	@Override
	public void addContract(double salary, String startDate, String endDate, int code_type, String code_employee,
			int code_job) throws EntityRepeatedException {
		Contract newContract = new Contract();
		newContract.saveContract(salary, startDate, endDate, code_type, code_employee, code_job, connection);
	}

	@Override
	public void addContractType(String description, String name) throws EntityRepeatedException {
		ContractType newContractType = new ContractType();
		newContractType.saveType(description, name, connection);
	}

	@Override
	public void addEmployee(String code, String name, String last_name, String email, String address, int code_job)
			throws EntityRepeatedException {
		Employee newEmployee = new Employee();
		newEmployee.saveEmployee(code, name, last_name, email, address, code_job, connection);
	}

	@Override
	public void addEmployeeInventory(String code_employee, int code_inventory) throws EntityRepeatedException {
		Employee_Inventory newEmployee_Inventory = new Employee_Inventory();
		newEmployee_Inventory.saveEmployeeInventory(code_employee, code_inventory, connection);
	}

	@Override
	public void addInventory(String date) throws EntityRepeatedException {
		Inventory newInventory = new Inventory();
		newInventory.saveInventory(date, connection);
	}

	@Override
	public void addInvoiceService(String address, String date, String description, int quantity, double total_payment,
			double exchange, int code_service) throws EntityRepeatedException {
		Invoice_Service newInvoice_Service = new Invoice_Service();
		newInvoice_Service.saveInvoice_Service(address, date, description, quantity, total_payment, exchange, code_service, connection);
	}

	@Override
	public void addJob(String name) throws EntityRepeatedException {
		Job newJob = new Job();
		newJob.saveJob(name, connection);
		
	}

	@Override
	public void addOrder(String address, String code_employee) throws EntityRepeatedException {
		Order newOrder = new Order();
		newOrder.saveOrder(address, code_employee, connection);
	}

	@Override
	public void addPaymentType(String name, String description) throws EntityRepeatedException {
		PaymentType newPaymentType = new PaymentType();
		newPaymentType.saveType(name, description, connection);
	}

	@Override
	public void addPhoneNumber(int number, String code_employee) throws EntityRepeatedException {
		PhoneNumber newPhoneNumber = new PhoneNumber();
		newPhoneNumber.savePhone(number, code_employee, connection);
	}

	@Override
	public void addProductInventory(int code_product, int code_inventory, int quantity) throws EntityRepeatedException {
		Product_Inventory newInventory = new Product_Inventory();
		newInventory.saveProductInventory(code_product, code_inventory, quantity, connection);
	}

	@Override
	public void addProductProvider(int code_provider, int code_product) throws EntityRepeatedException {
		Product_provider newProduct_provider = new Product_provider();
		newProduct_provider.saveProductProvider(code_provider, code_product, connection);
	}

	@Override
	public void addProductSale(int code_product, int code_sale) throws EntityRepeatedException {
		Product_Sale newProduct_Sale = new Product_Sale();
		newProduct_Sale.saveProductSale(code_product, code_sale, connection);
	}

	@Override
	public void addProduct(int quantity, String name, String details, double price, int code_proveedor)
			throws EntityRepeatedException {
		Product newProduct = new Product();
		newProduct.saveProduct(quantity, name, details, price, code_proveedor, connection);
	}

	@Override
	public void addProvider(String email, String name, String address, String phone) throws EntityRepeatedException {
		Provider newProvider = new Provider();
		newProvider.saveProvider(email, name, address, phone, connection);
	}

	@Override
	public void addSale(double total_payment, int code_client, int code_order) throws EntityRepeatedException {
		Sale newSale = new Sale();
		newSale.saveSale(total_payment, code_client, code_order, connection);
	}

	@Override
	public void addService(String detail, String name) throws EntityRepeatedException {
		Service newService = new Service();
		newService.saveService(detail, name, connection);
	}

}
