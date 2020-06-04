package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Inventory extends Conexion{

	private int code;
	private String date;

	public Inventory(String date) {
		this.date = date;
		saveInventory(this.date);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		Inventory other = (Inventory) obj;
		if (code != other.code)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Inventory [code=" + code + ", date=" + date + "]";
	}
	private void saveInventory(String date) {
		try {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO Inventario(fecha) VALUES(?)");
			statement.setDate(1, Date.valueOf(date));
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
