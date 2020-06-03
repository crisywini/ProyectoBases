package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

import java.sql.Connection;
import java.sql.Statement;

public class Client {
	private int code;
	private String name;
	private String lastName;
	private String address;
	private String phoneNumber;
	private Statement statement;
	private Connection connection;


	public Client() {

	}
	
	

	public Client(int code, String name, String lastName, String address, String phoneNumber) {
		super();
		this.code = code;
		this.name = name;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	
	

	public Statement getStatement() {
		return statement;
	}



	public void setStatement(Statement statement) {
		this.statement = statement;
	}



	public Connection getConnection() {
		return connection;
	}



	public void setConnection(Connection connection) {
		this.connection = connection;
	}



	@Override
	public String toString() {
		return "Client [code=" + code + ", name=" + name + ", lastName=" + lastName + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + "]";
	}
	
	public void saveClient(int code, String name, String lastName, String address, String phoneNumber) {
		
	}
}
