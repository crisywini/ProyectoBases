package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;


public class Job {

	public int code;
	public String name;

	public Job() {

	}

	public Job(String name) {

		this.name = name.toUpperCase();
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

	public void saveJob(String name, Connection connection) {
		try {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO Cargo(nombre) VALUES(?);");
			statement.setString(1, name);

			statement.executeUpdate();
			System.out.println("Se ha guardado el cargo: " + name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
