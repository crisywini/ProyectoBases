package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

public class Invoice_Service extends Conexion {

	private int code;
	private String address;
	private String date;
	private String description;
	private int quantity;
	private double total_payment;
	private double exchange;
	private int code_service;

	public Invoice_Service() {

	}

	public Invoice_Service(String address, String date, String description, int quantity, double total_payment,
			double exchange, int code_service) {

		this.address = address;
		this.date = date;
		this.description = description;
		this.quantity = quantity;
		this.total_payment = total_payment;
		this.exchange = exchange;
		this.code_service = code_service;

		saveInvoice_Service(address, this.date, description, quantity, total_payment, exchange, code_service);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotal_payment() {
		return total_payment;
	}

	public void setTotal_payment(double total_payment) {
		this.total_payment = total_payment;
	}

	public double getExchange() {
		return exchange;
	}

	public void setExchange(double exchange) {
		this.exchange = exchange;
	}

	public int getCode_service() {
		return code_service;
	}

	public void setCode_service(int code_service) {
		this.code_service = code_service;
	}

	private void saveInvoice_Service(String address, String date, String description, int quantity, double total_payment,
			double exchange, int code_service) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO Factura_servicio(direccion, fecha, descripcion, cantidad, totalPago, devuelta, code_servicio) values(?,?,?,?,?,?,?);");
			statement.setString(1, address);
			statement.setDate(2, Date.valueOf(date));
			statement.setString(3, description);
			statement.setInt(4, quantity);
			statement.setDouble(5, total_payment);
			statement.setDouble(6, exchange);
			statement.setInt(7, code_service);

			statement.executeUpdate();
			System.out.println("Se ha guardado la factura_servicio: " + address + " " + date + " " + description + " "
					+ quantity + " " + total_payment + " " + exchange + " " + code_service);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
