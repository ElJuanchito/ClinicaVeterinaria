package co.edu.uniquindio.clinicaVeterinaria.application;

import static one.jpro.routing.RouteUtils.getNode;

import java.io.IOException;
import java.util.HashMap;

import co.edu.uniquindio.clinicaVeterinaria.exceptions.EscenaNotFoundException;
import co.edu.uniquindio.clinicaVeterinaria.services.Pestanas;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import one.jpro.routing.Route;
import one.jpro.routing.RouteApp;

/**
 * JavaFX App
 */
public class App extends RouteApp {

	private static HashMap<Pestanas, Parent> escenas = new HashMap<>();
	private static BorderPane panel;
	private static BorderPane root = new BorderPane();
	private Pestanas pestanaActual;

	public static void main(String[] args) {
		launch(args);
	}

	public App() {
		System.out.println("App.App()");
		Platform.runLater(() -> {
			try {
				root.setCenter(loadFXML("loadScreen"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(
				App.class.getResource("/co/edu/uniquindio/clinicaVeterinaria/view/" + fxml + ".fxml"));
		System.out.println("/co/edu/uniquindio/clinicaVeterinaria/view/" + fxml + ".fxml");
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

	private static void cambiarEscenaEx(Pestanas escena) throws EscenaNotFoundException {
		Parent escenaEncontrada = escenas.getOrDefault(escena, null);
		if (escenaEncontrada == null)
			throw new EscenaNotFoundException("La escena seleccionada no fue encontrada");
		if (escena == Pestanas.LOGIN) {
			Platform.runLater(() -> root.setCenter(escenaEncontrada));
			return;
		}
		if (escena == Pestanas.INICIO) {
			root.setCenter(escenaEncontrada);
			panel = ((BorderPane) ((BorderPane) escenaEncontrada).getCenter());
			return;
		}
		if (panel == null)
			return;
		panel.setCenter(escenaEncontrada);
	}

	@Override
	public Route createRoute() {
		return Route.empty().and(getNode("/", r -> root)).and(getNode("/inicio", r -> obtenerEscena(Pestanas.INICIO)))
				.and(getNode("/login", r -> obtenerEscena(Pestanas.LOGIN)))
				.and(getNode("/cliente", r -> obtenerEscena(Pestanas.CLIENTE)))
				.and(getNode("/mascota", r -> obtenerEscena(Pestanas.MASCOTA)))
				.and(getNode("/cita", r -> obtenerEscena(Pestanas.CITA)))
				.and(getNode("/factura", r -> obtenerEscena(Pestanas.FACTURA)))
				.and(getNode("/mas", r -> obtenerEscena(Pestanas.MORE)));
	}

	private BorderPane obtenerEscena(Pestanas pestana) {
		if (this.pestanaActual == pestana)
			return root;
		try {
			cambiarEscenaEx(pestana);
		} catch (EscenaNotFoundException e) {
			e.printStackTrace();
		}
		return root;
	}


}