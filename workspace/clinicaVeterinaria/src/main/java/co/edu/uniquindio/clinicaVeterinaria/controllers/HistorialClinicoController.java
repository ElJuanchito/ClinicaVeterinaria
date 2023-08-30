/**
 * 
 */
package co.edu.uniquindio.clinicaVeterinaria.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.clinicaVeterinaria.model.AtencionVeterinaria;
import co.edu.uniquindio.clinicaVeterinaria.utils.FxUtility;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * 
 * @Author ElJuancho
 */
public class HistorialClinicoController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnBuscar;

	@FXML
	private TableColumn<AtencionVeterinaria, String> colCodigo;

	@FXML
	private TableColumn<AtencionVeterinaria, String> colEstado;

	@FXML
	private TableColumn<AtencionVeterinaria, String> colFecha;

	@FXML
	private TableColumn<AtencionVeterinaria, String> colVeterinario;

	@FXML
	private TableView<AtencionVeterinaria> tblCitas;

	@FXML
	private TextField txtCedula;

	@FXML
	private TextField txtMascota;

	private ObservableList<AtencionVeterinaria> listaObservable;

	@FXML
	void initialize() {
		ModelFactoryController.getInstance().loadData();
		FxUtility.setAsNumberTextfield(txtCedula);
		FxUtility.setMaximumTextSize(txtCedula, 10, "Este campo puede tener como maximo 10 digitos");
	}

	@FXML
	void buscarEvent(ActionEvent event) {
		buscarAction();
	}

	private void buscarAction() {
		if (!verificarCampos()) {
			Menucontroller.getInstance().crearAlerta("Llene todos los campos");
			return;
		}
		actualizarTabla();
	}

	private void actualizarTabla() {
		ModelFactoryController.getInstance().loadData();
		listaObservable = FXCollections.observableList(ModelFactoryController.getInstance().getClinica()
				.obtenerHistorialClinico(txtCedula.getText().trim(), txtMascota.getText().trim()));
		tblCitas.setItems(listaObservable);
		colCodigo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCodigo().toString()));
		colFecha.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getFecha().toLocalDate().toString()));
		colEstado.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getEstado().toString()));
		colVeterinario.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getVeterinario().getNombre()));
		tblCitas.refresh();
	}

	private boolean verificarCampos() {
		if (txtCedula.getText().trim().isEmpty() || txtMascota.getText().trim().isEmpty())
			return false;
		return true;
	}

}
