
package co.uniquindio.bases.supermarket.SuperMarketCampestre.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.uniquindio.bases.supermarket.SuperMarketCampestre.observables.ProductObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    @FXML
    void handleAddClientButton(ActionEvent event) {

    }

    @FXML
    void handleAddProductButton(ActionEvent event) {

    }

    @FXML
    void handleSellButton(ActionEvent event) {

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
}
