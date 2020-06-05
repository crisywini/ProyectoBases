package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Employee extends Conexion {

	private String code;
	private String name;
	private String last_name;
	private String email;
	private String address;
	private int code_job;

	public Employee() {

	}
	
	public Employee(String code, String name, String last_name, String email, String address, int code_job) {

		this.code = code;
		this.name = name;
		this.last_name = last_name;
		this.email = email;
		this.address = address;
		this.code_job = code_job;
		
		saveEmployee(code, name, last_name, email, address, code_job);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCode_job() {
		return code_job;
	}

	public void setCode_job(int code_job) {
		this.code_job = code_job;
	}

	private void saveEmployee(String code, String name, String last_name, String email, String address, int code_job) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO Empleado(cedula, nombre, apellido, email, direccion, code_cargo) values(?,?,?,?,?,?);");
			statement.setString(1, code);
			statement.setString(2, name);
			statement.setString(3, last_name);
			statement.setString(4, email);
			statement.setString(5, address);
			statement.setInt(6, code_job);

			statement.executeUpdate();
			System.out.println("Se ha guardado el cliente: " + code + " " + name + " " + last_name + " " + email + " "
					+ address + " " + code);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
