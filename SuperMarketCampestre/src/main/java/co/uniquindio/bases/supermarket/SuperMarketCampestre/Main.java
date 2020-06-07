package co.uniquindio.bases.supermarket.SuperMarketCampestre;

import java.io.IOException;

import co.uniquindio.bases.supermarket.SuperMarketCampestre.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			loadMain(primaryStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	public void loadMain(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("/MainView.fxml"));
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		parent.getStyleClass().add("pane");
		MainController controller = loader.getController();
		controller.setMain(this);
		controller.setPrimaryStage(primaryStage);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Super Market Campestre");
		primaryStage.show();
	}
}
