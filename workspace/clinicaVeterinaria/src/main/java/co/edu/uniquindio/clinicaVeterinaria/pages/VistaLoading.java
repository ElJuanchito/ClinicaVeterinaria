package co.edu.uniquindio.clinicaVeterinaria.pages;

import static co.edu.uniquindio.clinicaVeterinaria.application.App.loadFXML;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import one.jpro.routing.View;

public class VistaLoading extends View {
	private BorderPane loadscreen;

	public VistaLoading() {
		loadscreen = (BorderPane) loadFXML("loadScreen");
	}

	@Override
	public Node content() {
		return loadscreen;
	}

	@Override
	public boolean fullscreen() {
		return true;
	}

	@Override
	public String description() {
		return "Es la pantalla de carga de la clinica veterinaria Patitas Peludas";
	}

	@Override
	public String title() {
		return "Patitas Peludas | Cargando...";
	}
}
