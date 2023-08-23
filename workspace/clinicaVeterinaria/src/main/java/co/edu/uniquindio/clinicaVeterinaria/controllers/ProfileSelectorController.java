package co.edu.uniquindio.clinicaVeterinaria.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.clinicaVeterinaria.application.App;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.EscenaNotFoundException;
import co.edu.uniquindio.clinicaVeterinaria.services.Pestanas;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ProfileSelectorController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView imgUser1;

	@FXML
	private ImageView imgUser2;

	@FXML
	private ImageView imgUser3;

	@FXML
	private ImageView imgUser4;

	@FXML
	private Label lblUser1;

	@FXML
	private Label lblUser2;

	@FXML
	private Label lblUser3;

	@FXML
	private Label lblUser4;

	@FXML
	void selectUser1Event(MouseEvent event) {
		ModelFactoryController.getInstance().setVeterinario(0);
		try {
			App.cambiarEscenaEx(Pestanas.INICIO);
		} catch (EscenaNotFoundException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void selectUser2Event(MouseEvent event) {
		ModelFactoryController.getInstance().setVeterinario(1);
		try {
			App.cambiarEscenaEx(Pestanas.INICIO);
		} catch (EscenaNotFoundException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void selectUser3Event(MouseEvent event) {
		ModelFactoryController.getInstance().setVeterinario(2);
		try {
			App.cambiarEscenaEx(Pestanas.INICIO);
		} catch (EscenaNotFoundException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void selectUser4Event(MouseEvent event) {
		ModelFactoryController.getInstance().setVeterinario(3);
		try {
			App.cambiarEscenaEx(Pestanas.INICIO);
		} catch (EscenaNotFoundException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void initialize() {
	}

}