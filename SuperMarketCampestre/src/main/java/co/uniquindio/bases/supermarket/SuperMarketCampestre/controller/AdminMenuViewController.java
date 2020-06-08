/**
 * Sample Skeleton for 'AdminMenuView.fxml' Controller Class
 */

package co.uniquindio.bases.supermarket.SuperMarketCampestre.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.uniquindio.bases.supermarket.SuperMarketCampestre.Main;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminMenuViewController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="menuPane"
	private BorderPane menuPane; // Value injected by FXMLLoader
	private LogginViewController lastView;
	private Employee employee;
	private Stage stage;

	@FXML
	void handleContractMenu(ActionEvent event) {
		loadContractView();
	}

	@FXML
	void handleEmployeeMenu(ActionEvent event) {
		loadEmployee();
	}

	@FXML
	void handleProductMenu(ActionEvent event) {
		loadProductView();
	}

	@FXML
	void handleProviderMenu(ActionEvent event) {
		loadProviderView();
	}

	@FXML
	void handleComeBackMenu(ActionEvent event) {
		stage.hide();
		lastView.openStage();
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert menuPane != null : "fx:id=\"menuPane\" was not injected: check your FXML file 'AdminMenuView.fxml'.";
		loadProductView();
	}

	VBox productView;
	ProductAdminViewController productAdminViewController;
	VBox providerView;
	ProviderAdminViewController providerViewController;
	VBox employeeView;
	EmployeeAdminViewController employeeViewController;
	VBox contractView;
	AdminContractViewController contractViewController;

	public void loadProductView() {
		if (productView == null) {
			try {
				FXMLLoader loader = new FXMLLoader(Main.class.getResource("/ProductAdminView.fxml"));
				productView = (VBox) loader.load();
				productAdminViewController = loader.getController();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		productAdminViewController.setLastView(this);
		menuPane.setCenter(productView);
	}

	public void loadContractView() {
		if (contractView == null) {
			try {
				FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AdminContractView.fxml"));
				contractView = (VBox) loader.load();
				contractViewController = loader.getController();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		contractViewController.setLastView(this);
		menuPane.setCenter(contractView);
	}

	public void loadEmployee() {
		if (employeeView == null) {
			try {
				FXMLLoader loader = new FXMLLoader(Main.class.getResource("/EmployeeAdminView.fxml"));
				employeeView = (VBox) loader.load();
				employeeViewController = loader.getController();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		employeeViewController.setLastView(this);
		menuPane.setCenter(employeeView);
	}

	public void loadProviderView() {
		if (providerView == null) {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/ProviderAdminView.fxml"));
			try {
				providerView = (VBox) loader.load();
				providerViewController = loader.getController();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		providerViewController.setLastView(this);
		menuPane.setCenter(providerView);
	}

	public LogginViewController getLastView() {
		return lastView;
	}

	public void setLastView(LogginViewController lastView) {
		this.lastView = lastView;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
