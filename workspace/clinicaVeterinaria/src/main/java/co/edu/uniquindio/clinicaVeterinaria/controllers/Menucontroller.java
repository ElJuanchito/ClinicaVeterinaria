package co.edu.uniquindio.clinicaVeterinaria.controllers;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

public class Menucontroller {

	private boolean estaPerfilDesplegado = false;
	private boolean estaMenuDesplegado = false;
	private RotateTransition animacionRotarPerfil;
	private RotateTransition animacionRotarMenu;
	@FXML
	private SVGPath menuIzq;
	@FXML
	private Circle circulo;

	@FXML
	private ImageView imgVeterinario;

	@FXML
	private SVGPath trianguloDesplieguePerfil;

	@FXML
	void eventoHoverMenuAnimacion(MouseEvent event) {
		System.out.println("hover");
	}

	@FXML
	void eventoUnHoverMenuAnimacion(MouseEvent event) {
		System.out.println("unhover");
	}

	@FXML
	void eventoDespliegueMenu(MouseEvent event) {
		desplegarMenuAction();
	}

	@FXML
	void eventoDesplieguePerfil(MouseEvent event) {
		desplegarPerfilAction();
	}

	private void desplegarPerfilAction() {
		animarTriangulo();
		// TODO desplegar barra
		// TODO desplegar barra
		// TODO desplegar barra
		// TODO desplegar barra
		// TODO desplegar barra
		estaPerfilDesplegado = !estaPerfilDesplegado;
	}

	private void animarTriangulo() {
		if (animacionRotarPerfil == null)
			animacionRotarPerfil = new RotateTransition(Duration.millis(100), trianguloDesplieguePerfil);

		animacionRotarPerfil.setFromAngle(estaPerfilDesplegado ? 180 : 0);
		animacionRotarPerfil.setToAngle(estaPerfilDesplegado ? 0 : 180);
		animacionRotarPerfil.play();
	}

	private void desplegarMenuAction() {
		animarBarra();
		// TODO desplegar menu
		// TODO desplegar menu
		// TODO desplegar menu
		// TODO desplegar menu
		// TODO desplegar menu
		// TODO desplegar menu
		estaMenuDesplegado = !estaMenuDesplegado;
	}

	private void animarBarra() {
		if (animacionRotarMenu == null)
			animacionRotarMenu = new RotateTransition(Duration.millis(100), menuIzq);

		animacionRotarMenu.setFromAngle(estaMenuDesplegado ? 180 : 0);
		animacionRotarMenu.setToAngle(estaMenuDesplegado ? 0 : 180);
		animacionRotarMenu.play();
	}

}
