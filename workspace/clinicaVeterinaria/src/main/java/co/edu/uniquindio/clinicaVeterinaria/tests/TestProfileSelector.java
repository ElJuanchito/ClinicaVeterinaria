package co.edu.uniquindio.clinicaVeterinaria.tests;

import java.io.IOException;

import co.edu.uniquindio.clinicaVeterinaria.application.App;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestProfileSelector extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Scene scena = new Scene(loadFXML("profileSelector"), 1000, 480);
		primaryStage.setScene(scena);
		primaryStage.show();
		
	}
	
	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(
				App.class.getResource("/co/edu/uniquindio/clinicaVeterinaria/view/" + fxml + ".fxml"));
		return fxmlLoader.load();
	}

}
