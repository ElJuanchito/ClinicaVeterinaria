package co.edu.uniquindio.clinicaVeterinaria.controllers;

import co.edu.uniquindio.clinicaVeterinaria.application.App;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.EscenaNotFoundException;
import co.edu.uniquindio.clinicaVeterinaria.services.CustomFxThread;
import co.edu.uniquindio.clinicaVeterinaria.services.Pestanas;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

public class LoadController {

	@FXML
	private SVGPath patita;

	@FXML
	private Label lblPorcentaje;

	@FXML
	private ScrollPane scroll;

	DoubleProperty doubleProperty = new SimpleDoubleProperty(0.09);

	@FXML
	void initialize() {
		establecerPropiedades();
		ejecutarTransicion();
	}

	private void establecerPropiedades() {
		scroll.minHeightProperty().bind(doubleProperty.multiply(245));
		lblPorcentaje.textProperty().bind(doubleProperty.multiply(100).asString("%.0f"));
	}

	private void ejecutarTransicion() {
		Timeline transition = new Timeline();
		agregarKeyFramesTransicion(transition);

		transition.play();
		Platform.runLater(() -> {
			CustomFxThread.crearHilo(() -> cargarEscenas(transition)).iniciarActividad();
		});
	}

	private void cargarEscenas(Timeline transition) {
		App.cargarEscenas(() -> {
			if (transition.getCurrentTime().lessThan(Duration.millis(1500)))
				transition.jumpTo(Duration.millis(1500));
			transition.currentTimeProperty().addListener(new ChangeListener<Duration>() {
				@Override
				public void changed(ObservableValue<? extends Duration> obs, Duration oldVal, Duration newVal) {
					if (newVal.greaterThan(Duration.millis(2300))) {
						transition.currentTimeProperty().removeListener(this);
						irAEscenaPrincipal();
					}
				}
			});
			if (transition.getCurrentTime().greaterThan(Duration.millis(2300))) {
				irAEscenaPrincipal();
			}
		});
	}

	private void irAEscenaPrincipal() {
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(0)),
				new KeyFrame(Duration.millis(100), new KeyValue(doubleProperty, 1)),
				new KeyFrame(Duration.millis(300)));
		timeline.playFromStart();
		timeline.setOnFinished((e) -> {
			try {
				App.cambiarEscenaEx(Pestanas.LOGIN);
			} catch (EscenaNotFoundException e1) {
				e1.printStackTrace();
			}
		});

	}

	private void agregarKeyFramesTransicion(Timeline transition) {
		Interpolator interpolacion = crearInterpolacion();
		transition.getKeyFrames().add(new KeyFrame(Duration.millis(100), new KeyValue(doubleProperty, 0.09)));
		transition.getKeyFrames()
				.add(new KeyFrame(Duration.millis(2400), new KeyValue(doubleProperty, .99, interpolacion)));
	}

	private Interpolator crearInterpolacion() {
		Interpolator interpolacion = new Interpolator() {

			@Override
			protected double curve(double t) {
				return t * (2 - t);
			}
		};
		return interpolacion;
	}
}
