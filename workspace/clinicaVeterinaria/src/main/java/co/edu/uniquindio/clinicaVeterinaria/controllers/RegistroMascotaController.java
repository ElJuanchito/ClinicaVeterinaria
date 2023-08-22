package co.edu.uniquindio.clinicaVeterinaria.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.clinicaVeterinaria.exceptions.ClienteNoExistenteException;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.MascotaYaExistenteException;
import co.edu.uniquindio.clinicaVeterinaria.model.Cliente;
import co.edu.uniquindio.clinicaVeterinaria.model.Mascota;
import co.edu.uniquindio.clinicaVeterinaria.model.Sexo;
import co.edu.uniquindio.clinicaVeterinaria.model.Tipo;
import co.edu.uniquindio.clinicaVeterinaria.utils.FxUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class RegistroMascotaController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField txtNombre;

	@FXML
	private ComboBox<Sexo> cbSexo;

	@FXML
	private TextField txtRaza;

	@FXML
	private TextField txtEdad;

	@FXML
	private TextField txtCedula;

	@FXML
	private TextField txtCodigo;

	@FXML
	private Button btnRegistrar;

	@FXML
	private ComboBox<Tipo> cbTipo;

	@FXML
	void initialize() {
		ModelFactoryController.getInstance().loadData();
		cbSexo.getItems().addAll(Sexo.values());
		cbTipo.getItems().addAll(Tipo.values());
		FxUtility.setAsIntegerTextfield(txtEdad);
		FxUtility.setAsIntegerTextfield(txtCedula);
		FxUtility.setAsIntegerTextfield(txtCodigo);
	}

	@FXML
	void registrarEvent(ActionEvent event) {
		registrarAction();
	}

	private void registrarAction() {
		if (!verificarCampos()) {
			new Alert(AlertType.WARNING, "Llene todos los campos").show();
			return;
		}
		try {
			Cliente cliente = ModelFactoryController.getInstance().getClinica()
					.buscarCliente(txtCedula.getText().trim());
			ModelFactoryController.getInstance().getClinica().agregarMascota(cliente,
					new Mascota(txtCodigo.getText().trim(), cliente, txtNombre.getText().trim(),
							Integer.valueOf(txtEdad.getText()), txtRaza.getText().trim(), cbTipo.getValue(),
							cbSexo.getValue()));
			ModelFactoryController.getInstance().saveData();
		} catch (ClienteNoExistenteException e) {
			new Alert(AlertType.WARNING, "No existe ningun cliente con esta cedula").show();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (MascotaYaExistenteException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}

	}
	
	private boolean verificarCampos() {
		if (txtNombre.getText().trim().isEmpty() || txtRaza.getText().trim().isEmpty()
				|| txtEdad.getText().trim().isEmpty() || txtCedula.getText().trim().isEmpty()
				|| cbSexo.getValue() == null || cbTipo.getValue() == null || txtCodigo.getText().trim().isEmpty()) {
			return false;
		}
		return true;
	}
}
