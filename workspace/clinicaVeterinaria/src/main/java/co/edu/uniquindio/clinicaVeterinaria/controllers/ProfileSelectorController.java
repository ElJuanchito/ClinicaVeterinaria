package co.edu.uniquindio.clinicaVeterinaria.controllers;

import java.net.URL;
import java.util.ResourceBundle;

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
		lblUser1.setText("Arcangel la maravilla");
	}

	@FXML
	void selectUser2Event(MouseEvent event) {
		lblUser2.setText("El negrito ojos claros");
	}

	@FXML
	void selectUser3Event(MouseEvent event) {
		lblUser3.setText("Msmsmsm wou weeeer");
	}

	@FXML
	void selectUser4Event(MouseEvent event) {
		lblUser4.setText("Sooo Nasty");
	}

	@FXML
	void initialize() {
	}

}