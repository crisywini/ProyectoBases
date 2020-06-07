
package co.uniquindio.bases.supermarket.SuperMarketCampestre.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.uniquindio.bases.supermarket.SuperMarketCampestre.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    @FXML
    private BorderPane mainPane;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	loadLogginView();
    }
    private Main main;
    private Stage primaryStage;
    HBox logginView;
    LogginViewController logginViewController;

    public void loadLogginView() {
    	if(logginView==null) {
    		try {
    			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/LogginView.fxml"));
				logginView = (HBox)loader.load();
				logginViewController = loader.getController();
				logginViewController.setMainController(this);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
//    	logginViewController.setMainController(this);
    	mainPane.setCenter(logginView);
    }
	public Main getMain() {
		return main;
	}
	public void setMain(Main main) {
		this.main = main;
	}
	public static void showAlert(String message, String title, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setContentText(message);
		alert.setHeaderText("");
		alert.setTitle(title);
		alert.showAndWait();
	}
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	public void closeStage() {
		primaryStage.hide();
	}
	public void openStage() {
		primaryStage.show();
	}
}
