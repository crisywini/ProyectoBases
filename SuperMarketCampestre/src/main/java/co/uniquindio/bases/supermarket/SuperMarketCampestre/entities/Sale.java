package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;


public class Sale {

	private int code;
	private double total_payment;
	private int code_client;
	private int code_order;

	public Sale() {
	
	}
	
	public Sale(double total_payment, int code_client, int code_order) {

		this.total_payment = total_payment;
		this.code_client = code_client;
		this.code_order = code_order;

	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public double getTotal_payment() {
		return total_payment;
	}

	public void setTotal_payment(double total_payment) {
		this.total_payment = total_payment;
	}

	public int getCode_client() {
		return code_client;
	}

	public void setCode_client(int code_client) {
		this.code_client = code_client;
	}

	public int getCode_order() {
		return code_order;
	}

	public void setCode_order(int code_order) {
		this.code_order = code_order;
	}

	public void saveSale(double total_payment, int code_client, int code_order, Connection connection) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO Venta(costo_total, code_cliente, code_domicilio) values(?,?,?);");
			statement.setDouble(1, total_payment);
			statement.setInt(2, code_client);
			statement.setInt(3, code_order);

			statement.executeUpdate();
			System.out.println("Se ha guardado la venta: " + total_payment + " " + code_client + " " + code_order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
