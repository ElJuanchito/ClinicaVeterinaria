package co.edu.uniquindio.clinicaVeterinaria.controllers;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;
import one.jpro.routing.LinkUtil;
import one.jpro.routing.LinkUtil.ExtendNodeWithLink;

public class Menucontroller {

	private boolean estaPerfilDesplegado = false;
	private boolean estaMenuDesplegado = false;
	private RotateTransition animacionRotarPerfil;

	@FXML
	private ScrollPane btnCasita;

	@FXML
	private ScrollPane btnMascota;

	@FXML
	private ScrollPane btnFactura;
	@FXML
	private ScrollPane btnUsuario;

	@FXML
	private ScrollPane btnCita;

	@FXML
	private ScrollPane btnMas;

	@FXML
	private VBox menuIzq;

	@FXML
	private Rectangle rectanguloMid;

	@FXML
	private Rectangle rectanguloInf;

	@FXML
	private ImageView imgVeterinario;

	@FXML
	private SVGPath trianguloDesplieguePerfil;

	@FXML
	void eventoEnteredMenuAnimacion(MouseEvent event) {
		ejecutarAnimacionBotonCircular(event, 0.2);
	}

	@FXML
	void eventoExitedMenuAnimacion(MouseEvent event) {
		ejecutarAnimacionBotonCircular(event, event.getButton() == MouseButton.NONE ? 0 : 0.2);
	}

	@FXML
	void eventoPressedMenuAnimacion(MouseEvent event) {
		ejecutarAnimacionBotonCircular(event, 0.5);
	}

	@FXML
	void eventoReleasedMenuAnimacion(MouseEvent event) {
		ejecutarAnimacionBotonCircularReleased(event);
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
		animacionRotarPerfil = new RotateTransition(Duration.millis(100), trianguloDesplieguePerfil);
		Platform.runLater(() -> {
			LinkUtil.setLink(btnCasita, "/");
			LinkUtil.setLink(btnMascota, "/mascota");
			LinkUtil.setLink(btnUsuario, "/cliente");
			LinkUtil.setLink(btnCita, "/cita");
			LinkUtil.setLink(btnFactura, "/factura");
			LinkUtil.setLink(btnMas, "/mas");
		});
	}

	private void ejecutarAnimacionBotonCircular(MouseEvent event, double endValue) {
		ScrollPane nodo = (ScrollPane) event.getSource();
		Color color = (Color) nodo.getBackground().getFills().get(0).getFill();
		SimpleDoubleProperty prop = new SimpleDoubleProperty(color.getOpacity());
		StringExpression concat = new SimpleStringProperty("-fx-background-color: rgba(0.0,171.0,235.0,")
				.concat(Bindings.format(Locale.US, "%.3f", prop))
				.concat(");-fx-background-radius:100;-fx-min-width: " + nodo.getMinWidth() + ";");
		nodo.styleProperty().bind(concat);

		Timeline animacionHoverCirculo = new Timeline();
		KeyValue keyValue = new KeyValue(prop, endValue);
		animacionHoverCirculo.getKeyFrames().add(new KeyFrame(Duration.millis(100), keyValue));
		animacionHoverCirculo.playFromStart();
	}

	private void ejecutarAnimacionBotonCircularReleased(MouseEvent event) {
		ScrollPane nodo = (ScrollPane) event.getSource();
		Color color = (Color) nodo.getBackground().getFills().get(0).getFill();
		SimpleDoubleProperty prop = new SimpleDoubleProperty(color.getOpacity());
		StringExpression concat = new SimpleStringProperty("-fx-background-color: rgba(0.0,171.0,235.0,")
				.concat(Bindings.format(Locale.US, "%.3f", prop))
				.concat(");-fx-background-radius:100;-fx-min-width: " + nodo.getMinWidth() + ";");

		nodo.styleProperty().bind(concat);
		KeyValue keyValue = new KeyValue(prop, nodo.contains(event.getX(), event.getY()) ? 0.2 : 0);
		Timeline animacionHoverCirculo = new Timeline();
		animacionHoverCirculo.getKeyFrames().add(new KeyFrame(Duration.millis(100), keyValue));
		animacionHoverCirculo.playFromStart();

	}

	private void desplegarPerfilAction() {
		estaPerfilDesplegado = !estaPerfilDesplegado;
		animarTriangulo();
		// TODO desplegar barra
		// TODO desplegar barra
		// TODO desplegar barra
		// TODO desplegar barra
		// TODO desplegar barra

	}

	private void animarTriangulo() {
		animacionRotarPerfil.setFromAngle(estaPerfilDesplegado ? 0 : 180);
		animacionRotarPerfil.setToAngle(estaPerfilDesplegado ? 180 : 0);
		animacionRotarPerfil.play();
	}

	private void desplegarMenuAction() {
		estaMenuDesplegado = !estaMenuDesplegado;
		ejecutarAnimacionMenu();
	}

	private void ejecutarAnimacionMenu() {

		List<KeyValue> keyValues = menuIzq.getChildren().stream()
				.filter(nodo -> nodo.getClass().equals(ScrollPane.class))
				.map(node -> new KeyValue(((ScrollPane) node).minWidthProperty(), estaMenuDesplegado ? 250 : 84))
				.collect(Collectors.toList());
		keyValues.add(new KeyValue(rectanguloMid.widthProperty(), estaMenuDesplegado ? 17 : 22));
		keyValues.add(new KeyValue(rectanguloMid.xProperty(), estaMenuDesplegado ? 15 : 0));
		keyValues.add(new KeyValue(rectanguloInf.widthProperty(), estaMenuDesplegado ? 32 : 12));
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.millis(100), (KeyValue[]) keyValues.toArray(new KeyValue[keyValues.size()])));
		timeline.playFromStart();
	}
}
