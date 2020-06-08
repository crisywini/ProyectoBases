package co.uniquindio.bases.supermarket.SuperMarketCampestre.observables;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ContractObservable {
	private StringProperty code;
	private StringProperty salary;
	private StringProperty startDate;
	private StringProperty endDate;
	private StringProperty codeContractType;
	private StringProperty idEmployee;
	private StringProperty idJob;

	public ContractObservable(String code, String salary, String startDate, String endDate, String codeContractType,
			String idEmployee, String idJob) {
		this.code = new SimpleStringProperty(code);
		this.salary = new SimpleStringProperty(salary);
		this.startDate = new SimpleStringProperty(startDate);
		this.endDate = new SimpleStringProperty(endDate);
		this.codeContractType = new SimpleStringProperty(codeContractType);
		this.idEmployee = new SimpleStringProperty(idEmployee);
		this.idJob = new SimpleStringProperty(idJob);
	}

	public StringProperty getCode() {
		return code;
	}

	public void setCode(StringProperty code) {
		this.code = code;
	}

	public StringProperty getSalary() {
		return salary;
	}

	public void setSalary(StringProperty salary) {
		this.salary = salary;
	}

	public StringProperty getStartDate() {
		return startDate;
	}

	public void setStartDate(StringProperty startDate) {
		this.startDate = startDate;
	}

	public StringProperty getEndDate() {
		return endDate;
	}

	public void setEndDate(StringProperty endDate) {
		this.endDate = endDate;
	}

	public StringProperty getCodeContractType() {
		return codeContractType;
	}

	public void setCodeContractType(StringProperty codeContractType) {
		this.codeContractType = codeContractType;
	}

	public StringProperty getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(StringProperty idEmployee) {
		this.idEmployee = idEmployee;
	}

	public StringProperty getIdJob() {
		return idJob;
	}

	public void setIdJob(StringProperty idJob) {
		this.idJob = idJob;
	}
}
