package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

public class Worker {

	private String code;
	private String name;
	private String lastName;
	private String email;
	private String address;
	private String actualCharge;

	public Worker(String code, String name, String lastName, String email, String address, String actualCharge) {
		this.code = code;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.actualCharge = actualCharge;
	}

	public Worker() {

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getActualCharge() {
		return actualCharge;
	}

	public void setActualCharge(String actualCharge) {
		this.actualCharge = actualCharge;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actualCharge == null) ? 0 : actualCharge.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Worker other = (Worker) obj;
		if (actualCharge == null) {
			if (other.actualCharge != null)
				return false;
		} else if (!actualCharge.equals(other.actualCharge))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "worker [code=" + code + ", name=" + name + ", lastName=" + lastName + ", email=" + email + ", address="
				+ address + ", actualCharge=" + actualCharge + "]";
	}
}
