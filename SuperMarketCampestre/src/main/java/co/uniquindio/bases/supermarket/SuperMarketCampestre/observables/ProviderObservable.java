package co.uniquindio.bases.supermarket.SuperMarketCampestre.observables;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProviderObservable {
	private StringProperty code;
	private StringProperty name;
	private StringProperty email;
	private StringProperty address;
	private StringProperty phoneNumber;
	
	public ProviderObservable(String code, String name, String email, String address,
			String phoneNumber) {
		this.code = new SimpleStringProperty(code);
		this.name = new SimpleStringProperty(name);
		this.email = new SimpleStringProperty(email);
		this.address = new SimpleStringProperty(address);
		this.phoneNumber = new SimpleStringProperty(phoneNumber);
	}
	public StringProperty getCode() {
		return code;
	}
	public void setCode(StringProperty code) {
		this.code = code;
	}
	public StringProperty getName() {
		return name;
	}
	public void setName(StringProperty name) {
		this.name = name;
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
	public StringProperty getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(StringProperty phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	

}
