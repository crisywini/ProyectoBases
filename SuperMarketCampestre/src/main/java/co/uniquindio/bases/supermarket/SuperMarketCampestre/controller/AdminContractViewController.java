/**
 * Sample Skeleton for 'AdminContractView.fxml' Controller Class
 */

package co.uniquindio.bases.supermarket.SuperMarketCampestre.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private ComboBox<?> comboContractType; // Value injected by FXMLLoader

    @FXML // fx:id="idEmployeeField"
    private TextField idEmployeeField; // Value injected by FXMLLoader

    @FXML // fx:id="comboJob"
    private ComboBox<?> comboJob; // Value injected by FXMLLoader

    @FXML // fx:id="tableView"
    private TableView<?> tableView; // Value injected by FXMLLoader

    @FXML // fx:id="codeColumn"
    private TableColumn<?, ?> codeColumn; // Value injected by FXMLLoader

    @FXML // fx:id="idEmployeeColumn"
    private TableColumn<?, ?> idEmployeeColumn; // Value injected by FXMLLoader

    @FXML // fx:id="contractTypeColumn"
    private TableColumn<?, ?> contractTypeColumn; // Value injected by FXMLLoader

    @FXML // fx:id="startDateColumn"
    private TableColumn<?, ?> startDateColumn; // Value injected by FXMLLoader

    @FXML // fx:id="endDateColumn"
    private TableColumn<?, ?> endDateColumn; // Value injected by FXMLLoader

    @FXML // fx:id="jobColumn"
    private TableColumn<?, ?> jobColumn; // Value injected by FXMLLoader

    @FXML // fx:id="salaryColumn"
    private TableColumn<?, ?> salaryColumn; // Value injected by FXMLLoader

    @FXML
    void handleAddButton(ActionEvent event) {

    }

    @FXML
    void handleRemoveButton(ActionEvent event) {

    }

    @FXML
    void handleSelectButton(ActionEvent event) {

    }

    @FXML
    void handleUpdateButton(ActionEvent event) {

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
}
