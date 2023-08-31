package co.edu.uniquindio.clinicaVeterinaria.controllers;

import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import co.edu.uniquindio.clinicaVeterinaria.exceptions.AtencionNoExistenteException;
import co.edu.uniquindio.clinicaVeterinaria.model.AtencionVeterinaria;
import co.edu.uniquindio.clinicaVeterinaria.model.Estado;
import co.edu.uniquindio.clinicaVeterinaria.model.Factura;
import co.edu.uniquindio.clinicaVeterinaria.services.FxUtility;
import co.edu.uniquindio.clinicaVeterinaria.services.GeneracionPdf;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import one.jpro.routing.LinkUtil;

public class FinalizarAtencionController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private TableColumn<AtencionVeterinaria, String> colMascota;

	@FXML
	private TableColumn<AtencionVeterinaria, String> colCodigo;

	@FXML
	private TableColumn<AtencionVeterinaria, String> colCedula;

	@FXML
	private ComboBox<Estado> cbEstado;

	@FXML
	private Button btnFinalizar;

	@FXML
	private TextField txtDiagnostico;

	@FXML
	private TableColumn<AtencionVeterinaria, String> colNombre;

	@FXML
	private TextField txtCedula;

	@FXML
	private TextField txtTratamiento;

	@FXML
	private Label lblMascota;

	@FXML
	private Label lblFecha;

	@FXML
	private TableColumn<AtencionVeterinaria, String> colTipo;

	@FXML
	private TextField txtCosto;

	@FXML
	private Label lblVeterinario;

	@FXML
	private TableView<AtencionVeterinaria> tblCitas;

	@FXML
	private Label lblHora;

	@FXML
	void initialize() {
		ModelFactoryController.getInstance().loadData();

		actualizarTabla();

		cbEstado.getItems().add(Estado.ATENDIDA);
		cbEstado.getItems().add(Estado.CANCELADA);

		colCodigo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCodigo().toString()));
		colCedula.setCellValueFactory(
				e -> new ReadOnlyStringWrapper(e.getValue().getMascota().getDueno().getCedula().toString()));
		colNombre.setCellValueFactory(
				e -> new ReadOnlyStringWrapper(e.getValue().getMascota().getDueno().getNombre().toString()));
		colMascota.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getMascota().getNombre()));
		colTipo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getMascota().getTipo().toString()));

		FxUtility.setAsNumberTextfield(txtCosto);

		txtCedula.textProperty().addListener((observable, oldValue, newValue) -> {
			actualizarTabla();
		});
		tblCitas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

			if (newValue == null) {
				vaciarCampos();
				return;
			}
			cbEstado.setValue(newValue.getEstado());
			lblHora.setText(newValue.getFecha().toLocalTime().toString());
			lblMascota.setText(newValue.getMascota().getNombre());
			lblVeterinario.setText(newValue.getVeterinario().getNombre());
			lblFecha.setText(newValue.getFecha().format(DateTimeFormatter.ISO_LOCAL_DATE));
		});
	}

	@FXML
	void finalizarEvent(ActionEvent event) {
		finalizarAction();
	}

	private void finalizarAction() {
		if (!verificarCampos()) {
			Menucontroller.getInstance().crearAlerta("Llene todos los campos");
			return;
		}
		if (cbEstado.getValue() == Estado.CREADA) {
			Menucontroller.getInstance().crearAlerta("Debe cambiar el estado de la cita");
			return;
		}
		AtencionVeterinaria cita = tblCitas.getSelectionModel().getSelectedItem();
		try {
			cita.setEstado(cbEstado.getValue());
			ModelFactoryController.getInstance().getClinica().actualizarCita(cita);
		} catch (AtencionNoExistenteException e) {
			Menucontroller.getInstance().crearAlerta(e.getMessage());
			return;
		}
		Factura factura = new Factura(Double.valueOf(txtCosto.getText().trim()), txtDiagnostico.getText().trim(),
				txtTratamiento.getText().trim(), cita);
		try {
			ModelFactoryController.getInstance().getClinica().actualizarCita(cita);
			ModelFactoryController.getInstance().saveData();
		} catch (AtencionNoExistenteException e) {
			Menucontroller.getInstance().crearAlerta(e.getMessage());
			return;
		}
		ModelFactoryController.getInstance().getClinica().agregarFactura(factura);
		ModelFactoryController.getInstance().saveData();
		Platform.runLater(() -> new GeneracionPdf(factura).ejecutarImpresion());
		Menucontroller.getInstance().crearAlerta("Factura creada con exito");
		vaciarCampos();
		actualizarTabla();
		LinkUtil.gotoPage(btnFinalizar, "/inicio");
	}

	private void actualizarTabla() {
		tblCitas.setItems(FXCollections.observableList(
				ModelFactoryController.getInstance().getClinica().filtrarCitasCreadasPorCedula(txtCedula.getText())));
		tblCitas.refresh();
	}

	private boolean verificarCampos() {
		if (txtCosto.getText().trim().isEmpty() || txtDiagnostico.getText().trim().isEmpty()
				|| txtTratamiento.getText().trim().isEmpty() || cbEstado.getValue() == null) {
			return false;
		}
		return true;
	}

	private void vaciarCampos() {
		lblFecha.setText("");
		lblHora.setText("");
		lblMascota.setText("");
		lblVeterinario.setText("");
		txtDiagnostico.setText("");
		txtTratamiento.setText("");
		txtCosto.setText("70000");
	}
}