package co.edu.uniquindio.clinicaVeterinaria.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class Menucontroller {

	@FXML
	private Circle circulo;

	@FXML
	private ImageView imgVeterinario;

	@FXML
	void eventoDespliegueMenu(MouseEvent event) {
		System.out.println("despliegue menu");
	}

	@FXML
	void eventoHoverMenuAnimacion(MouseEvent event) {
		System.out.println("hover");
	}

	@FXML
	void eventoUnHoverMenuAnimacion(MouseEvent event) {
		System.out.println("unhover");
	}

	@FXML
	void eventoDesplieguePerfil(MouseEvent event) {
System.out.println("despliegue perfil");
	}
}
