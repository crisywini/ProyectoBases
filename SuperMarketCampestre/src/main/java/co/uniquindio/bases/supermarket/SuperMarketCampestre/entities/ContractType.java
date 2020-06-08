package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContractType {

	private int code;
	private String description;
	private String name;

	public ContractType() {

	}

	public ContractType(int code, String description, String name) {
		this.code = code;
		this.description = description;
		this.name = name.toUpperCase();

	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	@Override
	public String toString() {
		return  name ;
	}

	/**
	 * 
	 * @param description
	 * @param name
	 */
	public void saveType(String description, String name, Connection connection) {

		try {
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO TipoContrato(descripcion, nombre) VALUES(?,?)");
			statement.setString(1, description);
			statement.setString(2, name);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
