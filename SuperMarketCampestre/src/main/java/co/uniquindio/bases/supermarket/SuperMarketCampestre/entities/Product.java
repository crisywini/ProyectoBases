package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

public class Product {
	private int code;
	private int quantity;
	private String name;
	private String details;

	public Product(int code, int quantity, String name, String details) {

		this.code = code;
		this.quantity = quantity;
		this.name = name;
		this.details = details;
	}

	public Product() {

	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "Product [code=" + code + ", quantity=" + quantity + ", name=" + name + ", details=" + details + "]";
	}
}
