package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;


public class PhoneNumber {

	private long number;
	private String code_employee;

	public PhoneNumber() {

	}

	public PhoneNumber(int number, String code_employee) {

		this.number = number;
		this.code_employee = code_employee;

	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public String getCode_employee() {
		return code_employee;
	}

	public void setCode_employee(String code_employee) {
		this.code_employee = code_employee;
	}

	public  void savePhone(long number, String code_employee, Connection connection) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO Telefono(numero, empleado_cedula) values(?,?);");
			statement.setLong(1, number);
			statement.setString(2, code_employee);
			statement.executeUpdate();
			System.out.println("Se ha guardado el telefono: " + number + " " + code_employee);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
