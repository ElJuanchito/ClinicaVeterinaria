package co.edu.uniquindio.clinicaVeterinaria.pages;

import static co.edu.uniquindio.clinicaVeterinaria.application.App.loadFXML;

import java.util.HashMap;

import co.edu.uniquindio.clinicaVeterinaria.exceptions.EscenaNotFoundException;
import co.edu.uniquindio.clinicaVeterinaria.services.PestanasMenu;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import one.jpro.routing.View;

public class VistaMenu extends View {
	private HashMap<PestanasMenu, Parent> escenas = new HashMap<>();
	private PestanasMenu pestanaActual = PestanasMenu.INICIO;
	private BorderPane menuNode;
	private ImageView imgLogo;

	public VistaMenu() {
		menuNode = (BorderPane) loadFXML("menu");
		imgLogo = (ImageView) ((HBox) ((BorderPane) ((BorderPane) menuNode.getCenter()).getCenter()).getCenter())
				.getChildren().get(0);
	}

	@Override
	public Node content() {
		return menuNode;
	}

	@Override
	public boolean fullscreen() {
		return true;
	}

	@Override
	public String description() {
		return "Es el menu principal de la clinica veterinaria Patitas Peludas";
	}

	@Override
	public String title() {
		return "Patitas Peludas | Menu Principal";
	}

	public void cargarMenus() {
		PestanasMenu[] pestanas = PestanasMenu.values();
		for (PestanasMenu pestana : pestanas)
			escenas.put(pestana, loadFXML(pestana.getFxml()));
	}

	public VistaMenu cambiarPestana(PestanasMenu pestana) {
		if (pestanaActual == pestana)
			return this;
		try {
			cambiarEscenaEx(pestana);
			pestanaActual = pestana;
		} catch (EscenaNotFoundException e) {
			e.printStackTrace();
		}
		return this;
	}

	private void cambiarEscenaEx(PestanasMenu escena) throws EscenaNotFoundException {
		Parent escenaEncontrada = escenas.getOrDefault(escena, null);
		if (escenaEncontrada == null)
			throw new EscenaNotFoundException("La escena seleccionada no fue encontrada");
		if (escena == PestanasMenu.INICIO) {
			((BorderPane) ((BorderPane) menuNode.getCenter()).getCenter()).setCenter(imgLogo);
			return;
		}
		Platform.runLater(
				() -> ((BorderPane) ((BorderPane) menuNode.getCenter()).getCenter()).setCenter(escenaEncontrada));
	}
}
