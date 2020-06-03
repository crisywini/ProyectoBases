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
	private Connection connection;

	public Contract(int code, double salary, String startDate, String endDate, Connection con) {

		this.code = code;
		this.salary = salary;
		this.startDate = startDate;
		this.endDate = endDate;
		connection = con;
	}

	public Contract() {

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		long temp;
		temp = Double.doubleToLongBits(salary);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contract other = (Contract) obj;
		if (code != other.code)
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.salary))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contract [code=" + code + ", salary=" + salary + ", startDate=" + startDate + ", endDate=" + endDate
				+ "]";
	}
	/**
	 * 
	 * @param salary
	 * @param startDate
	 * @param endDate
	 */
	public void saveContract(double salary, String startDate, String endDate, int codeContractType, String idEmployee, String codeJob) {
		try {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO Contrato(sueldo, fechaInicio, fechaFin, code_tipo, cedula_empleado, code_cargo) VALUES(?,?,?,?,?,?)");
			Date startDateAux = Date.valueOf(startDate);
			Date endDateAux = Date.valueOf(endDate);
			statement.setDouble(1, salary);
			statement.setDate(2, startDateAux);
			statement.setDate(3, endDateAux);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
