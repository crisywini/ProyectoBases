/**
 * Sample Skeleton for 'ProviderAdminView.fxml' Controller Class
 */

package co.uniquindio.bases.supermarket.SuperMarketCampestre.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.exceptions.EntityRepeatedException;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.exceptions.NonexistentEntityException;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.util.AdministratorDelegate;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.Provider;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.observables.ProviderObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ProviderAdminViewController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="nameField"
	private TextField nameField; // Value injected by FXMLLoader

	@FXML // fx:id="emailField"
	private TextField emailField; // Value injected by FXMLLoader

	@FXML // fx:id="addressField"
	private TextField addressField; // Value injected by FXMLLoader

	@FXML // fx:id="phoneField"
	private TextField phoneField; // Value injected by FXMLLoader

	@FXML // fx:id="tableView"
	private TableView<ProviderObservable> tableView; // Value injected by FXMLLoader

	@FXML // fx:id="codeColumn"
	private TableColumn<ProviderObservable, String> codeColumn; // Value injected by FXMLLoader

	@FXML // fx:id="nameColumn"
	private TableColumn<ProviderObservable, String> nameColumn; // Value injected by FXMLLoader

	@FXML // fx:id="emailColumn"
	private TableColumn<ProviderObservable, String> emailColumn; // Value injected by FXMLLoader

	@FXML // fx:id="addressColumn"
	private TableColumn<ProviderObservable, String> addressColumn; // Value injected by FXMLLoader

	@FXML // fx:id="phoneColumn"
	private TableColumn<ProviderObservable, String> phoneColumn; // Value injected by FXMLLoader
	private ObservableList<ProviderObservable> providersData = FXCollections.observableArrayList();
	private ProviderObservable providerSelected;
	private AdminMenuViewController lastView;
	private AdministratorDelegate admin = new AdministratorDelegate();

	@FXML
	void handleAddButton(ActionEvent event) {
		if (isInputValid()) {
			try {
				admin.addProvider(emailField.getText(), nameField.getText(), addressField.getText(),
						phoneField.getText());
				Provider provider = admin.getProvider(emailField.getText());
				providersData.add(new ProviderObservable(provider.getCode() + "", provider.getName() + "",
						provider.getEmail(), provider.getAddress(), provider.getPhone()));
				tableView.refresh();
				MainController.showAlert("Se ha agregado el proveedor: " + provider.getName() + " correctamente",
						"INFORMACIÓN", AlertType.INFORMATION);
				emailField.setText("");
				nameField.setText("");
				addressField.setText("");
				phoneField.setText("");
			} catch (EntityRepeatedException e) {
				MainController.showAlert(e.getMessage(), "ERROR", AlertType.ERROR);
			} catch (NonexistentEntityException e) {
				MainController.showAlert(e.getMessage(), "ERROR", AlertType.ERROR);
			}
		}

	}

	public boolean isInputValid() {
		String errorMessage = "";
		boolean isValid = false;
		if (nameField.getText().isEmpty())
			errorMessage += "Debes ingresar el nombre\n";
		if (emailField.getText().isEmpty())
			errorMessage += "Debes ingresar el correo electrónico\n";
		if (addressField.getText().isEmpty())
			errorMessage += "Debes ingresar la dirección\n";
		if (phoneField.getText().isEmpty())
			errorMessage += "Debes ingresar el número de teléfono\n";
		else
			try {
				Integer.parseInt(phoneField.getText());
			} catch (Exception e) {
				errorMessage += "El teléfono debe tener solo números";
			}
		if (errorMessage.isEmpty())
			isValid = true;
		else
			MainController.showAlert(errorMessage, "ADVERTENCIA", AlertType.WARNING);
		return isValid;
	}

	@FXML
	void handleRemoveButton(ActionEvent event) {
		if (!tableView.getSelectionModel().isEmpty()) {
			providerSelected = tableView.getSelectionModel().getSelectedItem();
			try {
				admin.removeProvider(Integer.parseInt(providerSelected.getCode().get()));
				MainController.showAlert("El proveedor: " + providerSelected.getName().get() + " se ha eliminado",
						"ADVERTENCIA", AlertType.WARNING);
				initTableView();
				emailField.setText("");
				nameField.setText("");
				addressField.setText("");
				phoneField.setText("");
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (NonexistentEntityException e) {
				MainController.showAlert(e.getMessage(), "ERROR", AlertType.ERROR);
			}
		} else
			MainController.showAlert("Debes seleccionar algún proveedor", "ADVERTENCIA", AlertType.WARNING);
	}

	@FXML
	void handleSelectButton(ActionEvent event) {
		if (!tableView.getSelectionModel().isEmpty()) {
			providerSelected = tableView.getSelectionModel().getSelectedItem();
			MainController.showAlert("Se ha seleccionado el proveedor: " + providerSelected.getName().get(),
					"INFORMACIÓN", AlertType.INFORMATION);

			emailField.setText(providerSelected.getEmail().get());
			nameField.setText(providerSelected.getName().get());
			addressField.setText(providerSelected.getAddress().get());
			phoneField.setText(providerSelected.getPhoneNumber().get());

		} else
			MainController.showAlert("Debes seleccionar algún proveedor", "ADVERTENCIA", AlertType.WARNING);
	}

	@FXML
	void handleUpdateButton(ActionEvent event) {
		if (isInputValid()) {
			Provider provider = new Provider(Integer.parseInt(providerSelected.getCode().get()), emailField.getText(),
					nameField.getText(), addressField.getText(), phoneField.getText());
			
		}
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert nameField != null : "fx:id=\"nameField\" was not injected: check your FXML file 'ProviderAdminView.fxml'.";
		assert emailField != null : "fx:id=\"emailField\" was not injected: check your FXML file 'ProviderAdminView.fxml'.";
		assert addressField != null : "fx:id=\"addressField\" was not injected: check your FXML file 'ProviderAdminView.fxml'.";
		assert phoneField != null : "fx:id=\"phoneField\" was not injected: check your FXML file 'ProviderAdminView.fxml'.";
		assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'ProviderAdminView.fxml'.";
		assert codeColumn != null : "fx:id=\"codeColumn\" was not injected: check your FXML file 'ProviderAdminView.fxml'.";
		assert nameColumn != null : "fx:id=\"nameColumn\" was not injected: check your FXML file 'ProviderAdminView.fxml'.";
		assert emailColumn != null : "fx:id=\"emailColumn\" was not injected: check your FXML file 'ProviderAdminView.fxml'.";
		assert addressColumn != null : "fx:id=\"addressColumn\" was not injected: check your FXML file 'ProviderAdminView.fxml'.";
		assert phoneColumn != null : "fx:id=\"phoneColumn\" was not injected: check your FXML file 'ProviderAdminView.fxml'.";

	}

	public void initTableView() {
		tableView.getItems().clear();
		codeColumn.setCellValueFactory(cellData -> cellData.getValue().getCode());
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
		emailColumn.setCellValueFactory(cellData -> cellData.getValue().getEmail());
		addressColumn.setCellValueFactory(cellData -> cellData.getValue().getAddress());
		phoneColumn.setCellValueFactory(cellData -> cellData.getValue().getPhoneNumber());
		loadDataProviders();
		tableView.setItems(providersData);
		tableView.refresh();
	}

	public void loadDataProviders() {
		List<Provider> providers = admin.getAllProviders();
		for (Provider provider : providers) {
			providersData.add(new ProviderObservable(provider.getCode() + "", provider.getName(), provider.getEmail(),
					provider.getAddress(), provider.getPhone()));
		}
	}

	public AdminMenuViewController getLastView() {
		return lastView;
	}

	public void setLastView(AdminMenuViewController lastView) {
		this.lastView = lastView;
		initTableView();
	}

}
