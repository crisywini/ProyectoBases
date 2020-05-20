package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

public class Sale {

	private int code;
	private double totalPrice;
	private boolean order;

	public Sale(int code, double totalPrice, boolean order) {

		this.code = code;
		this.totalPrice = totalPrice;
		this.order = order;
	}

	public Sale() {

	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public boolean isOrder() {
		return order;
	}

	public void setOrder(boolean order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + (order ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(totalPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Sale other = (Sale) obj;
		if (code != other.code)
			return false;
		if (order != other.order)
			return false;
		if (Double.doubleToLongBits(totalPrice) != Double.doubleToLongBits(other.totalPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sale [code=" + code + ", totalPrice=" + totalPrice + ", order=" + order + "]";
	}

}
