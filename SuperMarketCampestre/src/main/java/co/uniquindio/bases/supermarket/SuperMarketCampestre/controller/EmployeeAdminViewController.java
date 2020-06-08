/**
 * Sample Skeleton for 'EmployeeAdminView.fxml' Controller Class
 */

package co.uniquindio.bases.supermarket.SuperMarketCampestre.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.exceptions.EntityRepeatedException;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.exceptions.NonexistentEntityException;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.util.AdministratorDelegate;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.Employee;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.Job;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.observables.EmployeeObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class EmployeeAdminViewController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="idEmployeeField"
	private TextField idEmployeeField; // Value injected by FXMLLoader

	@FXML // fx:id="nameEmployeeField"
	private TextField nameEmployeeField; // Value injected by FXMLLoader

	@FXML // fx:id="lastNameEmployeeField"
	private TextField lastNameEmployeeField; // Value injected by FXMLLoader

	@FXML // fx:id="emailField"
	private TextField emailField; // Value injected by FXMLLoader

	@FXML // fx:id="addressField"
	private TextField addressField; // Value injected by FXMLLoader

	@FXML // fx:id="comboField"
	private ComboBox<Job> comboField; // Value injected by FXMLLoader

	@FXML // fx:id="tableView"
	private TableView<EmployeeObservable> tableView; // Value injected by FXMLLoader

	@FXML // fx:id="idEmployeeColumn"
	private TableColumn<EmployeeObservable, String> idEmployeeColumn; // Value injected by FXMLLoader

	@FXML // fx:id="nameEmployeeColumn"
	private TableColumn<EmployeeObservable, String> nameEmployeeColumn; // Value injected by FXMLLoader

	@FXML // fx:id="lastNameEmployeeColumn"
	private TableColumn<EmployeeObservable, String> lastNameEmployeeColumn; // Value injected by FXMLLoader

	@FXML // fx:id="emailEmployeeColumn"
	private TableColumn<EmployeeObservable, String> emailEmployeeColumn; // Value injected by FXMLLoader

	@FXML // fx:id="addressEmployeeColumn"
	private TableColumn<EmployeeObservable, String> addressEmployeeColumn; // Value injected by FXMLLoader

	@FXML // fx:id="jobEmployeeColumn"
	private TableColumn<EmployeeObservable, String> jobEmployeeColumn; // Value injected by FXMLLoader
	private ObservableList<EmployeeObservable> employeesData = FXCollections.observableArrayList();
	private AdministratorDelegate admin = new AdministratorDelegate();
	private AdminMenuViewController lastView;
	private EmployeeObservable employeeSelected;

	@FXML
	void handleAddEmployeeButton(ActionEvent event) {
		if (isInputValid()) {
			Job jobSelected = comboField.getSelectionModel().getSelectedItem();
			try {
				admin.addEmployee(idEmployeeField.getText(), nameEmployeeField.getText(),
						lastNameEmployeeField.getText(), emailField.getText(), addressField.getText(),
						jobSelected.getCode());
				MainController.showAlert("El empleado: " + idEmployeeField.getText() + " ha sido agregado",
						"INFORMACIÓN", AlertType.INFORMATION);
				initTableView();
				idEmployeeField.setText("");
				nameEmployeeField.setText("");
				lastNameEmployeeField.setText("");
				emailField.setText("");
				addressField.setText("");
				comboField.getSelectionModel().clearSelection();
			} catch (EntityRepeatedException e) {
				MainController.showAlert(e.getMessage(), "ERROR", AlertType.ERROR);
			}
		}

	}

	public boolean isInputValid() {
		String errorMessage = "";
		boolean isValid = false;
		if (addressField.getText().isEmpty())
			errorMessage += "Debes ingresar la dirección\n";
		if (emailField.getText().isEmpty())
			errorMessage += "Debes ingresar el correo electrónico\n";
		if (idEmployeeField.getText().isEmpty())
			errorMessage += "Debes ingresar la cédula\n";
		else if (idEmployeeField.getText().length() > 10)
			errorMessage += "La cédula debe tener 10 caracereres o menos\n";
		if (lastNameEmployeeField.getText().isEmpty())
			errorMessage += "Debes ingresar el apellido\n";
		if (nameEmployeeField.getText().isEmpty())
			errorMessage += "Debes ingresar el nombre\n";
		if (comboField.getSelectionModel().isEmpty())
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
			employeeSelected = tableView.getSelectionModel().getSelectedItem();
			try {
				admin.removeEmployee(employeeSelected.getId().get());
				MainController.showAlert("Se ha eliminado el empleado: " + employeeSelected.getId().get(),
						"ADVERTENCIA", AlertType.WARNING);
				initTableView();
				idEmployeeField.setText("");
				nameEmployeeField.setText("");
				lastNameEmployeeField.setText("");
				emailField.setText("");
				addressField.setText("");
				comboField.getSelectionModel().clearSelection();
			} catch (NonexistentEntityException e) {
				MainController.showAlert(e.getMessage(), "ERROR", AlertType.ERROR);
			}

		} else
			MainController.showAlert("Debes seleccionar un empleado", "ADVERTENCIA", AlertType.WARNING);
	}

	@FXML
	void handleSelectButton(ActionEvent event) {
		if (!tableView.getSelectionModel().isEmpty()) {
			employeeSelected = tableView.getSelectionModel().getSelectedItem();

			MainController.showAlert("Se ha seleccionado el empleado: " + employeeSelected.getId().get(), "INFORMACIÓN",
					AlertType.INFORMATION);
			idEmployeeField.setText(employeeSelected.getId().get());
			nameEmployeeField.setText(employeeSelected.getName().get());
			lastNameEmployeeField.setText(employeeSelected.getLastName().get());
			emailField.setText(employeeSelected.getEmail().get());
			addressField.setText(employeeSelected.getAddress().get());
		} else
			MainController.showAlert("Debes seleccionar un empleado", "ADVERTENCIA", AlertType.WARNING);
	}

	@FXML
	void handleUpdateButton(ActionEvent event) {
		if (isInputValid()) {
			employeeSelected = tableView.getSelectionModel().getSelectedItem();
			Job job = comboField.getSelectionModel().getSelectedItem();
			Employee employee = new Employee(employeeSelected.getId().get(), nameEmployeeField.getText(),
					lastNameEmployeeField.getText(), emailField.getText(), addressField.getText(), job.getCode());
			admin.updateEmployee(employee);
			MainController.showAlert("Se ha modificado el empleado: " + employeeSelected.getId().get(), "ADVERTENCIA",
					AlertType.WARNING);
			initTableView();
			idEmployeeField.setText("");
			nameEmployeeField.setText("");
			lastNameEmployeeField.setText("");
			emailField.setText("");
			addressField.setText("");
			comboField.getSelectionModel().clearSelection();
		}

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert idEmployeeField != null : "fx:id=\"idEmployeeField\" was not injected: check your FXML file 'EmployeeAdminView.fxml'.";
		assert nameEmployeeField != null : "fx:id=\"nameEmployeeField\" was not injected: check your FXML file 'EmployeeAdminView.fxml'.";
		assert lastNameEmployeeField != null : "fx:id=\"lastNameEmployeeField\" was not injected: check your FXML file 'EmployeeAdminView.fxml'.";
		assert emailField != null : "fx:id=\"emailField\" was not injected: check your FXML file 'EmployeeAdminView.fxml'.";
		assert addressField != null : "fx:id=\"addressField\" was not injected: check your FXML file 'EmployeeAdminView.fxml'.";
		assert comboField != null : "fx:id=\"comboField\" was not injected: check your FXML file 'EmployeeAdminView.fxml'.";
		assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'EmployeeAdminView.fxml'.";
		assert idEmployeeColumn != null : "fx:id=\"idEmployeeColumn\" was not injected: check your FXML file 'EmployeeAdminView.fxml'.";
		assert nameEmployeeColumn != null : "fx:id=\"nameEmployeeColumn\" was not injected: check your FXML file 'EmployeeAdminView.fxml'.";
		assert lastNameEmployeeColumn != null : "fx:id=\"lastNameEmployeeColumn\" was not injected: check your FXML file 'EmployeeAdminView.fxml'.";
		assert emailEmployeeColumn != null : "fx:id=\"emailEmployeeColumn\" was not injected: check your FXML file 'EmployeeAdminView.fxml'.";
		assert addressEmployeeColumn != null : "fx:id=\"addressEmployeeColumn\" was not injected: check your FXML file 'EmployeeAdminView.fxml'.";
		assert jobEmployeeColumn != null : "fx:id=\"jobEmployeeColumn\" was not injected: check your FXML file 'EmployeeAdminView.fxml'.";

	}

	public void initTableView() {
		tableView.getItems().clear();
		addressEmployeeColumn.setCellValueFactory(cellData -> cellData.getValue().getAddress());
		emailEmployeeColumn.setCellValueFactory(cellData -> cellData.getValue().getEmail());
		idEmployeeColumn.setCellValueFactory(cellData -> cellData.getValue().getId());
		jobEmployeeColumn.setCellValueFactory(cellData -> cellData.getValue().getJob());
		lastNameEmployeeColumn.setCellValueFactory(cellData -> cellData.getValue().getLastName());
		nameEmployeeColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
		loadEmployeeData();
		tableView.setItems(employeesData);
		tableView.refresh();
	}

	public void loadEmployeeData() {
		List<Employee> employeeList = admin.getAllEmployees();
		for (Employee employee : employeeList) {
			employeesData.add(new EmployeeObservable(employee.getCode(), employee.getName(), employee.getLast_name(),
					employee.getEmail(), employee.getAddress(), employee.getCode_job() + ""));
		}
	}

	public void initCombo() {
		ObservableList<Job> jobs = FXCollections.observableArrayList();
		List<Job> jobsR = admin.getAllJobs();
		for (Job job : jobsR) {
			jobs.add(job);
		}
		comboField.setItems(jobs);
	}

	public AdminMenuViewController getLastView() {
		return lastView;
	}

	public void setLastView(AdminMenuViewController lastView) {
		this.lastView = lastView;
		initCombo();
		initTableView();
	}
}
