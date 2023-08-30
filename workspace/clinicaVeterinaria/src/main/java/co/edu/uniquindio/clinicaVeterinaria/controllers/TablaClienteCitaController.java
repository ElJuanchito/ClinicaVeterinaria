package co.edu.uniquindio.clinicaVeterinaria.controllers;

import co.edu.uniquindio.clinicaVeterinaria.exceptions.ClienteNoExistenteException;
import co.edu.uniquindio.clinicaVeterinaria.model.Cliente;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import one.jpro.routing.LinkUtil;

/**
 * 
 * @Author ElJuancho
 */
public class TablaClienteCitaController {

	@FXML
	private Button btnSiguiente;

	@FXML
	private TableColumn<Cliente, String> colCorreo;

	@FXML
	private TableColumn<Cliente, String> colDireccion;

	@FXML
	private TableColumn<Cliente, String> colNombre;

	@FXML
	private TableColumn<Cliente, String> colCedula;

	@FXML
	private TableColumn<Cliente, String> colTelefono;

	@FXML
	private Label lblDueno;

	@FXML
	private TableView<Cliente> tblCliente;

	@FXML
	private TextField txtCedula;

	private Cliente cliente;

	@FXML
	void seleccionarEvent(MouseEvent event) {
		seleccionarAction();
	}

	private void seleccionarAction() {
		cliente = tblCliente.getSelectionModel().getSelectedItem();

		if (cliente == null)
			return;

		lblDueno.setText(cliente.getNombre());
	}

	@FXML
	void siguienteEvent(ActionEvent event) {
		siguienteAction();
	}

	private void siguienteAction() {
		if (cliente == null) {
			Menucontroller.getInstance().crearAlerta("Debe seleccionar un cliente");
			return;
		}
		try {
			ModelFactoryController.getInstance().setCliente(cliente.getCedula());
			LinkUtil.gotoPage(btnSiguiente, "/tablaMascota");
		} catch (ClienteNoExistenteException e) {
			Menucontroller.getInstance().crearAlerta("El cliente con cedula " + cliente.getCedula() + " no existe");
		}
	}

	@FXML
	void initialize() {
		ModelFactoryController.getInstance().loadData();
		txtCedula.textProperty().addListener((observable, oldValue, newValue) -> {
			tblCliente.setItems(FXCollections
					.observableArrayList(ModelFactoryController.getInstance().filtrarClienteCedu(newValue)));
			tblCliente.refresh();
		});
		colNombre.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getNombre()));
		colCorreo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCorreo()));
		colTelefono.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getTelefono()));
		colCedula.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCedula()));
		colDireccion.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getDireccion()));
		tblCliente.refresh();
	}

}
