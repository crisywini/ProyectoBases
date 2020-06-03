package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Job extends Conexion{

	public int code;
	public String name;

	public Job(String name) {

		this.name = name.toUpperCase();
		saveJob(this.name);
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
	private void saveJob(String name) {
		try {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO Cargo(nombre) VALUES(?);");
			statement.setString(1, name);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
