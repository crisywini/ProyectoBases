package co.uniquindio.bases.supermarket.SuperMarketCampestre.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.util.AdministratorDelegate;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.Product;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.observables.ProductObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AuxiliarProductViewController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

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

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'AuxiliarProductView.fxml'.";
		assert codeColumn != null : "fx:id=\"codeColumn\" was not injected: check your FXML file 'AuxiliarProductView.fxml'.";
		assert nameColumn != null : "fx:id=\"nameColumn\" was not injected: check your FXML file 'AuxiliarProductView.fxml'.";
		assert detailColumn != null : "fx:id=\"detailColumn\" was not injected: check your FXML file 'AuxiliarProductView.fxml'.";
		assert quantityColumn != null : "fx:id=\"quantityColumn\" was not injected: check your FXML file 'AuxiliarProductView.fxml'.";
		assert priceColumn != null : "fx:id=\"priceColumn\" was not injected: check your FXML file 'AuxiliarProductView.fxml'.";

	}

	public void initTableView() {
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

	private AuxiliarMenuViewController currentView;
	private AdministratorDelegate admin = new AdministratorDelegate();
	private ObservableList<ProductObservable> productData = FXCollections.observableArrayList();

	public void loadDataProducts() {
		List<Product> productList = admin.getAllProducts();
		for (Product product : productList) {
			productData.add(new ProductObservable(product.getCode() + "", product.getQuantity() + "", product.getName(),
					product.getDetails(), product.getPrice() + ""));
		}
	}

	public AuxiliarMenuViewController getCurrentView() {
		return currentView;
	}

	public void setCurrentView(AuxiliarMenuViewController currentView) {
		this.currentView = currentView;
		initTableView();
	}
}
