package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;


public class PaymentType {

	private int code;
	private String name;
	private String description;

	public PaymentType() {

	}

	public PaymentType(int code, String name, String description) {
		this.code = code;
		this.name = name.toUpperCase();
		this.description = description;

	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	@Override
	public String toString() {
		return name;
	}

	public void saveType(String name, String description, Connection connection) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO TipoPago(nombre, descripcion) VALUES(?,?)");
			statement.setString(1, name);
			statement.setString(2, description);

			statement.executeUpdate();
			System.out.println("Se ha guardado el tipo de pago: " + name + " " + description);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
