
package co.uniquindio.bases.supermarket.SuperMarketCampestre.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.exceptions.NonexistentEntityException;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.util.AdministratorDelegate;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.Client;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.Order;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.OrderEnum;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.PaymentType;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.Product;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.observables.ProductObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SalesViewController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="clientNameField"
	private TextField clientNameField; // Value injected by FXMLLoader

	@FXML // fx:id="clientLastNameField"
	private TextField clientLastNameField; // Value injected by FXMLLoader

	@FXML // fx:id="codeProductField"
	private TextField codeProductField; // Value injected by FXMLLoader

	@FXML // fx:id="quantityProductField"
	private TextField quantityProductField; // Value injected by FXMLLoader

	@FXML // fx:id="tableView"
	private TableView<ProductObservable> tableView; // Value injected by FXMLLoader

	@FXML // fx:id="codeProductColumn"
	private TableColumn<ProductObservable, String> codeProductColumn; // Value injected by FXMLLoader

	@FXML // fx:id="productNameColumn"
	private TableColumn<ProductObservable, String> productNameColumn; // Value injected by FXMLLoader

	@FXML // fx:id="productQuantityColumn"
	private TableColumn<ProductObservable, String> productQuantityColumn; // Value injected by FXMLLoader
	@FXML // fx:id="comboType"
	private ComboBox<PaymentType> comboType; // Value injected by FXMLLoader

	@FXML // fx:id="comboOrder"
	private ComboBox<OrderEnum> comboOrder; // Value injected by FXMLLoader

	@FXML // fx:id="addressField"
	private TextField addressField; // Value injected by FXMLLoader

	private ObservableList<ProductObservable> productData = FXCollections.observableArrayList();
	private List<Product> products = new ArrayList<Product>();
	private AdministratorDelegate admin = new AdministratorDelegate();
	private AuxiliarMenuViewController lastView;

	@FXML
	void handleAddClientButton(ActionEvent event) {
		if (isInputClientValid()) {
			admin.addClient(clientNameField.getText(), clientLastNameField.getText());
			MainController.showAlert("El cliente: " + clientNameField.getText() + " ha sido agregado con exito",
					"INFORMACIÓN", AlertType.INFORMATION);
		}
	}

	public boolean isInputClientValid() {
		String errorMessage = "";
		boolean isValid = false;
		if (clientNameField.getText().isEmpty())
			errorMessage += "Debes ingresar el nombre del cliente\n";
		if (clientLastNameField.getText().isEmpty())
			errorMessage += "Debes ingresar el apellido del cliente";
		if (errorMessage.isEmpty())
			isValid = true;
		else
			MainController.showAlert(errorMessage, "ADVERTENCIA", AlertType.WARNING);
		return isValid;
	}

	@FXML
	void handleAddProductButton(ActionEvent event) {
		if (isInputProductValid()) {
			try {
				Product product = admin.getProduct(Integer.parseInt(codeProductField.getText()));
				if (product.getQuantity() >= Integer.parseInt(quantityProductField.getText())) {
					products.add(product);
					ProductObservable productO = new ProductObservable(product.getCode() + "",
							quantityProductField.getText(), product.getName(), product.getDetails(),
							product.getPrice() + "");
					productData.add(productO);
					tableView.refresh();
					product.setQuantity(product.getQuantity() - Integer.parseInt(quantityProductField.getText()));
					admin.updateProduct(product);
				} else
					MainController.showAlert("La cantidad: " + quantityProductField.getText() + " no está disponible",
							"ERROR", AlertType.ERROR);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (NonexistentEntityException e) {
				MainController.showAlert(e.getMessage(), "ERROR", AlertType.ERROR);
			}
		}
	}

	public boolean isInputProductValid() {
		String errorMessage = "";
		boolean isValid = false;
		if (codeProductField.getText().isEmpty())
			errorMessage += "Debes ingresar el código del producto\n";
		if (quantityProductField.getText().isEmpty())
			errorMessage += "Debes ingresar la cantidad a vender\n";
		if (errorMessage.isEmpty())
			isValid = true;
		else
			MainController.showAlert(errorMessage, "ADVERTENCIA", AlertType.WARNING);
		return isValid;
	}

	@FXML
	void handleSellButton(ActionEvent event) {
		if (!products.isEmpty()) {
			if (comboOrder.getSelectionModel().getSelectedItem() == OrderEnum.SI) {
				if (!addressField.getText().isEmpty()) {
					admin.addOrder(addressField.getText(), lastView.getEmployee().getCode());
					try {
						Order order = admin.getOrder(addressField.getText(), lastView.getEmployee().getCode());
						Client client = admin.getClient(clientNameField.getText(), clientLastNameField.getText());
						admin.addSale(getValue(), client.getCode(), order.getCode());
						MainController.showAlert("Venta realizada", "INFORMACIÓN", AlertType.INFORMATION);
						tableView.getItems().clear();
						tableView.refresh();
						products.clear();
					} catch (NonexistentEntityException e) {
						MainController.showAlert(e.getMessage(), "ERROR", AlertType.ERROR);
					}
				} else
					MainController.showAlert("Debes ingresar la dirección", "ADVERTENCIA", AlertType.WARNING);
			} else {
				try {
					Client client = admin.getClient(clientNameField.getText(), clientLastNameField.getText());
					admin.addSale(getValue(), client.getCode(), 0);
					MainController.showAlert("Venta realizada", "INFORMACIÓN", AlertType.INFORMATION);
					tableView.getItems().clear();
					tableView.refresh();
					products.clear();
				} catch (NonexistentEntityException e) {
					MainController.showAlert(e.getMessage(), "ERROR", AlertType.ERROR);
				}
			}
		} else
			MainController.showAlert("Debes tener productos en el carrito", "ADVERTENCIA", AlertType.WARNING);
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert clientNameField != null : "fx:id=\"clientNameField\" was not injected: check your FXML file 'SalesView.fxml'.";
		assert clientLastNameField != null : "fx:id=\"clientLastNameField\" was not injected: check your FXML file 'SalesView.fxml'.";
		assert codeProductField != null : "fx:id=\"codeProductField\" was not injected: check your FXML file 'SalesView.fxml'.";
		assert quantityProductField != null : "fx:id=\"quantityProductField\" was not injected: check your FXML file 'SalesView.fxml'.";
		assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'SalesView.fxml'.";
		assert codeProductColumn != null : "fx:id=\"codeProductColumn\" was not injected: check your FXML file 'SalesView.fxml'.";
		assert productNameColumn != null : "fx:id=\"productNameColumn\" was not injected: check your FXML file 'SalesView.fxml'.";
		assert productQuantityColumn != null : "fx:id=\"productQuantityColumn\" was not injected: check your FXML file 'SalesView.fxml'.";
	}

	public void initTableView() {
		codeProductColumn.setCellValueFactory(cellData -> cellData.getValue().getCode());
		productNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
		productQuantityColumn.setCellValueFactory(cellData -> cellData.getValue().getQuantity());
		tableView.setItems(productData);
		tableView.refresh();
	}

	public void initComboOrder() {
		ObservableList<OrderEnum> orderData = FXCollections.observableArrayList();
		orderData.add(OrderEnum.NO);
		orderData.add(OrderEnum.SI);
		comboOrder.setItems(orderData);
	}

	public void initComboType() {
		ObservableList<PaymentType> paysData = FXCollections.observableArrayList();
		List<PaymentType> pays = admin.getAllPaymentType();
		for (PaymentType paymentType : pays) {
			paysData.add(paymentType);
		}
		comboType.setItems(paysData);
	}

	public AuxiliarMenuViewController getLastView() {
		return lastView;
	}

	public void setLastView(AuxiliarMenuViewController lastView) {
		this.lastView = lastView;
		initComboOrder();
		initComboType();
		initTableView();
	}

	public double getValue() {
		double value = 0.0;
		for (ProductObservable productObservable : productData) {
			value = Double.parseDouble(productObservable.getPrice().get())
					* Integer.parseInt(productObservable.getQuantity().get());
		}
		return value;
	}

}
