package co.edu.uniquindio.clinicaVeterinaria.application;

import java.io.IOException;
import java.util.HashMap;

import co.edu.uniquindio.clinicaVeterinaria.exceptions.EscenaNotFoundException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	private static HashMap<ESCENA, Parent> escenas = new HashMap<>();
	private static Scene scena;
	private static BorderPane panel;

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

	public static void cargarEscenas(Runnable accionTerminado) {
		try {
			cargarEscena(ESCENA.INICIO, "principal");
			cargarEscena(ESCENA.LOGIN, "profileSelector");
			Platform.runLater(() -> accionTerminado.run());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void cargarEscena(ESCENA escena, String fxml) throws IOException {
		escenas.put(escena, loadFXML(fxml));
	}

	public static void cambiarEscena(ESCENA escena) throws EscenaNotFoundException {
		Parent escenaEncontrada = escenas.getOrDefault(escena, null);
		if (escenaEncontrada == null)
			throw new EscenaNotFoundException("La escena seleccionada no fue encontrada");
		if (escena == ESCENA.LOGIN) {
			scena.setRoot(escenaEncontrada);
			return;
		}
		if (escena == ESCENA.INICIO) {
			scena.setRoot(escenaEncontrada);
			panel = (BorderPane) escenaEncontrada;
			return;
		}
		if (panel == null)
			return;
		panel.setCenter(escenaEncontrada);
	}

	public static enum ESCENA {
		INICIO, LOGIN;
	}

}