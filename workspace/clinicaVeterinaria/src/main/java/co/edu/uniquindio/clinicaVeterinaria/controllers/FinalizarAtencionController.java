package co.edu.uniquindio.clinicaVeterinaria.controllers;

import java.util.ResourceBundle;

import co.edu.uniquindio.clinicaVeterinaria.model.AtencionVeterinaria;
import co.edu.uniquindio.clinicaVeterinaria.model.Estado;
import co.edu.uniquindio.clinicaVeterinaria.model.Factura;
import co.edu.uniquindio.clinicaVeterinaria.services.GeneracionPdf;
import co.edu.uniquindio.clinicaVeterinaria.utils.FxUtility;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
	    private DatePicker txtFecha;

	private ObservableList<AtencionVeterinaria> listaObservable;

	private AtencionVeterinaria citaSelecciona;

	@FXML
	void initialize() {
		ModelFactoryController.getInstance().loadData();
		

		actualizarTabla();

		cbEstado.getItems().add(Estado.ATENDIDA);
		cbEstado.getItems().add(Estado.CANCELADA);

		FxUtility.setAsNumberTextfield(txtCosto);
		
		
		txtCedula.textProperty().addListener((observable, oldValue, newValue) -> {
			actualizarTabla();
			tblCitas.refresh();
		});
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
		if (!verificarCampos()) {
			Menucontroller.getInstance().crearAlerta("Llene todos los campos");
			return;
		}
		if (cbEstado.getValue() != Estado.CREADA) {
			Menucontroller.getInstance().crearAlerta("Debe cambiar el estado de la cita");
			return;
		}
		Factura factura = new Factura(Double.valueOf(txtCosto.getText().trim()), citaSelecciona.getFecha(),
				txtDiagnostico.getText().trim(), txtTratamiento.getText().trim(),
				citaSelecciona.getMascota().getDueno(), citaSelecciona);
		Platform.runLater(() -> new GeneracionPdf(factura).ejecutarImpresion());
		ModelFactoryController.getInstance().getClinica().agregarFactura(factura);
		ModelFactoryController.getInstance().saveData();
		Menucontroller.getInstance().crearAlerta("Factura creada con exito");
		vaciarCampos();
	}

	private void seleccionarAction() {
		citaSelecciona = tblCitas.getSelectionModel().getSelectedItem();

		if (citaSelecciona == null) return;

		cbEstado.setValue(citaSelecciona.getEstado());
		txtFecha.setValue(citaSelecciona.getFecha().toLocalDate());
		lblHora.setText(citaSelecciona.getFecha().toLocalTime().toString());
		lblMascota.setText(citaSelecciona.getMascota().getNombre());
		lblVeterinario.setText(citaSelecciona.getVeterinario().getNombre());
	}

	private void actualizarTabla() {
		
		listaObservable = FXCollections
				.observableList(ModelFactoryController.getInstance().getClinica().filtrarCitaPorCedula(txtCedula.getText()));
		tblCitas.setItems(listaObservable);
		colCodigo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCodigo().toString()));
		colCedula.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getMascota().getDueno().getCedula().toString()));
		colNombre.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getMascota().getDueno().getNombre().toString()));
		//colFecha.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getFecha().toLocalDate().toString()));
		//colHora.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getFecha().toLocalTime().toString()));
		colMascota.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getMascota().getNombre()));
		colTipo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getMascota().getTipo().toString()));
		//colSexo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getMascota().getSexo().toString()));
		//colVeterinario.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getVeterinario().getNombre()));
		tblCitas.refresh();
	}

	private boolean verificarCampos() {
		if (txtCosto.getText().trim().isEmpty() || txtDiagnostico.getText().trim().isEmpty()
				|| txtTratamiento.getText().trim().isEmpty() || cbEstado.getValue() == null) {
			return false;
		}
		return true;
	}

	private void vaciarCampos(){
		txtFecha.setValue(null);
		lblHora.setText("");
		lblVeterinario.setText("");
		txtDiagnostico.setText("");
		txtTratamiento.setText("");
		txtCosto.setText("70000");
	}
}