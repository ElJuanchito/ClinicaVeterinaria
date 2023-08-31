package co.edu.uniquindio.clinicaVeterinaria.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.clinicaVeterinaria.model.AtencionVeterinaria;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import one.jpro.routing.LinkUtil;

/**
 * 
 * @Author ElJuancho
 */
public class HistorialCitasController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnVolver;

	@FXML
	private Button btnBuscar;

	@FXML
	private TableColumn<AtencionVeterinaria, String> colCodigo;

	@FXML
	private TableColumn<AtencionVeterinaria, String> colEstado;

	@FXML
	private TableColumn<AtencionVeterinaria, String> colFecha;

	@FXML
	private TableColumn<AtencionVeterinaria, String> colMascota;

	@FXML
	private TableColumn<AtencionVeterinaria, String> colVeterinario;

	@FXML
	private TableView<AtencionVeterinaria> tblCitas;

	@FXML
	private DatePicker txtFin;

	@FXML
	private DatePicker txtInicio;

	private ObservableList<AtencionVeterinaria> listaObservable;

	@FXML
	void initialize() {
		ModelFactoryController.getInstance().loadData();
	}

	@FXML
	void buscarEvent(ActionEvent event) {
		buscarAction();
	}

	@FXML
	void volverEvent(ActionEvent event) {
		volverAction();
	}

	private void volverAction() {
		LinkUtil.gotoPage(btnVolver, "/mas");
	}

	private void buscarAction() {
		if (!verificarCampos()) {
			Menucontroller.getInstance().crearAlerta("Llene todos los campos");
			return;
		}
		actualizarTabla();
	}

	private boolean verificarCampos() {
		if (txtInicio.getValue() == null || txtFin.getValue() == null)
			return false;
		return true;
	}

	private void actualizarTabla() {
		ModelFactoryController.getInstance().loadData();
		listaObservable = FXCollections.observableList(ModelFactoryController.getInstance().getClinica()
				.citasEnRangoDeDias(txtInicio.getValue(), txtFin.getValue()));
		tblCitas.setItems(listaObservable);
		colCodigo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCodigo().toString()));
		colFecha.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getFecha().toLocalDate().toString()));
		colEstado.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getEstado().toString()));
		colMascota.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getMascota().getNombre()));
		colVeterinario.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getVeterinario().getNombre()));
		tblCitas.refresh();
	}

}
