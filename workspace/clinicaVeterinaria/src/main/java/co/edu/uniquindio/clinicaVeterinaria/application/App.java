package co.edu.uniquindio.clinicaVeterinaria.application;

import java.io.IOException;
import java.util.HashMap;

import com.jpro.webapi.JProApplication;

import co.edu.uniquindio.clinicaVeterinaria.exceptions.EscenaNotFoundException;
import co.edu.uniquindio.clinicaVeterinaria.services.Pestanas;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends JProApplication {

	private static HashMap<Pestanas, Parent> escenas = new HashMap<>();
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
			Pestanas[] pestanas = Pestanas.values();
			for (Pestanas pestana : pestanas)
				escenas.put(pestana, loadFXML(pestana.getFxml()));
			Platform.runLater(() -> accionTerminado.run());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void cambiarEscena(Pestanas escena) throws EscenaNotFoundException {
		Parent escenaEncontrada = escenas.getOrDefault(escena, null);
		if (escenaEncontrada == null)
			throw new EscenaNotFoundException("La escena seleccionada no fue encontrada");
		if (escena == Pestanas.LOGIN) {
			scena.setRoot(escenaEncontrada);
			return;
		}
		if (escena == Pestanas.INICIO) {
			scena.setRoot(escenaEncontrada);
			panel = ((BorderPane) ((BorderPane) escenaEncontrada).getCenter());
			return;
		}
		if (panel == null)
			return;
		panel.setCenter(escenaEncontrada);
	}

}