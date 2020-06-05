package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PhoneNumber extends Conexion {

	private int number;
	private String code_employee;

	public PhoneNumber() {

	}

	public PhoneNumber(int number, String code_employee) {

		this.number = number;
		this.code_employee = code_employee;

		savePhone(number, code_employee);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getCode_employee() {
		return code_employee;
	}

	public void setCode_employee(String code_employee) {
		this.code_employee = code_employee;
	}

	private void savePhone(int number, String code_employee) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO Telefono(numero, empleado_cedula) values(?,?);");
			statement.setInt(1, number);
			statement.setString(2, code_employee);
			statement.executeUpdate();
			System.out.println("Se ha guardado el telefono: " + number + " " + code_employee);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
