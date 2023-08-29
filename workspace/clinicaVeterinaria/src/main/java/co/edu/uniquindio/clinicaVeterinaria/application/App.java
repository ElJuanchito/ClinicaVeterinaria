package co.edu.uniquindio.clinicaVeterinaria.application;

import static one.jpro.routing.RouteUtils.get;

import java.io.IOException;

import co.edu.uniquindio.clinicaVeterinaria.pages.VistaLoading;
import co.edu.uniquindio.clinicaVeterinaria.pages.VistaLogin;
import co.edu.uniquindio.clinicaVeterinaria.pages.VistaMenu;
import co.edu.uniquindio.clinicaVeterinaria.services.PestanasMenu;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import one.jpro.routing.Route;
import one.jpro.routing.RouteApp;

/**
 * JavaFX App
 */
public class App extends RouteApp {

	private VistaMenu menuPrincipal;
	private VistaLogin vistaLogin;
	private VistaLoading vistaLoading;
	private static App app;

	public static void main(String[] args) {
		launch(args);
	}

	public App() {
		app = this;
		vistaLoading = new VistaLoading();
	}

	public static Parent loadFXML(String fxml) {
		FXMLLoader fxmlLoader = new FXMLLoader(
				App.class.getResource("/co/edu/uniquindio/clinicaVeterinaria/view/" + fxml + ".fxml"));
		try {
			return fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void cargarEscenas(Runnable accionTerminado) {
		app.cargarEscenasObj(accionTerminado);
	}

	private void cargarEscenasObj(Runnable accionTerminado) {
		vistaLogin = new VistaLogin();
		menuPrincipal = new VistaMenu();
		menuPrincipal.cargarMenus();
		Platform.runLater(() -> {
			applyScrollPaneCssObj();
			accionTerminado.run();
		});
	}

	public static void applyScrollPaneCss() {
		app.applyScrollPaneCssObj();
	}

	private void applyScrollPaneCssObj() {
		((StackPane) (getScene().getRoot())).setAlignment(Pos.CENTER);
		getScene().getRoot().getStylesheets().add(getClass()
				.getResource("/co/edu/uniquindio/clinicaVeterinaria/styles/scrollPaneStyle.css").toExternalForm());
		getScene().getRoot().applyCss();
	}

	@Override
	public Route createRoute() {
		return Route.empty()
				.and(get("/", r -> vistaLoading))
				.and(get("/login", r -> vistaLogin))
				.and(get("/inicio", r -> menuPrincipal.cambiarPestana(PestanasMenu.INICIO)))
				.and(get("/cliente", r -> menuPrincipal.cambiarPestana(PestanasMenu.CLIENTE)))
				.and(get("/mascota", r -> menuPrincipal.cambiarPestana(PestanasMenu.MASCOTA)))
				.and(get("/cita", r -> menuPrincipal.cambiarPestana(PestanasMenu.CITA)))
				.and(get("/factura", r -> menuPrincipal.cambiarPestana(PestanasMenu.FACTURA)))
				.and(get("/histoial", r -> menuPrincipal.cambiarPestana(PestanasMenu.FACTURA)))
				.and(get("/mas", r -> menuPrincipal.cambiarPestana(PestanasMenu.MORE)))
				.and(get("/historialCitas", r -> menuPrincipal.cambiarPestana(PestanasMenu.HCITAS)))
				.and(get("/historialClinico", r -> menuPrincipal.cambiarPestana(PestanasMenu.HCLINICO)));
	}
	
	public void cargarFont() {
		Font.loadFont(getClass().getResource("/co/edu/uniquindio/clinicaVeterinaria/sources/fonts/Rubik-SemiBold.ttf").toExternalForm(), 600);
	}

}