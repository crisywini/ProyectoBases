/**
 * Sample Skeleton for 'ProductAdminView.fxml' Controller Class
 */

package co.uniquindio.bases.supermarket.SuperMarketCampestre.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.exceptions.EntityRepeatedException;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.exceptions.NonexistentEntityException;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.util.AdministratorDelegate;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.Product;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.observables.ProductObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ProductAdminViewController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="detailArea"
	private TextArea detailArea; // Value injected by FXMLLoader

	@FXML // fx:id="nameField"
	private TextField nameField; // Value injected by FXMLLoader

	@FXML // fx:id="quantityField"
	private TextField quantityField; // Value injected by FXMLLoader

	@FXML // fx:id="priceField"
	private TextField priceField; // Value injected by FXMLLoader

	@FXML // fx:id="tableView"
	private TableView<ProductObservable> tableView; // Value injected by FXMLLoader

	@FXML // fx:id="codeColumn"
	private TableColumn<ProductObservable, String> codeColumn; // Value injected by FXMLLoader

	@FXML // fx:id="nameColumn"
	private TableColumn<ProductObservable, String> nameColumn; // Value injected by FXMLLoader

	@FXML // fx:id="detailColumn"
	private TableColumn<ProductObservable, String> detailColumn; // Value injected by FXMLLoader

	@FXML // fx:id="quantityColumn"
	private TableColumn<ProductObservable, String> quantityColumn; // Value injected by FXMLLoader

	@FXML // fx:id="priceColumn"
	private TableColumn<ProductObservable, String> priceColumn; // Value injected by FXMLLoader
	private AdminMenuViewController lastView;

	@FXML
	void handleAddProduct(ActionEvent event) {
		if (isInputValid()) {
			try {
				admin.addProduct(Integer.parseInt(quantityField.getText()), nameField.getText(), detailArea.getText(),
						Double.parseDouble(priceField.getText()));
				Product product = admin.getProduct(nameField.getText(), detailArea.getText());
				productData.add(new ProductObservable(product.getCode() + "", product.getQuantity() + "",
						product.getName(), product.getDetails(), product.getPrice() + ""));
				tableView.refresh();
				MainController.showAlert("Producto agregado", "INFORMACIÓN", AlertType.INFORMATION);
			} catch (NumberFormatException e) {
				e.printStackTrace();
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
		if (quantityField.getText().isEmpty())
			errorMessage += "Debes ingresar la cantidad\n";
		else
			try {
				Integer.parseInt(quantityField.getText());
			} catch (Exception e) {
				errorMessage += "La cantidad debe ser numérica\n";
			}
		if (priceField.getText().isEmpty())
			errorMessage += "Debes ingresar el precio\n";
		else
			try {
				Double.parseDouble(priceField.getText());
			} catch (Exception e) {
				errorMessage += "El precio debe ser numérico\n";
			}
		if (detailArea.getText().isEmpty())
			errorMessage += "Debes ingresar el detalle";
		if (errorMessage.isEmpty())
			isValid = true;
		else
			MainController.showAlert(errorMessage, "ADVERTENCIA", AlertType.WARNING);
		return isValid;
	}

	private ProductObservable productObservableSelected;

	@FXML
	void handleSelectButton(ActionEvent event) {
		if (!tableView.getSelectionModel().isEmpty()) {
			productObservableSelected = tableView.getSelectionModel().getSelectedItem();
			MainController.showAlert("Producto: " + productObservableSelected.getName() + " seleccionado",
					"INFORMACIÓN", AlertType.INFORMATION);
			nameField.setText(productObservableSelected.getName().get());
			quantityField.setText(productObservableSelected.getQuantity().get());
			detailArea.setText(productObservableSelected.getDetails().get());
			priceField.setText(productObservableSelected.getPrice().get());

		} else {
			MainController.showAlert("Debes seleccionar un producto", "ADVERTENCIA", AlertType.WARNING);
		}
	}

	@FXML
	void handleRemoveButton(ActionEvent event) {
		if (!tableView.getSelectionModel().isEmpty()) {
			productObservableSelected = tableView.getSelectionModel().getSelectedItem();
			try {
				admin.removeProduct(Integer.parseInt(productObservableSelected.getCode().get()));
				MainController.showAlert("Producto: " + productObservableSelected.getName().get() + " eliminado",
						"ADVERTENCIA", AlertType.WARNING);
				iniTableView();
				nameField.setText(productObservableSelected.getName().get());
				quantityField.setText(productObservableSelected.getQuantity().get());
				detailArea.setText(productObservableSelected.getDetails().get());
				priceField.setText(productObservableSelected.getPrice().get());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (NonexistentEntityException e) {
				MainController.showAlert(e.getMessage(), "ERROR", AlertType.ERROR);
			}

		} else {
			MainController.showAlert("Debes seleccionar un producto", "ADVERTENCIA", AlertType.WARNING);
		}
	}

	@FXML
	void handleUpdateButton(ActionEvent event) {

		if (isInputValid()) {
			Product product = new Product(Integer.parseInt(productObservableSelected.getCode().get()),
					Integer.parseInt(quantityField.getText()),
					nameField.getText(), detailArea.getText(),
					Double.parseDouble(priceField.getText()));
			admin.updateProduct(product);
			iniTableView();
			MainController.showAlert("Producto: " + product.getName() + " modificado", "INFORMACIÓN",
					AlertType.INFORMATION);
			nameField.setText("");
			quantityField.setText("");
			detailArea.setText("");
			priceField.setText("");
		}
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert detailArea != null : "fx:id=\"detailArea\" was not injected: check your FXML file 'ProductAdminView.fxml'.";
		assert nameField != null : "fx:id=\"nameField\" was not injected: check your FXML file 'ProductAdminView.fxml'.";
		assert quantityField != null : "fx:id=\"quantityField\" was not injected: check your FXML file 'ProductAdminView.fxml'.";
		assert priceField != null : "fx:id=\"priceField\" was not injected: check your FXML file 'ProductAdminView.fxml'.";
		assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'ProductAdminView.fxml'.";
		assert codeColumn != null : "fx:id=\"codeColumn\" was not injected: check your FXML file 'ProductAdminView.fxml'.";
		assert nameColumn != null : "fx:id=\"nameColumn\" was not injected: check your FXML file 'ProductAdminView.fxml'.";
		assert detailColumn != null : "fx:id=\"detailColumn\" was not injected: check your FXML file 'ProductAdminView.fxml'.";
		assert quantityColumn != null : "fx:id=\"quantityColumn\" was not injected: check your FXML file 'ProductAdminView.fxml'.";
		assert priceColumn != null : "fx:id=\"priceColumn\" was not injected: check your FXML file 'ProductAdminView.fxml'.";
	}

	public void iniTableView() {
		tableView.getItems().clear();
		codeColumn.setCellValueFactory(cellData -> cellData.getValue().getCode());
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
		detailColumn.setCellValueFactory(cellData -> cellData.getValue().getDetails());
		quantityColumn.setCellValueFactory(cellData -> cellData.getValue().getQuantity());
		priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPrice());
		loadDataProducts();
		tableView.setItems(productData);
		tableView.refresh();
	}

	private AdministratorDelegate admin = new AdministratorDelegate();
	private ObservableList<ProductObservable> productData = FXCollections.observableArrayList();

	public void loadDataProducts() {
		List<Product> productList = admin.getAllProducts();
		for (Product product : productList) {
			productData.add(new ProductObservable(product.getCode() + "", product.getQuantity() + "", product.getName(),
					product.getDetails(), product.getPrice() + ""));
		}
	}

	public AdminMenuViewController getLastView() {
		return lastView;
	}

	public void setLastView(AdminMenuViewController lastView) {
		this.lastView = lastView;
		iniTableView();
	}
}
