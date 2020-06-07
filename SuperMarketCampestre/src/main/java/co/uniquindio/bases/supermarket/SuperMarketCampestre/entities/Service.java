package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;


public class Service {

	private int code;
	private String detail;
	private String name;

	public Service() {

	}

	public Service(String detail, String name) {

		this.detail = detail;
		this.name = name;

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

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void saveService(String detail, String name, Connection connection) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO Servicio(detalle, nombre) values(?,?);");
			statement.setString(1, detail);
			statement.setString(2, name);
			statement.executeUpdate();
			System.out.println("Se ha guardado el servicio: " + detail + " " + name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
