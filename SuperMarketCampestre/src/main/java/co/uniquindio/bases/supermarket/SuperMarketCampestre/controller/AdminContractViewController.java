/**
 * Sample Skeleton for 'AdminContractView.fxml' Controller Class
 */

package co.uniquindio.bases.supermarket.SuperMarketCampestre.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.exceptions.EntityRepeatedException;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.exceptions.NonexistentEntityException;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.util.AdministratorDelegate;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.Contract;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.ContractType;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.Job;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.observables.ContractObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AdminContractViewController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="salaryField"
	private TextField salaryField; // Value injected by FXMLLoader

	@FXML // fx:id="startDate"
	private DatePicker startDate; // Value injected by FXMLLoader

	@FXML // fx:id="endDate"
	private DatePicker endDate; // Value injected by FXMLLoader

	@FXML // fx:id="comboContractType"
	private ComboBox<ContractType> comboContractType; // Value injected by FXMLLoader

	@FXML // fx:id="idEmployeeField"
	private TextField idEmployeeField; // Value injected by FXMLLoader

	@FXML // fx:id="comboJob"
	private ComboBox<Job> comboJob; // Value injected by FXMLLoader

	@FXML // fx:id="tableView"
	private TableView<ContractObservable> tableView; // Value injected by FXMLLoader

	@FXML // fx:id="codeColumn"
	private TableColumn<ContractObservable, String> codeColumn; // Value injected by FXMLLoader

	@FXML // fx:id="idEmployeeColumn"
	private TableColumn<ContractObservable, String> idEmployeeColumn; // Value injected by FXMLLoader

	@FXML // fx:id="contractTypeColumn"
	private TableColumn<ContractObservable, String> contractTypeColumn; // Value injected by FXMLLoader

	@FXML // fx:id="startDateColumn"
	private TableColumn<ContractObservable, String> startDateColumn; // Value injected by FXMLLoader

	@FXML // fx:id="endDateColumn"
	private TableColumn<ContractObservable, String> endDateColumn; // Value injected by FXMLLoader

	@FXML // fx:id="jobColumn"
	private TableColumn<ContractObservable, String> jobColumn; // Value injected by FXMLLoader

	@FXML // fx:id="salaryColumn"
	private TableColumn<ContractObservable, String> salaryColumn; // Value injected by FXMLLoader
	private AdministratorDelegate admin = new AdministratorDelegate();
	private ObservableList<ContractObservable> contractsData = FXCollections.observableArrayList();
	private AdminMenuViewController lastView;
	private ContractObservable contractSelected;

	@FXML
	void handleAddButton(ActionEvent event) {
		if (isInputValid()) {
			ContractType type = comboContractType.getSelectionModel().getSelectedItem();
			Job job = comboJob.getSelectionModel().getSelectedItem();
			if (type.getName().equalsIgnoreCase("indefinido")) {
				LocalDate startLocalDate = startDate.getValue();
				String formatDate = startLocalDate.getYear() + "-" + startLocalDate.getMonthValue() + "-"
						+ startLocalDate.getDayOfMonth();
				try {
					admin.addContract2(Double.parseDouble(salaryField.getText()), formatDate, type.getCode(),
							idEmployeeField.getText(), job.code);
					Contract contract = admin.getContract2(formatDate, idEmployeeField.getText(), job.getCode());
					contractsData.add(new ContractObservable(contract.getCode() + "", contract.getSalary() + "",
							contract.getStartDate(), null, contract.getCode_type() + "", contract.getCode_employee(),
							contract.getCode_job() + ""));
					tableView.refresh();
					MainController.showAlert("Se a agregado el contrato correctamente", "INFORMACIÓN",
							AlertType.INFORMATION);
					salaryField.setText("");
					startDate.setValue(null);
					idEmployeeField.setText("");

				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (EntityRepeatedException e) {
					MainController.showAlert(e.getMessage(), "ERROR", AlertType.ERROR);
				} catch (NonexistentEntityException e) {
					MainController.showAlert(e.getMessage(), "ERROR", AlertType.ERROR);
				}
			} else {
				LocalDate startLocalDate = startDate.getValue();
				LocalDate endLocalDate = endDate.getValue();
				String formatDate = startLocalDate.getYear() + "-" + startLocalDate.getMonthValue() + "-"
						+ startLocalDate.getDayOfMonth();
				String formarFinalDate = endLocalDate.getYear() + "-" + endLocalDate.getMonthValue() + "-"
						+ endLocalDate.getDayOfMonth();
				try {
					admin.addContract(Double.parseDouble(salaryField.getText()), formatDate, formarFinalDate,
							type.getCode(), idEmployeeField.getText(), job.code);
					Contract contract = admin.getContract(formatDate, formarFinalDate, idEmployeeField.getText(),
							job.getCode());
					contractsData.add(new ContractObservable(contract.getCode() + "", contract.getSalary() + "",
							contract.getStartDate(), formarFinalDate, contract.getCode_type() + "",
							contract.getCode_employee(), contract.getCode_job() + ""));
					tableView.refresh();
					MainController.showAlert("Se a agregado el contrato correctamente", "INFORMACIÓN",
							AlertType.INFORMATION);
					salaryField.setText("");
					startDate.setValue(null);
					idEmployeeField.setText("");

				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (EntityRepeatedException e) {
					MainController.showAlert(e.getMessage(), "ERROR", AlertType.ERROR);
				} catch (NonexistentEntityException e) {
					MainController.showAlert(e.getMessage(), "ERROR", AlertType.ERROR);
				}
			}
		}

	}

	public boolean isInputValid() {
		String errorMessage = "";
		boolean isValid = false;
		if (salaryField.getText().isEmpty())
			errorMessage += "Debes ingresar el salario\n";
		else
			try {
				Double.parseDouble(salaryField.getText());
			} catch (Exception e) {
				errorMessage += "El salario debe ser numérico\n";
			}
		if (comboContractType.getSelectionModel().isEmpty())
			errorMessage += "Debes seleccionar un término\n";
		else {
			if (comboContractType.getSelectionModel().getSelectedItem().getName().equalsIgnoreCase("indefinido")) {
				if (startDate.getValue() == null)
					errorMessage += "Los contratos a término indefinido deben tener una fecha de inicio\n";

			} else if (startDate.getValue() == null)
				errorMessage += "Los contratos a término definido deben tener una fecha de inicio\n";
			if (endDate.getValue() == null)
				errorMessage += "Los contratos a término definido deben tener una fecha de fin\n";
		}
		if (idEmployeeField.getText().isEmpty())
			errorMessage += "Debes ingresar la cédula del empleado\n";
		if (comboJob.getSelectionModel().isEmpty())
			errorMessage += "Debes seleccionar un cargo\n";
		if (errorMessage.isEmpty())
			isValid = true;
		else
			MainController.showAlert(errorMessage, "ADVERTENCIA", AlertType.WARNING);
		return isValid;
	}

	@FXML
	void handleRemoveButton(ActionEvent event) {
		if (!tableView.getSelectionModel().isEmpty()) {
			contractSelected = tableView.getSelectionModel().getSelectedItem();
			try {
				admin.removeContract(Integer.parseInt(contractSelected.getCode().get()));
				initTableView();
				MainController.showAlert("Se ha eliminado el contrato correctamente", "ADVERTENCIA", AlertType.WARNING);
				initTableView();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (NonexistentEntityException e) {
				MainController.showAlert(e.getMessage(), "ERROR", AlertType.ERROR);
			}
		} else
			MainController.showAlert("Debes seleccionar un contrato", "ADVERTENCIA", AlertType.WARNING);
	}

	@FXML
	void handleSelectButton(ActionEvent event) {
		if (!tableView.getSelectionModel().isEmpty()) {
			contractSelected = tableView.getSelectionModel().getSelectedItem();
			idEmployeeField.setText(contractSelected.getIdEmployee().get());
			salaryField.setText(contractSelected.getIdEmployee().get());
			MainController.showAlert("Se ha seleccionado correctamente el contrato", "", AlertType.INFORMATION);
		} else
			MainController.showAlert("Debes seleccionar algún contrato", "ADVERTENCIA", AlertType.INFORMATION);
	}

	@FXML
	void handleUpdateButton(ActionEvent event) {
		if (isInputValid()) {
			LocalDate startLocalDate = startDate.getValue();
			String formatDate = startLocalDate.getYear() + "-" + startLocalDate.getMonthValue() + "-"
					+ startLocalDate.getDayOfMonth();

			ContractType type = comboContractType.getValue();
			Job job = comboJob.getValue();
			if (type.getName().equalsIgnoreCase("indefinido")) {
				Contract contract = new Contract(Integer.parseInt(contractSelected.getCode().get()),
						Double.parseDouble(salaryField.getText()), formatDate, null, type.getCode(),
						idEmployeeField.getText(), job.getCode());
				admin.updateContract(contract);
				initTableView();

				MainController.showAlert("Se ha actualizado el contrato correctamente", "INFORMACIÓN",
						AlertType.INFORMATION);
				salaryField.setText("");
				startDate.setValue(null);
				idEmployeeField.setText("");
			} else {
				LocalDate endLocalDate = endDate.getValue();

				String formatFinalDate = endLocalDate.getYear() + "-" + endLocalDate.getMonthValue() + "-"
						+ endLocalDate.getDayOfMonth();
				Contract contract = new Contract(Integer.parseInt(contractSelected.getCode().get()),
						Double.parseDouble(salaryField.getText()), formatDate, formatFinalDate, type.getCode(),
						idEmployeeField.getText(), job.getCode());

				admin.updateContract(contract);
				MainController.showAlert("Se ha actualizado el contrato correctamente", "INFORMACIÓN",
						AlertType.INFORMATION);
				initTableView();
				salaryField.setText("");
				startDate.setValue(null);
				idEmployeeField.setText("");

			}
		}

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert salaryField != null : "fx:id=\"salaryField\" was not injected: check your FXML file 'AdminContractView.fxml'.";
		assert startDate != null : "fx:id=\"startDate\" was not injected: check your FXML file 'AdminContractView.fxml'.";
		assert endDate != null : "fx:id=\"endDate\" was not injected: check your FXML file 'AdminContractView.fxml'.";
		assert comboContractType != null : "fx:id=\"comboContractType\" was not injected: check your FXML file 'AdminContractView.fxml'.";
		assert idEmployeeField != null : "fx:id=\"idEmployeeField\" was not injected: check your FXML file 'AdminContractView.fxml'.";
		assert comboJob != null : "fx:id=\"comboJob\" was not injected: check your FXML file 'AdminContractView.fxml'.";
		assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'AdminContractView.fxml'.";
		assert codeColumn != null : "fx:id=\"codeColumn\" was not injected: check your FXML file 'AdminContractView.fxml'.";
		assert idEmployeeColumn != null : "fx:id=\"idEmployeeColumn\" was not injected: check your FXML file 'AdminContractView.fxml'.";
		assert contractTypeColumn != null : "fx:id=\"contractTypeColumn\" was not injected: check your FXML file 'AdminContractView.fxml'.";
		assert startDateColumn != null : "fx:id=\"startDateColumn\" was not injected: check your FXML file 'AdminContractView.fxml'.";
		assert endDateColumn != null : "fx:id=\"endDateColumn\" was not injected: check your FXML file 'AdminContractView.fxml'.";
		assert jobColumn != null : "fx:id=\"jobColumn\" was not injected: check your FXML file 'AdminContractView.fxml'.";
		assert salaryColumn != null : "fx:id=\"salaryColumn\" was not injected: check your FXML file 'AdminContractView.fxml'.";
	}

	public void initTableView() {
		tableView.getItems().clear();
		codeColumn.setCellValueFactory(cellData -> cellData.getValue().getCode());
		contractTypeColumn.setCellValueFactory(cellData -> cellData.getValue().getCodeContractType());
		endDateColumn.setCellValueFactory(cellData -> cellData.getValue().getEndDate());
		idEmployeeColumn.setCellValueFactory(cellData -> cellData.getValue().getIdEmployee());
		jobColumn.setCellValueFactory(cellData -> cellData.getValue().getIdJob());
		salaryColumn.setCellValueFactory(cellData -> cellData.getValue().getSalary());
		startDateColumn.setCellValueFactory(cellData -> cellData.getValue().getStartDate());
		loadContractsData();
		tableView.setItems(contractsData);
		tableView.refresh();
	}

	public void loadContractsData() {
		List<Contract> contracts = admin.getAllContracts();
		for (Contract contract : contracts) {
			contractsData.add(new ContractObservable(contract.getCode() + "", contract.getSalary() + "",
					contract.getStartDate(), contract.getEndDate(), contract.getCode_type() + "",
					contract.getCode_employee(), contract.getCode_job() + ""));
		}
	}

	public void initComboType() {
		ObservableList<ContractType> contracts = FXCollections.observableArrayList();
		List<ContractType> types = admin.getAllContractTypes();
		for (ContractType contractType : types) {
			contracts.add(contractType);
		}
		comboContractType.setItems(contracts);
	}

	public void initComboJob() {
		ObservableList<Job> jobs = FXCollections.observableArrayList();
		List<Job> jobsR = admin.getAllJobs();
		for (Job job : jobsR) {
			jobs.add(job);
		}
		comboJob.setItems(jobs);
	}

	public AdminMenuViewController getLastView() {
		return lastView;
	}

	public void setLastView(AdminMenuViewController lastView) {
		this.lastView = lastView;
		initComboJob();
		initComboType();
		initTableView();
	}
}
