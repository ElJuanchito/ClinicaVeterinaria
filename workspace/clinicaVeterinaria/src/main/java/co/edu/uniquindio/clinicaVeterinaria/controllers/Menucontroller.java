package co.edu.uniquindio.clinicaVeterinaria.controllers;

import static one.jpro.routing.LinkUtil.gotoPage;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

public class Menucontroller {

	private boolean estaPerfilDesplegado = false;
	private boolean estaMenuDesplegado = false;
	private ParallelTransition animacionPerfil;
	private static Menucontroller instance;

	public Menucontroller() {
		instance = this;
	}

	@FXML
	private BorderPane overlay;

	@FXML
	private Label lblAlerta;

	@FXML
	private HBox menuDesplegable;

	@FXML
	private ImageView imgLogo;

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
	private Circle imgCircle;

	@FXML
	private SVGPath trianguloDesplieguePerfil;
	@FXML
	private BorderPane centerPane;

	@FXML
	private StackPane btnCerrarSesion;

	@FXML
	void casitaEvent(MouseEvent event) {
		casitaAction();
	}

	@FXML
	void mascotaEvent(MouseEvent event) {
		mascotaAction();
	}

	@FXML
	void usuarioEvent(MouseEvent event) {
		usuarioAction();
	}

	@FXML
	void citaEvent(MouseEvent event) {
		citaAction();
	}

	@FXML
	void facturaEvent(MouseEvent event) {
		facturaAction();
	}

	@FXML
	void masEvent(MouseEvent event) {
		masAction();
	}

	private void casitaAction() {
		gotoPage(btnCasita, "/inicio");
	}

	private void mascotaAction() {
		gotoPage(btnMascota, "/mascota");
	}

	private void usuarioAction() {
		gotoPage(btnUsuario, "/cliente");
	}

	private void citaAction() {
		gotoPage(btnCita, "/cita");
	}

	private void facturaAction() {
		gotoPage(btnFactura, "/factura");
	}

	private void masAction() {
		gotoPage(btnMas, "/mas");
	}

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
	void cerrarSesionEvent(MouseEvent event) {
		cerrarSesionAction();
	}

	@FXML
	void cerrarOverlayEvent(ActionEvent event) {
		cerrarOverlayAction();
	}

	@FXML
	void initialize() {
		RotateTransition animacionRotarPerfil = new RotateTransition(Duration.millis(200), trianguloDesplieguePerfil);
		animacionRotarPerfil.setInterpolator(new Interpolator() {
			
			@Override
			protected double curve(double t) {
				return 0.6 * Math.sin(4.334 * t - 2.18) + 0.5;
			}
		});
		FadeTransition animacionBtn = new FadeTransition(Duration.millis(200), btnCerrarSesion);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames()
				.add(new KeyFrame(Duration.millis(0), new KeyValue(btnCerrarSesion.maxHeightProperty(), 0)));
		timeline.getKeyFrames()
				.add(new KeyFrame(Duration.millis(200), new KeyValue(btnCerrarSesion.maxHeightProperty(), 70)));
		animacionPerfil = new ParallelTransition(animacionRotarPerfil, animacionBtn, timeline);

		animacionRotarPerfil.setFromAngle(0);
		animacionRotarPerfil.setToAngle(180);
		animacionBtn.setFromValue(0);
		animacionBtn.setToValue(1);

		ModelFactoryController.getInstance().getVeterinarioFotoProp()
				.addListener((observable, old, newImage) -> imgCircle.setFill(new ImagePattern(newImage)));
		crearAnimacionCentro();
	}

	private void cerrarSesionAction() {
		Platform.runLater(() -> {
			desplegarPerfilAction();
			gotoPage(menuDesplegable, "/login");
		});
	}

	private void crearAnimacionCentro() {
		ScaleTransition anim = new ScaleTransition(Duration.seconds(2), imgLogo);
		anim.setToX(1.2);
		anim.setToY(1.2);
		Interpolator interpolator = new Interpolator() {

			@Override
			protected double curve(double t) {
				return (Math.cos(Math.PI * (t + 1)) + 1) / 2;
			}
		};
		anim.setInterpolator(interpolator);
		anim.setAutoReverse(true);
		anim.setCycleCount(-1);
		Platform.runLater(anim::play);
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
		animarPerfil();
		estaPerfilDesplegado = !estaPerfilDesplegado;
	}

	private void animarPerfil() {
		if (estaPerfilDesplegado) {
			animacionPerfil.jumpTo(Duration.millis(200));
			animacionPerfil.setRate(-1);
			animacionPerfil.play();
		} else {
			animacionPerfil.jumpTo(Duration.ZERO);
			animacionPerfil.setRate(1);
			animacionPerfil.play();
		}
	}

	private void desplegarMenuAction() {
		estaMenuDesplegado = !estaMenuDesplegado;
		ejecutarAnimacionMenu();
	}

	public static Menucontroller getInstance() {
		return instance;
	}

	public void setCenter(Node node) {
		centerPane.setCenter(node);
	}

	public void goToInicio() {
		centerPane.setCenter(imgLogo);
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

	public void crearAlerta(String msg) {
		overlay.setDisable(false);
		overlay.setOpacity(1);
		lblAlerta.setText(msg);
	}

	private void cerrarOverlayAction() {
		overlay.setDisable(true);
		overlay.setOpacity(0);
	}
}
