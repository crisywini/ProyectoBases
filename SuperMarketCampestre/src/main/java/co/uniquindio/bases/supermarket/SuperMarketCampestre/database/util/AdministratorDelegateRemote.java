package co.uniquindio.bases.supermarket.SuperMarketCampestre.database.util;

import java.sql.Connection;
import java.util.List;

import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.exceptions.EntityRepeatedException;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.exceptions.NonexistentEntityException;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.Contract;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.Employee;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.Product;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.Provider;

public interface AdministratorDelegateRemote {
	void addClient(String name, String lastName);

	void addContract(double salary, String startDate, String endDate, int code_type, String code_employee, int code_job)
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

	void addClientService(int code_client, int code_service, Connection connection);

	
	
	boolean removeContract(int code) throws NonexistentEntityException;

	boolean removeEmployee(String code) throws NonexistentEntityException;

	boolean removeProduct(int code) throws NonexistentEntityException;

	boolean removeProvider(int code) throws NonexistentEntityException;
	Employee loggin(String email, String id)throws NonexistentEntityException;

	List<Contract> getAllContracts();
	List<Employee> getAllEmployees();
	List<Product> getAllProducts();
	List<Provider> getAllProviders();
	
	
	List<Product> getProductsWithTheHighestPrice();
	List<Provider> getProvidersGmail();
}
