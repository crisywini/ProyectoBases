package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;


public class Product_provider {

	private int code_provider;
	private int code_product;

	public Product_provider() {

	}

	public Product_provider(int code_product, int code_provider) {

		this.code_product = code_product;
		this.code_provider = code_provider;
		
	}

	public int getCode_product() {
		return code_product;
	}

	public void setCode_product(int code_product) {
		this.code_product = code_product;
	}

	public int getCode_provider() {
		return code_provider;
	}

	public void setCode_provider(int code_provider) {
		this.code_provider = code_provider;
	}

	public void saveProductProvider(int code_provider, int code_product, Connection connection) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO Producto_proveedor(code_producto, code_proveedor) values(?,?);");
			statement.setInt(1, code_provider);
			statement.setInt(2, code_provider);

			statement.executeUpdate();
			System.out.println("Se ha guardado el cliente: " + code_provider + " " + code_product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
