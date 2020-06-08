package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Employee_Inventory {

	private String code_employee;
	private int code_inventory;

	public Employee_Inventory() {

	}

	public Employee_Inventory(String code_employee, int code_inventory) {

		this.code_employee = code_employee;
		this.code_inventory = code_inventory;

	}

	public String getCode_employee() {
		return code_employee;
	}

	public void setCode_employee(String code_employee) {
		this.code_employee = code_employee;
	}

	public int getCode_inventory() {
		return code_inventory;
	}

	public void setCode_inventory(int code_inventory) {
		this.code_inventory = code_inventory;
	}

	@Override
	public String toString() {
		return "Employee_Inventory [code_employee=" + code_employee + ", code_inventory=" + code_inventory + "]";
	}

	public void saveEmployeeInventory(String code_employee, int code_inventory, Connection connection) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO Empleado_inventario(empleado_cedula, inventario_code) values(?,?);");
			statement.setString(1, code_employee);
			statement.setInt(2, code_inventory);

			statement.executeUpdate();
			System.out.println("Se ha guardado el empleado_inventario: " + code_employee + " " + code_inventory);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
