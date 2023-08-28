package co.edu.uniquindio.clinicaVeterinaria.pages;

import static co.edu.uniquindio.clinicaVeterinaria.application.App.loadFXML;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import one.jpro.routing.View;

public class VistaLogin extends View {
	private BorderPane profileSelector;

	public VistaLogin() {
		profileSelector = (BorderPane) loadFXML("profileSelector");
	}

	@Override
	public Node content() {
		return profileSelector;
	}

	@Override
	public boolean fullscreen() {
		return true;
	}

	@Override
	public String description() {
		return "Es el Inicio de Sesion de la clinica veterinaria Patitas Peludas";
	}

	@Override
	public String title() {
		return "Patitas Peludas | Iniciar Sesion";
	}
}
