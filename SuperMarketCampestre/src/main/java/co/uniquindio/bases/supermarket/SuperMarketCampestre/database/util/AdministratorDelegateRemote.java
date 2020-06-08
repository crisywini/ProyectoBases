package co.uniquindio.bases.supermarket.SuperMarketCampestre.database.util;


import java.util.List;

import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.exceptions.EntityRepeatedException;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.exceptions.NonexistentEntityException;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.Client;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.Contract;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.ContractType;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.Employee;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.Job;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.Order;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.PaymentType;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.Product;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.Provider;

public interface AdministratorDelegateRemote {
	void addClient(String name, String lastName);

	void addContract(double salary, String startDate, String endDate, int code_type, String code_employee, int code_job)
			throws EntityRepeatedException;
	void addContract2(double salary, String startDate, int code_type, String code_employee, int code_job)
			throws EntityRepeatedException;
	void addContractType(String description, String name) throws EntityRepeatedException;

	void addEmployee(String code, String name, String last_name, String email, String address, int code_job)
			throws EntityRepeatedException;

	void addEmployeeInventory(String code_employee, int code_inventory);

	void addInventory(String date);

	void addInvoiceService(String address, String date, String description, int quantity, double total_payment,
			double exchange, int code_service);

	void addJob(String name) throws EntityRepeatedException;

	void addOrder(String address, String code_employee);

	void addPaymentType(String name, String description) throws EntityRepeatedException;

	void addPhoneNumber(long number, String code_employee) throws EntityRepeatedException;

	void addProductInventory(int code_product, int code_inventory, int quantity);

	void addProductProvider(int code_provider, int code_product);

	void addProductSale(int code_product, int code_sale);

	void addProduct(int quantity, String name, String details, double price) throws EntityRepeatedException;

	void addProvider(String email, String name, String address, String phone) throws EntityRepeatedException;

	void addSale(double total_payment, int code_client, int code_order);

	void addService(String detail, String name) throws EntityRepeatedException;

	void addClientService(int code_client, int code_service);

	
	
	boolean removeContract(int code) throws NonexistentEntityException;

	boolean removeEmployee(String code) throws NonexistentEntityException;

	boolean removeProduct(int code) throws NonexistentEntityException;

	boolean removeProvider(int code) throws NonexistentEntityException;
	Employee loggin(String email, String id)throws NonexistentEntityException;
	Product getProduct(int code) throws NonexistentEntityException;
	Order getOrder(String address, String code) throws NonexistentEntityException;
	Client getClient(String name, String lastName) throws NonexistentEntityException;
	Product getProduct(String name, String details) throws NonexistentEntityException;
	Provider getProvider(String email) throws NonexistentEntityException;
	Contract getContract(String startDate, String endDate, String code_employee, int code_job) throws NonexistentEntityException;

	List<Contract> getAllContracts();
	List<Employee> getAllEmployees();
	List<Product> getAllProducts();
	List<Provider> getAllProviders();
	
	List<ContractType> getAllContractTypes();
	List<PaymentType> getAllPaymentType();
	List<Job> getAllJobs();
	void updateProduct(Product product);
	void updatePrivider(Provider provider);
	void updateEmployee(Employee employee);
	void updateContract(Contract contract);
	
	List<Product> getProductsWithTheHighestPrice();
	List<Provider> getProvidersGmail();

	List<Client> getClientNameDesc();
	
//	nombre de los cargos con la cantidad de empleados que estan en él
	List<Job> getNameJobQuantityEmployees();
//	Cantidad de domicilios que se han hecho a una direccion
	List<Order> getQuantityOrderByAddress();
//	direccion de los 5 domicilios mas caros
	List<Order> get5OrderByCost();
//	Cuantos contratos tienen el mismo tipo de cargo (muestra el cargo)
	List<Job> getContractByJob();
//	nombre del empleado que más ventas realizo
	List<Employee> getEmployeeBySale();
//	Nombre de los 3 empleados que tienen más números de telefono
	List<Employee> getEmployeeByPhoneNumber();
//	Nombre del numero de compras que realizan los clientes con su nombre
	List<Order> getQuantityOrderByEmployee();
	
	
}
