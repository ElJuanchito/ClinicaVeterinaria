package co.edu.uniquindio.clinicaVeterinaria.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.clinicaVeterinaria.exceptions.ClienteExistenteException;
import co.edu.uniquindio.clinicaVeterinaria.model.Cliente;
import co.edu.uniquindio.clinicaVeterinaria.utils.FxUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegistroClienteController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtDireccion;

	@FXML
	private TextField txtTelefono;

	@FXML
	private TextField txtCedula;

	@FXML
	private Button btnRegistrar;

	@FXML
	private TextField txtCorreo;

	@FXML
	void initialize() {
		ModelFactoryController.getInstance().loadData();
		FxUtility.setAsNumberTextfield(txtTelefono);
		FxUtility.setAsNumberTextfield(txtCedula);
		FxUtility.setMaximumTextSize(txtTelefono, 10, "El maximo de caracteres debe de ser 10");
		FxUtility.setMaximumTextSize(txtCedula, 10, "El maximo de caracteres debe de ser 10");
	}

	@FXML
	void registrarEvent(ActionEvent event) {
		registrarAction();
	}

	private void registrarAction() {
		if (!verificarCampos()) {
			Menucontroller.getInstance().crearAlerta("Llene todos los campos antes de enviar");
			return;
		}
		try {
			ModelFactoryController.getInstance().getClinica()
					.agregarCliente(new Cliente(txtNombre.getText().trim(), txtCorreo.getText().trim(),
							txtTelefono.getText().trim(), txtCedula.getText().trim(), txtDireccion.getText().trim()));
			ModelFactoryController.getInstance().saveData();
			Menucontroller.getInstance().crearAlerta("Cliente agregado con exito");
			vaciarCampos();
		} catch (ClienteExistenteException e) {
			Menucontroller.getInstance().crearAlerta("El cliente ya existe en el sistema.");
		}

	}

	private boolean verificarCampos() {
		if (txtNombre.getText().trim().isEmpty() || txtCorreo.getText().trim().isEmpty()
				|| txtTelefono.getText().trim().isEmpty() || txtCedula.getText().trim().isEmpty()
				|| txtDireccion.getText().trim().isEmpty()) {
			return false;
		}
		return true;
	}

	private void vaciarCampos() {
		txtNombre.clear();
		txtDireccion.clear();
		txtTelefono.clear();
		txtCedula.clear();
		txtCorreo.clear();
	}
}
