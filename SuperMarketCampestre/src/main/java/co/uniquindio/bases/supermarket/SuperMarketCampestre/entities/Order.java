package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Order extends Conexion {

	public int code;
	public String address;
	public String code_employee;

	public Order() {

	}

	public Order(String address, String code_employee) {

		this.address = address;
		this.code_employee = code_employee;

		saveOrder(address, code_employee);
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

	public String getCode_employee() {
		return code_employee;
	}

	public void setCode_employee(String code_employee) {
		this.code_employee = code_employee;
	}

	private void saveOrder(String address, String code_employee) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO Domicilio(direccion, cedula_empleado) values(?,?);");
			statement.setString(1, address);
			statement.setString(2, code_employee);
			statement.executeUpdate();
			System.out.println("Se ha guardado el domicilio: " + address + " " + code_employee);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
