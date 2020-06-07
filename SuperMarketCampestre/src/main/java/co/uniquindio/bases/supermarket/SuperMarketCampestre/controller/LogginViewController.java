
package co.uniquindio.bases.supermarket.SuperMarketCampestre.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.uniquindio.bases.supermarket.SuperMarketCampestre.Main;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.exceptions.NonexistentEntityException;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.database.util.AdministratorDelegate;
import co.uniquindio.bases.supermarket.SuperMarketCampestre.entities.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LogginViewController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;
    @FXML
    private ImageView image;
    @FXML
    private TextField userField;

    @FXML
    private PasswordField passwordField;

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		image.setImage(new Image("file:src/main/resources/images/nombreSuperMarket.png"));	
		
	}
	private MainController mainController;
	private AdministratorDelegate admin = new AdministratorDelegate();
	public MainController getMainController() {
		return mainController;
	}
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	@FXML
	void handleLogginButton(ActionEvent event) {
		if(isInputValid()) {
			try {
				Employee employee = admin.loggin(userField.getText(), passwordField.getText());
				userField.setText("");
				passwordField.setText("");
				mainController.closeStage();
				int job = employee.getCode_job();
				MainController.showAlert("Bienvenido: "+employee.getEmail(), "INFORMACIÓN", AlertType.INFORMATION);
				if(job == 1) {
					loadAdminMenu(employee);
				}
				if(job==2) {					
					loadAuxiliarMenu(employee);
				}
			} catch (NonexistentEntityException e) {
				MainController.showAlert(e.getMessage(), "ERROR", AlertType.ERROR);
			}
		}
	}
	public void loadAuxiliarMenu(Employee employee) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AuxiliarMenuView.fxml"));
			Parent parent = loader.load();
			AuxiliarMenuViewController controller = loader.getController();
			controller.setEmployee(employee);
			controller.setLastView(this);
			Scene scene = new Scene(parent);
			Stage stage =new Stage();
			stage.setScene(scene);
			stage.setTitle("Super Market Campestre");
			controller.setStage(stage);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void loadAdminMenu(Employee employee) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AdminMenuView.fxml"));
			Parent parent = loader.load();
			AdminMenuViewController controller = loader.getController();
			controller.setEmployee(employee);
			controller.setLastView(this);
			Scene scene = new Scene(parent);
			Stage stage =new Stage();
			stage.setScene(scene);
			stage.setTitle("Super Market Campestre");
			controller.setStage(stage);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public boolean isInputValid() {
		String errorMessage = "";
		boolean isValid = false;
		if(userField.getText().isEmpty())
			errorMessage += "Debes ingresar el usuario\n";
		if(passwordField.getText().isEmpty())
			errorMessage+="Debes ingresar la contraseña";
		if(errorMessage.isEmpty())
			isValid = true;
		else
			MainController.showAlert(errorMessage, "ADVERTENCIA", AlertType.WARNING);
		return isValid;
	}
	public void openStage() {
		mainController.openStage();
	}

}
