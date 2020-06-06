package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Inventory {

	private int code;
	private String date;

	public Inventory(String date) {
		this.date = date;
	}

	public Inventory() {

	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void saveInventory(String date, Connection connection) {
		try {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO Inventario(fecha) VALUES(?)");
			statement.setDate(1, Date.valueOf(date));
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
