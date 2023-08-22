package co.edu.uniquindio.clinicaVeterinaria.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.clinicaVeterinaria.exceptions.FacturaYaExistenteException;
import co.edu.uniquindio.clinicaVeterinaria.model.AtencionVeterinaria;
import co.edu.uniquindio.clinicaVeterinaria.model.Estado;
import co.edu.uniquindio.clinicaVeterinaria.model.Factura;
import co.edu.uniquindio.clinicaVeterinaria.utils.FxUtility;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class FinalizarAtencionController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TableColumn<AtencionVeterinaria, String> colFecha;

	@FXML
	private TableColumn<AtencionVeterinaria, String> colMascota;

	@FXML
	private TableColumn<AtencionVeterinaria, String> colCodigo;

	@FXML
	private ComboBox<Estado> cbEstado;

	@FXML
	private TableColumn<AtencionVeterinaria, String> colSexo;

	@FXML
	private Button btnFinalizar;

	@FXML
	private TextField txtDiagnostico;

	@FXML
	private TableColumn<AtencionVeterinaria, String> colHora;

	@FXML
	private TextField txtTratamiento;

	@FXML
	private TableColumn<AtencionVeterinaria, String> colVeterinario;

	@FXML
	private Label lblMascota;

	@FXML
	private TableColumn<AtencionVeterinaria, String> colTipo;

	@FXML
	private Label lblVeterinario;

	@FXML
	private TableView<AtencionVeterinaria> tblCitas;

	@FXML
	private Label lblHora;

	@FXML
	private DatePicker txtFecha;

	@FXML
	private TextField txtCosto;

	private ObservableList<AtencionVeterinaria> listaObservable;

	private AtencionVeterinaria citaSelecciona;

	@FXML
	void initialize() {
		ModelFactoryController.getInstance().loadData();
		
		actualizarTabla();
		
		cbEstado.getItems().add(Estado.ATENDIDA);
		cbEstado.getItems().add(Estado.CANCELADA);
		
		FxUtility.setAsNumberTextfield(txtCosto);
	}

	@FXML
	void finalizarEvent(ActionEvent event) {
		finalizarAction();
	}

	@FXML
	void seleccionarEvent(MouseEvent event) {
		seleccionarAction();
	}

	private void finalizarAction() {
		if(!verificarCampos()) {
			new Alert(AlertType.ERROR, "Llene todos los campos").show();
			return;
		}
		Factura factura = new Factura(Double.valueOf(txtCosto.getText().trim()), citaSelecciona.getFecha(),
				txtDiagnostico.getText().trim(), txtTratamiento.getText().trim(),
				citaSelecciona.getMascota().getDueno(), citaSelecciona);
		try {
			ModelFactoryController.getInstance().getClinica().agregarFactura(factura);
			ModelFactoryController.getInstance().saveData();
		} catch (FacturaYaExistenteException e) {
			new Alert(AlertType.ERROR, e.getMessage()).show();
		}
	}

	private void seleccionarAction() {
		citaSelecciona = tblCitas.getSelectionModel().getSelectedItem();

		cbEstado.setValue(citaSelecciona.getEstado());
		txtFecha.setValue(citaSelecciona.getFecha().toLocalDate());
		lblHora.setText(citaSelecciona.getFecha().toLocalTime().toString());
		lblMascota.setText(citaSelecciona.getMascota().getNombre());
		lblVeterinario.setText(citaSelecciona.getVeterinario().getNombre());
	}

	private void actualizarTabla() {
		ModelFactoryController.getInstance().loadData();
		listaObservable = FXCollections
				.observableList(ModelFactoryController.getInstance().getClinica().getListaCitas());
		tblCitas.setItems(listaObservable);
		colCodigo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCodigo().toString()));
		colFecha.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getFecha().toLocalDate().toString()));
		colHora.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getFecha().toLocalTime().toString()));
		colMascota.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getMascota().getNombre()));
		colTipo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getMascota().getTipo().toString()));
		colSexo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getMascota().getSexo().toString()));
		colVeterinario.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getVeterinario().getNombre()));
		tblCitas.refresh();
	}

	private boolean verificarCampos() {
		if (txtCosto.getText().trim().isEmpty() || txtDiagnostico.getText().trim().isEmpty()
				|| txtTratamiento.getText().trim().isEmpty() || cbEstado.getValue() == null) {
			return false;
		}
		return true;
	}
}