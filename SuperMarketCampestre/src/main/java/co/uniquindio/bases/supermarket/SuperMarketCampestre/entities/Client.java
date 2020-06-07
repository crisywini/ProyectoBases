package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Client{
	
	public int code;
	public String name;
	public String lastName;

	public Client() {

	}

	public Client(int code, String name, String lastName) {
		this.code = code;
		this.name = name;
		this.lastName = lastName;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
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
		Client other = (Client) obj;
		if (code != other.code)
			return false;
		return true;
	}

	public void saveClient(String name, String lastName, Connection connection) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO Cliente(nombre, apellido) values(?,?);");
			statement.setString(1, name);
			statement.setString(2, lastName);
			
			statement.executeUpdate();
			System.out.println("Se ha guardado el cliente: " + name + " " + lastName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
