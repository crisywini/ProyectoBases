package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PhoneNumber extends Conexion{
	private int number;
	private String idEmployee;
	
	public PhoneNumber(int number, String idEmployee) {
		this.number = number;
		this.idEmployee = idEmployee;
		savePhoneNumber(this.number, this.idEmployee);
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getIdEmployee() {
		return idEmployee;
	}
	public void setIdEmployee(String idEmployee) {
		this.idEmployee = idEmployee;
	}
	
	private void savePhoneNumber(int number, String idEmployee) {
		try {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO Telefono(numero, empleado_cedula) VALUES(?,?);");
			statement.setInt(1, number);
			statement.setString(2, idEmployee);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
