package co.edu.uniquindio.clinicaVeterinaria.controllers;

import co.edu.uniquindio.clinicaVeterinaria.application.App;
import co.edu.uniquindio.clinicaVeterinaria.application.App.ESCENA;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.EscenaNotFoundException;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
		scroll.minHeightProperty().bind(doubleProperty.multiply(245));
		lblPorcentaje.textProperty().bind(doubleProperty.multiply(100).asString("%.0f"));
		Interpolator interpolacion = new Interpolator() {

			@Override
			protected double curve(double t) {
				return t * (2 - t);
			}
		};
		Timeline transition = new Timeline();
		transition.getKeyFrames()
				.add(new KeyFrame(Duration.seconds(2), new KeyValue(doubleProperty, 1, interpolacion)));
		transition.getKeyFrames().add(new KeyFrame(Duration.millis(2400), new KeyValue(doubleProperty, 1)));
		transition.setOnFinished(e -> {
			try {
				App.cambiarEscena(ESCENA.INICIO);
			} catch (EscenaNotFoundException e1) {
				e1.printStackTrace();
			}
		});

		Platform.runLater(() -> {
			transition.play();
			App.cargarEscenas();
		});

	}
}
