package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Product_Sale extends Conexion {

	private int code_product;
	private int code_sale;

	public Product_Sale() {

	}

	public Product_Sale(int code_product, int code_sale) {

		this.code_product = code_product;
		this.code_sale = code_sale;

		saveProductSale(code_product, code_sale);
	}

	public int getCode_product() {
		return code_product;
	}

	public void setCode_product(int code_product) {
		this.code_product = code_product;
	}

	public int getCode_sale() {
		return code_sale;
	}

	public void setCode_venta(int code_sale) {
		this.code_sale = code_sale;
	}

	private void saveProductSale(int code_product, int code_sale) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO Producto_venta(code_producto, code_venta) values(?,?);");
			statement.setInt(1, code_product);
			statement.setInt(2, code_sale);

			statement.executeUpdate();
			System.out.println("Se ha guardado el producto_venta: " + code_product + " " + code_sale);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
