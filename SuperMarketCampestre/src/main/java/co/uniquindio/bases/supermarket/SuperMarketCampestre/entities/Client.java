package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Client {
	private int code;
	private String name;
	private String lastName;
	private Connection connection;

	public Client() {

	}
	public Client(String name, String lastName, Connection con) {
		connection = con;
		this.name = name;
		this.lastName = lastName;
		saveClient(name, lastName);
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


	public Connection getConnection() {
		return connection;
	}



	public void setConnection(Connection connection) {
		this.connection = connection;
	}


	@Override
	public String toString() {
		return "Client [code=" + code + ", name=" + name + ", lastName=" + lastName + 
				 ", connection=" + connection + "]";
	}



	public void saveClient(String name, String lastName) {
		try {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO Cliente(nombre, apellido) values(?,?);");
			statement.setString(1, name);
			statement.setString(2, lastName);
			statement.executeUpdate();
			System.out.println("Se ha guardado el cliente: "+name+" "+lastName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
