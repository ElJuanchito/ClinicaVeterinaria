package co.edu.uniquindio.clinicaVeterinaria.tests;

import java.io.IOException;

import co.edu.uniquindio.clinicaVeterinaria.application.App;
import co.edu.uniquindio.clinicaVeterinaria.controllers.ModelFactoryController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestVentanas extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ModelFactoryController.getInstance().saveData();
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Scene scena = new Scene(loadFXML("profileSelector"));
		//scena.getStylesheets().add(getClass().getResource("/co/edu/uniquindio/clinicaVeterinaria/styles/textStyle.css").toExternalForm());
		primaryStage.setScene(scena);
		primaryStage.setFullScreen(true);
		primaryStage.show();
		
	}
	
	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(
				App.class.getResource("/co/edu/uniquindio/clinicaVeterinaria/view/" + fxml + ".fxml"));
		return fxmlLoader.load();
	}

}
