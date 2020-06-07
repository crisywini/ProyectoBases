package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

public class Product {
	private int code;
	private int quantity;
	private String name;
	private String details;
	private double price;

	public Product() {

	}

	public Product(int code ,int quantity, String name, String details, double price) {
		this.code = code;
		this.quantity = quantity;
		this.name = name;
		this.details = details;
		this.price = price;

	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	


	@Override
	public String toString() {
		return "Product [code=" + code + ", quantity=" + quantity + ", name=" + name + ", details=" + details
				+ ", price=" + price + "]";
	}

	public void saveProduct(int quantity, String name, String details, double price, Connection connection) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO Producto(cantidad, nombre, detalle, precio) values(?,?,?,?);");
			statement.setInt(1, quantity);
			statement.setString(2, name);
			statement.setString(3, details);
			statement.setDouble(4, price);

			statement.executeUpdate();
			System.out.println("Se ha guardado el Producto: " + quantity + " " + name + " " + details + " " + price);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
