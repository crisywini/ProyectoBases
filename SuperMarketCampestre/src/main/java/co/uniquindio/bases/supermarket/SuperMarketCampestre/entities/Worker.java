package co.uniquindio.bases.supermarket.SuperMarketCampestre.entities;

public class Worker {

	private int code;
	private String name;
	private String lastName;
	private String email;
	private String address;
	private String actualJob;

	public Worker(int code, String name, String lastName, String email, String address, String actualJob) {
		this.code = code;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.actualJob = actualJob;
	}

	public Worker() {

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

	public String getActualJob() {
		return actualJob;
	}

	public void setActualJob(String actualJob) {
		this.actualJob = actualJob;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actualJob == null) ? 0 : actualJob.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + code;
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
		if (actualJob == null) {
			if (other.actualJob != null)
				return false;
		} else if (!actualJob.equals(other.actualJob))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (code != other.code)
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
				+ address + ", actualJob=" + actualJob + "]";
	}
}
