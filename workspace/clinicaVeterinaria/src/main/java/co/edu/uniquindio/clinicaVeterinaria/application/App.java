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

	private static HashMap<ESCENA, Parent> escenas = new HashMap<>();
	private static Scene scena;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws IOException {
		scena = new Scene(loadFXML("loadScreen"), 1000, 480);
		stage.setScene(scena);
		stage.show();
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(
				App.class.getResource("/co/edu/uniquindio/clinicaVeterinaria/view/" + fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void cargarEscenas() {
		try {
			escenas.put(ESCENA.INICIO, loadFXML("principal"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void cambiarEscena(ESCENA escena) throws EscenaNotFoundException {
		Parent escenaEncontrada = escenas.getOrDefault(escena, null);
		if (escenaEncontrada == null)
			throw new EscenaNotFoundException("La escena seleccionada no fue encontrada");
		scena.setRoot(escenaEncontrada);
	}

	public static enum ESCENA {
		MAIN, INICIO;
	}

}