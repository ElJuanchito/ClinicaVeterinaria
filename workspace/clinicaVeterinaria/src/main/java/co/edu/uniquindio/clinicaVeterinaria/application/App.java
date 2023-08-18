package co.edu.uniquindio.clinicaVeterinaria.application;

import java.io.IOException;
import java.util.HashMap;

import co.edu.uniquindio.clinicaVeterinaria.exceptions.EscenaNotFoundException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	private static HashMap<ESCENA, Scene> escenas;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws IOException {
		stage.setScene(new Scene(loadFXML("principal"), 640, 480));
		stage.show();
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(
				App.class.getResource("/co/edu/uniquindio/clinicaVeterinaria/view/" + fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void cargarEscenas(Runnable actionOnFinished) {
		// TODO
		// TODO
		// TODO
		// TODO
		// TODO
		// TODO
		// TODO
		actionOnFinished.run();
	}

	public static void irA(ESCENA escena) throws EscenaNotFoundException {
		Scene escenaEncontrada = escenas.getOrDefault(escena, null);
		if (escenaEncontrada == null)
			throw new EscenaNotFoundException("La escena seleccionada no fue encontrada");
		// TODO
		// TODO
		// TODO
		// TODO
		// TODO
		// TODO
		// TODO
	}

	public static enum ESCENA {
		MAIN, INICIO;
	}

}