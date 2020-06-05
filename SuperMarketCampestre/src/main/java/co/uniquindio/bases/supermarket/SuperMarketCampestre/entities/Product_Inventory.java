package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Product_Inventory extends Conexion {

	private int code_product;
	private int code_inventory;
	private int quantity;

	public Product_Inventory() {

	}

	public Product_Inventory(int code_product, int code_inventory, int quantity) {

		this.code_product = code_product;
		this.code_inventory = code_inventory;
		this.quantity = quantity;

		saveProductInventory(code_product, code_inventory, quantity);
	}

	public int getCode_product() {
		return code_product;
	}

	public void setCode_product(int code_product) {
		this.code_product = code_product;
	}

	public int getCode_inventory() {
		return code_inventory;
	}

	public void setCode_inventory(int code_inventory) {
		this.code_inventory = code_inventory;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	private void saveProductInventory(int code_product, int code_inventory, int quantity) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO Producto_inventario(code_producto, code_inventario, cantidad) values(?,?,?);");
			statement.setInt(1, code_product);
			statement.setInt(2, code_inventory);
			statement.setInt(3, quantity);

			statement.executeUpdate();
			System.out.println(
					"Se ha guardado el procuto_inventario: " + code_product + " " + code_inventory + " " + quantity);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
