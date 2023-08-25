package co.edu.uniquindio.clinicaVeterinaria.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.clinicaVeterinaria.application.App;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.EscenaNotFoundException;
import co.edu.uniquindio.clinicaVeterinaria.model.Veterinario;
import co.edu.uniquindio.clinicaVeterinaria.services.Pestanas;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import one.jpro.routing.LinkUtil;

public class ProfileSelectorController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView imgVet_1;

	@FXML
	private ImageView imgVet_2;

	@FXML
	private ImageView imgVet_3;

	@FXML
	private ImageView imgVet_4;

	@FXML
	private Label lblVet1;

	@FXML
	private Label lblVet2;

	@FXML
	private Label lblVet3;

	@FXML
	private Label lblVet4;

	@FXML
	private HBox panelPrincipal;

	@FXML
	void selectUser1Event(MouseEvent event) {
		selectUser1Action();
	}

	@FXML
	void selectUser2Event(MouseEvent event) {
		selectUser2Action();
	}

	@FXML
	void selectUser3Event(MouseEvent event) {
		selectUser3Action();
	}

	@FXML
	void selectUser4Event(MouseEvent event) {
		selectUser4Action();
	}

	private void selectUser1Action() {
		seleccionarUsuarioInicio(imgVet_1, "0001");
	}

	private void selectUser2Action() {
		seleccionarUsuarioInicio(imgVet_2, "0002");
	}

	private void selectUser3Action() {
		seleccionarUsuarioInicio(imgVet_3, "0003");
	}

	private void selectUser4Action() {
		seleccionarUsuarioInicio(imgVet_4, "0004");
	}

	private void seleccionarUsuarioInicio(ImageView imagen, String codigoVet) {
		ModelFactoryController.getInstance().setVeterinario(codigoVet);
		LinkUtil.gotoPage(imagen, "/inicio");
	}

	@FXML
	void initialize() {
		Veterinario[] veterinarios = ModelFactoryController.getInstance().getClinica().getVeterinarios();
		imgVet_1.setImage(veterinarios[0].getFoto());
		lblVet1.setText(veterinarios[0].getNombre());

		imgVet_2.setImage(veterinarios[1].getFoto());
		lblVet2.setText(veterinarios[1].getNombre());

		imgVet_3.setImage(veterinarios[2].getFoto());
		lblVet3.setText(veterinarios[2].getNombre());

		imgVet_4.setImage(veterinarios[3].getFoto());
		lblVet4.setText(veterinarios[3].getNombre());

	}

}