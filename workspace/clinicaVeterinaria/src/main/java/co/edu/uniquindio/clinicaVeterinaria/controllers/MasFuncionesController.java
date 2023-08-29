package co.edu.uniquindio.clinicaVeterinaria.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import one.jpro.routing.LinkUtil;

public class MasFuncionesController {

	@FXML
	private HBox btnHistorialClinico;

	@FXML
	void verHistorialClinicoEvent(MouseEvent event) {
		verHistorialClinicoAction();
	}

	private void verHistorialClinicoAction() {
		LinkUtil.gotoPage(btnHistorialClinico, "/historialClinico");
	}
}
