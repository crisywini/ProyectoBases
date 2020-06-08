package co.uniquindio.bases.supermarket.SuperMarketCampestre.observables;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EmployeeObservable {
	private StringProperty id;
	private StringProperty name;
	private StringProperty lastName;
	private StringProperty email;
	private StringProperty address;
	private StringProperty job;

	public EmployeeObservable(String id, String name, String lastName, String email, String address, String job) {
		this.id = new SimpleStringProperty(id);
		this.name = new SimpleStringProperty(name);
		this.lastName = new SimpleStringProperty(lastName);
		this.email = new SimpleStringProperty(email);
		this.address = new SimpleStringProperty(address);
		this.job = new SimpleStringProperty(job);
	}

	public StringProperty getId() {
		return id;
	}

	public void setId(StringProperty id) {
		this.id = id;
	}

	public StringProperty getName() {
		return name;
	}

	public void setName(StringProperty name) {
		this.name = name;
	}

	public StringProperty getLastName() {
		return lastName;
	}

	public void setLastName(StringProperty lastName) {
		this.lastName = lastName;
	}

	public StringProperty getEmail() {
		return email;
	}

	public void setEmail(StringProperty email) {
		this.email = email;
	}

	public StringProperty getAddress() {
		return address;
	}

	public void setAddress(StringProperty address) {
		this.address = address;
	}

	public StringProperty getJob() {
		return job;
	}

	public void setJob(StringProperty job) {
		this.job = job;
	}

}
