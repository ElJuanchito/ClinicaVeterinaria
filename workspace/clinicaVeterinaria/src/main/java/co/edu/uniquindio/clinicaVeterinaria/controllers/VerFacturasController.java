package co.edu.uniquindio.clinicaVeterinaria.controllers;

import co.edu.uniquindio.clinicaVeterinaria.model.Factura;
import co.edu.uniquindio.clinicaVeterinaria.services.FxUtility;
import co.edu.uniquindio.clinicaVeterinaria.services.GeneracionPdf;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import one.jpro.routing.LinkUtil;

public class VerFacturasController {
	@FXML
	private TableColumn<Factura, String> colMascota;

	@FXML
	private TableColumn<Factura, String> colVeterinario;

	@FXML
	private TableColumn<Factura, String> colCodigo;

	@FXML
	private Button btnVolver;

	@FXML
	private TableColumn<Factura, String> colCedula;

	@FXML
	private Button btnGenerar;

	@FXML
	private TableColumn<Factura, String> colValor;

	@FXML
	private TextField txtCodigo;

	@FXML
	private TableView<Factura> tblFacturas;

	@FXML
	void volverEvent(ActionEvent event) {
		volverAction();
	}

	@FXML
	void initialize() {
		actualizarTabla();
		txtCodigo.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.isEmpty()) {
				actualizarTabla();
				return;
			}
			actualizarTabla(newValue);
		});
		colMascota.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getNombreMascota()));
		colCedula.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCliente().getCedula()));
		colCodigo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getId().toString()));
		colVeterinario.setCellValueFactory(
				e -> new ReadOnlyStringWrapper(e.getValue().getAtencionVeterinaria().getVeterinario().getNombre()));
		colValor.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCosto().toString()));
		FxUtility.setAsNumberTextfield(txtCodigo);
	}

	public void actualizarTabla(String codigo) {
		Long code;
		try {
			code = Long.parseLong(codigo);
		} catch (Exception e) {
			return;
		}
		tblFacturas.setItems(
				FXCollections.observableArrayList(ModelFactoryController.getInstance().filtrarFacturasCodigo(code)));
		tblFacturas.refresh();
	}

	public void actualizarTabla() {
		tblFacturas.setItems(FXCollections
				.observableArrayList(ModelFactoryController.getInstance().getClinica().getListaFacturas()));
	}

	private void volverAction() {
		LinkUtil.gotoPage(btnVolver, "/mas");
	}

	@FXML
	void generarEvent(ActionEvent event) {
		generarAction();
	}

	private static VerFacturasController instance;

	public static VerFacturasController getInstance() {
		return instance;
	}

	public VerFacturasController() {
		instance = this;
	}

	private void generarAction() {
		Factura factura = tblFacturas.getSelectionModel().getSelectedItem();
		if (factura == null) {
			Menucontroller.getInstance().crearAlerta("Recuerda Seleccionar una factura");
			return;
		}
		Platform.runLater(() -> new GeneracionPdf(factura).ejecutarImpresion());
	}
}
