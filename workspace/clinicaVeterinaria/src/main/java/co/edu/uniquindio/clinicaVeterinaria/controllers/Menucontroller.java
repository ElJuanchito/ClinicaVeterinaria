package co.edu.uniquindio.clinicaVeterinaria.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

public class Menucontroller {

	private boolean estaPerfilDesplegado = false;
	private boolean estaMenuDesplegado = false;
	private RotateTransition animacionRotarPerfil;
	private ParallelTransition animacionRotarMenu;
	private Timeline animacionRotarMenuMid;
	private Timeline animacionRotarMenuMid2;
	private Timeline animacionRotarMenuInf;

	@FXML
	private SVGPath menuIzq;
	@FXML
	private Circle circulo;

	@FXML
	private Rectangle rectanguloMid;

	@FXML
	private Rectangle rectanguloInf;

	@FXML
	private ImageView imgVeterinario;

	@FXML
	private SVGPath trianguloDesplieguePerfil;
	private Timeline animacionUnHoverCirculo;

	@FXML
	void eventoEnteredMenuAnimacion(MouseEvent event) {
		Group grupo = (Group) event.getSource();
		try {
			Circle circulo = (Circle) grupo.getChildren().get(0);
			Timeline animacionHoverCirculo = new Timeline();
			animacionHoverCirculo.getKeyFrames().add(new KeyFrame(Duration.millis(100),
					new KeyValue(circulo.opacityProperty(), event.getButton() == MouseButton.NONE ? 0.2 : 0.5)));
			animacionHoverCirculo.playFromStart();
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void eventoExitedMenuAnimacion(MouseEvent event) {
		Group grupo = (Group) event.getSource();
		try {
			Circle circulo = (Circle) grupo.getChildren().get(0);
			Timeline animacionHoverCirculo = new Timeline();
			animacionHoverCirculo.getKeyFrames().add(new KeyFrame(Duration.millis(100),
					new KeyValue(circulo.opacityProperty(), event.getButton() == MouseButton.NONE ? 0 : 0.2)));
			animacionHoverCirculo.playFromStart();
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void eventoPressedMenuAnimacion(MouseEvent event) {
		Group grupo = (Group) event.getSource();
		try {

			Circle circulo = (Circle) grupo.getChildren().get(0);
			Timeline animacionHoverCirculo = new Timeline();
			animacionHoverCirculo.getKeyFrames()
					.add(new KeyFrame(Duration.millis(100), new KeyValue(circulo.opacityProperty(), 0.5)));
			animacionHoverCirculo.playFromStart();
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void eventoReleasedMenuAnimacion(MouseEvent event) {
		Group grupo = (Group) event.getSource();
		try {
			Circle circulo = (Circle) grupo.getChildren().get(0);
			Timeline animacionHoverCirculo = new Timeline();
			animacionHoverCirculo.getKeyFrames().add(new KeyFrame(Duration.millis(100),
					new KeyValue(circulo.opacityProperty(), grupo.contains(event.getX(), event.getY()) ? 0.2 : 0)));
			animacionHoverCirculo.playFromStart();
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void eventoDespliegueMenu(MouseEvent event) {
		desplegarMenuAction();
	}

	@FXML
	void eventoDesplieguePerfil(MouseEvent event) {
		desplegarPerfilAction();
	}

	@FXML
	void initialize() {
		animacionRotarMenuMid = new Timeline();
		animacionRotarMenuMid2 = new Timeline();
		animacionRotarMenuInf = new Timeline();
		animacionRotarMenu = new ParallelTransition(animacionRotarMenuMid, animacionRotarMenuMid2,
				animacionRotarMenuInf);
		animacionRotarPerfil = new RotateTransition(Duration.millis(100), trianguloDesplieguePerfil);
		animacionUnHoverCirculo = new Timeline();
		animacionUnHoverCirculo.getKeyFrames()
				.add(new KeyFrame(Duration.millis(100), new KeyValue(circulo.opacityProperty(), 0.2)));
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

		animacionRotarPerfil.setFromAngle(estaPerfilDesplegado ? 180 : 0);
		animacionRotarPerfil.setToAngle(estaPerfilDesplegado ? 0 : 180);
		animacionRotarPerfil.play();
	}

	private void desplegarMenuAction() {
		estaMenuDesplegado = !estaMenuDesplegado;
		animarBarra();
		// TODO desplegar menu
		// TODO desplegar menu
		// TODO desplegar menu
		// TODO desplegar menu
		// TODO desplegar menu
		// TODO desplegar menu
	}

	private void animarBarra() {
		animacionRotarMenuMid.getKeyFrames().clear();
		animacionRotarMenuMid2.getKeyFrames().clear();
		animacionRotarMenuInf.getKeyFrames().clear();

		animacionRotarMenuMid.getKeyFrames().add(new KeyFrame(Duration.millis(100),
				new KeyValue(rectanguloMid.widthProperty(), estaMenuDesplegado ? 17 : 22)));
		animacionRotarMenuMid2.getKeyFrames().add(new KeyFrame(Duration.millis(100),
				new KeyValue(rectanguloMid.xProperty(), estaMenuDesplegado ? 15 : 0)));
		animacionRotarMenuInf.getKeyFrames().add(new KeyFrame(Duration.millis(100),
				new KeyValue(rectanguloInf.widthProperty(), estaMenuDesplegado ? 32 : 12)));
		animacionRotarMenu.playFromStart();

	}

}
