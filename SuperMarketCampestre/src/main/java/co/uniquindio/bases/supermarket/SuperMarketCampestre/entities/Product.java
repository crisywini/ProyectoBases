package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Product extends Conexion {
	private int code;
	private int quantity;
	private String name;
	private String details;
	private double price;
	private int code_provider;

	public Product() {

	}
	
	public Product(int quantity, String name, String details, double price, int code_provider) {

		this.quantity = quantity;
		this.name = name;
		this.details = details;
		this.price = price;
		this.code_provider = code_provider;

		saveProduct(quantity, name, details, price, code_provider);
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

	public int getCode_provider() {
		return code_provider;
	}

	public void setCode_provider(int code_provider) {
		this.code_provider = code_provider;
	}

	private void saveProduct(int quantity, String name, String details, double price, int code_proveedor) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO Producto(cantidad, nombre, detalles, precio, code_proveedor) values(?,?,?,?,?);");
			statement.setInt(1, quantity);
			statement.setString(2, name);
			statement.setString(3, details);
			statement.setDouble(4, price);
			statement.setInt(5, code_proveedor);

			statement.executeUpdate();
			System.out.println("Se ha guardado el Producto: " + quantity + " " + name + " " + details + " " + price
					+ " " + code_proveedor);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
