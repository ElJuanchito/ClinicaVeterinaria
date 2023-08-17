package co.edu.uniquindio.clinicaVeterinaria.controllers;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

public class MainController {
	@FXML
	private ProgressBar progressBar;

	@FXML
	private Label lblPorcentaje;

	DoubleProperty doubleProperty = new SimpleDoubleProperty(0.09);

	@FXML
	void initialize() {
		progressBar.progressProperty().bind(doubleProperty);
		lblPorcentaje.textProperty().bind(doubleProperty.multiply(100).asString("%.0f"));
		Interpolator interpolacion = new Interpolator() {

			@Override
			protected double curve(double t) {
				return (Math.cos(Math.PI * (t + 1)) + 1) / 2;
			}
		};
		Timeline transition = new Timeline();
		transition.getKeyFrames()
				.add(new KeyFrame(Duration.seconds(3), new KeyValue(doubleProperty, 1, interpolacion)));
		Platform.runLater(() -> transition.play());

	}
}
