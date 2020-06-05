package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Client_Service extends Conexion {

	private int code_client;
	private int code_service;

	public Client_Service() {

	}

	public Client_Service(int code_client, int code_service) {

		this.code_client = code_client;
		this.code_service = code_service;

		saveClientService(this.code_client, this.code_service);
	}

	public int getCode_client() {
		return code_client;
	}

	public void setCode_client(int code_client) {
		this.code_client = code_client;
	}

	public int getCode_service() {
		return code_service;
	}

	public void setCode_service(int code_service) {
		this.code_service = code_service;
	}

	private void saveClientService(int code_client, int code_service) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO Cliente_servicio(cliente_code, servicio_code) values(?,?);");
			statement.setInt(1, code_client);
			statement.setInt(2, code_service);

			statement.executeUpdate();
			System.out.println("Se ha guardado el Cliente_servicio: " + code_client + " " + code_service);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
