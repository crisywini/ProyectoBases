package co.uniquindio.bases.supermarket.SuperMarketCampestre.observables;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProductObservable {
	private StringProperty code;
	private StringProperty quantity;
	private StringProperty name;
	private StringProperty details;
	private StringProperty price;
	
	public ProductObservable(String code, String quantity, String name, String details,
			String price) {
		this.code = new SimpleStringProperty(code);
		this.quantity =new SimpleStringProperty(quantity);
		this.name = new SimpleStringProperty(name);
		this.details = new SimpleStringProperty(details);
		this.price = new SimpleStringProperty(price);
	}
	public StringProperty getCode() {
		return code;
	}
	public void setCode(StringProperty code) {
		this.code = code;
	}
	public StringProperty getQuantity() {
		return quantity;
	}
	public void setQuantity(StringProperty quantity) {
		this.quantity = quantity;
	}
	public StringProperty getName() {
		return name;
	}
	public void setName(StringProperty name) {
		this.name = name;
	}
	public StringProperty getDetails() {
		return details;
	}
	public void setDetails(StringProperty details) {
		this.details = details;
	}
	public StringProperty getPrice() {
		return price;
	}
	public void setPrice(StringProperty price) {
		this.price = price;
	}
	

}
