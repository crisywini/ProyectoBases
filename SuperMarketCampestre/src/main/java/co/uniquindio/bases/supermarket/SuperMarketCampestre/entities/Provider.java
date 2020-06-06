package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;


public class Provider {

	private int code;
	private String email;
	private String name;
	private String address;
	private String phone;

	public Provider() {
	
	}
	
	public Provider(String email, String name, String address, String phone) {

		this.email = email;
		this.name = name;
		this.address = address;
		this.phone = phone;

	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void saveProvider(String email, String name, String address, String phone, Connection connection) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO Proveedor(email, nombre, direccion, telefono) values(?,?,?,?);");
			statement.setString(1, email);
			statement.setString(2, name);
			statement.setString(3, address);
			statement.setString(4, phone);

			statement.executeUpdate();
			System.out.println("Se ha guardado el proveedor: " + email + " " + name + " " + address + " " + phone);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
