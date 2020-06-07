package co.uniquindio.bases.supermarket.SuperMarketCampestre.database.util;

import java.sql.Connection;

import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.exceptions.EntityRepeatedException;

public interface AdministratorDelegateRemote {
	public void addClient(String name, String lastName);
	public void addContract(double salary, String startDate, String endDate, int code_type, String code_employee,
			int code_job)throws EntityRepeatedException;
	public void addContractType(String description, String name)throws EntityRepeatedException;
	public void addEmployee(String code, String name, String last_name, String email, String address, int code_job)throws EntityRepeatedException;
	public void addEmployeeInventory(String code_employee, int code_inventory);
	public void addInventory(String date);
	public void addInvoiceService(String address, String date, String description, int quantity, double total_payment,
			double exchange, int code_service);
	public void addJob(String name)throws EntityRepeatedException;
	public void addOrder(String address, String code_employee);
	public void addPaymentType(String name, String description)throws EntityRepeatedException;
	public void addPhoneNumber(long number, String code_employee)throws EntityRepeatedException;
	public void addProductInventory(int code_product, int code_inventory, int quantity);
	public void addProductProvider(int code_provider, int code_product);
	public void addProductSale(int code_product, int code_sale)throws EntityRepeatedException;
	public void addProduct(int quantity, String name, String details, double price)throws EntityRepeatedException;
	public void addProvider(String email, String name, String address, String phone)throws EntityRepeatedException;
	public void addSale(double total_payment, int code_client, int code_order);
	public void addService(String detail, String name)throws EntityRepeatedException;
	public void addClientService(int code_client, int code_service, Connection connection);
	
}
