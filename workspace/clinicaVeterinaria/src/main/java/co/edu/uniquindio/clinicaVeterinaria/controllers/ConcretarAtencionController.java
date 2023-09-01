package co.edu.uniquindio.clinicaVeterinaria.controllers;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

import co.edu.uniquindio.clinicaVeterinaria.model.AtencionVeterinaria;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import one.jpro.routing.LinkUtil;

/**
 * 
 * @Author ElJuancho
 */

public class ConcretarAtencionController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnSiguiente;

	@FXML
	private Label lblCliente;

	@FXML
	private Label lblMascota;

	@FXML
	private Label lblVeterinario;

	@FXML
	private DatePicker txtFecha;

	@FXML
	private Spinner<Integer> txtHora;

	@FXML
	private Spinner<Integer> txtMin;

	@FXML
	void siguienteEvent(ActionEvent event) {
		siguienteAction();
	}

	/**
	 * 
	 * @author ElJuancho
	 */
	private void siguienteAction() {
		int hora = txtHora.getValue();
		int minuto = txtMin.getValue();

		LocalTime localTime = LocalTime.of(hora, minuto);

		LocalDateTime fechita = LocalDateTime.of(txtFecha.getValue(), localTime);

		AtencionVeterinaria cita = new AtencionVeterinaria(fechita, ModelFactoryController.getInstance().getMascota(),
				ModelFactoryController.getInstance().getVeterinario());

		if (!verificarCampos()) {
			Menucontroller.getInstance().crearAlerta("Llene todos los campos");
			return;
		}
		ModelFactoryController.getInstance().getClinica().agregarCita(cita);
		ModelFactoryController.getInstance().saveData();
		Menucontroller.getInstance().crearAlerta("Cita creada con exito");
		LinkUtil.gotoPage(btnSiguiente, "/inicio");

	}

	@FXML
	void initialize() {
		ModelFactoryController.getInstance().loadData();

		ModelFactoryController.getInstance().getPropClienteCita().addListener((observable, oldValue, newValue) -> {
			lblCliente.setText(newValue == null ? "" : newValue.getNombre());
		});
		ModelFactoryController.getInstance().getPropMascotaCita().addListener((observable, oldValue, newValue) -> {
			lblMascota.setText(newValue == null ? "" : newValue.getNombre());
		});
		ModelFactoryController.getInstance().getPropMascotaCita().addListener((observable, oldValue, newValue) -> {
			lblMascota.setText(newValue == null ? "" : newValue.getNombre());
		});
		ModelFactoryController.getInstance().getPropVeterinarioSel().addListener((observable, oldValue, newValue) -> {
			lblVeterinario.setText(newValue == null ? "" : newValue.getNombre());
		});
	}

	private boolean verificarCampos() {
		if (txtFecha.getValue() == null || txtHora.getValue() == null || txtMin.getValue() == null) {
			return false;
		}
		return true;
	}

}
