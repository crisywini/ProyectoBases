package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Contract {

	private int code;
	private double salary;
	private String startDate;
	private String endDate;
	private int code_type;
	private String code_employee;
	private int code_job;

	public Contract() {

	}

	public Contract(int code, double salary, String startDate, String endDate, int code_type, String code_employee,
			int code_job) {
		this.code = code;
		this.salary = salary;
		this.startDate = startDate;
		this.endDate = endDate;
		this.code_type = code_type;
		this.code_employee = code_employee;
		this.code_job = code_job;

	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getCode_type() {
		return code_type;
	}

	public void setCode_type(int code_type) {
		this.code_type = code_type;
	}

	public String getCode_employee() {
		return code_employee;
	}

	public void setCode_employee(String code_employee) {
		this.code_employee = code_employee;
	}

	public int getCode_job() {
		return code_job;
	}

	public void setCode_job(int code_job) {
		this.code_job = code_job;
	}

	@Override
	public String toString() {
		return "Contract [code=" + code + ", salary=" + salary + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", code_type=" + code_type + ", code_employee=" + code_employee + ", code_job=" + code_job + "]";
	}

	public void saveContract(double salary, String startDate, String endDate, int code_type, String code_employee,
			int code_job, Connection connection) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO Contrato(sueldo, fechaInicio, fechaFin, code_tipo, cedula_empleado, code_cargo) VALUES(?,?,?,?,?,?)");

			Date startDateAux = Date.valueOf(startDate);
			Date endDateAux = Date.valueOf(endDate);
			statement.setDouble(1, salary);
			statement.setDate(2, startDateAux);
			statement.setDate(3, endDateAux);
			statement.setInt(4, code_type);
			statement.setString(5, code_employee);
			statement.setInt(6, code_job);

			statement.executeUpdate();
			System.out.println("Se ha guardado el contrato: " + salary + " " + startDate + " " + endDate + " "
					+ code_type + " " + code_employee + " " + code_job);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void saveContract(double salary, String startDate, int code_type, String code_employee, int code_job,
			Connection connection) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO Contrato(sueldo, fechaInicio, code_tipo, cedula_empleado, code_cargo) VALUES(?,?,?,?,?)");

			Date startDateAux = Date.valueOf(startDate);
			statement.setDouble(1, salary);
			statement.setDate(2, startDateAux);
			statement.setInt(3, code_type);
			statement.setString(4, code_employee);
			statement.setInt(5, code_job);

			statement.executeUpdate();
			System.out.println("Se ha guardado el contrato: " + salary + " " + startDate + " " + endDate + " "
					+ code_type + " " + code_employee + " " + code_job);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
