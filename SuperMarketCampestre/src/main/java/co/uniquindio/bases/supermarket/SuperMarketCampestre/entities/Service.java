package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

public class Service {
	private int code;
	private String name;
	private String details;

	public Service(int code, String name, String details) {

		this.code = code;
		this.name = name;
		this.details = details;
	}

	public Service() {
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

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
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
		Service other = (Service) obj;
		if (code != other.code)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Service [code=" + code + ", name=" + name + ", details=" + details + "]";
	}
}
