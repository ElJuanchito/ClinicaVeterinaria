package co.edu.uniquindio.clinicaVeterinaria.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.clinicaVeterinaria.exceptions.ClienteNoExistenteException;
import co.edu.uniquindio.clinicaVeterinaria.model.Cliente;
import co.edu.uniquindio.clinicaVeterinaria.model.Mascota;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CreacionAtencionController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TableView<Mascota> tblMascotas;

	@FXML
	private DatePicker txtDate;

	@FXML
	private TableColumn<Mascota, String> colSexo;

	@FXML
	private TableColumn<Mascota, String> colEdad;

	@FXML
	private TableColumn<Mascota, String> colTipo;

	@FXML
	private Label lblVeterinario;

	@FXML
	private TableColumn<Mascota, String> colNombre;

	@FXML
	private Button btnCrear;

	@FXML
	private TextField txtCliente;

	@FXML
	private TableColumn<Mascota, String> colRaza;

	@FXML
	private Button btnBuscar;

	@FXML
	private TextField txtTime;

	private Cliente cliente;

	private ObservableList<Mascota> listaObservable;

	@FXML
	void buscarEvent(ActionEvent event) {
		buscarAction();
	}

	@FXML
	void crearEvent(ActionEvent event) {
		//TODO
	}

	@FXML
	void initialize() {
		ModelFactoryController.getInstance().loadData();
	}

	private void buscarAction() {
		try {
			cliente = ModelFactoryController.getInstance().getClinica().buscarCliente(txtCliente.getText());
			actualizarTabla();
		} catch (ClienteNoExistenteException e) {
			new Alert(AlertType.ERROR, "El cliente con " + txtCliente.getText() + " no existe.").show();
		}

	}
	
	private void actualizarTabla() {
		ModelFactoryController.getInstance().loadData();
		listaObservable = FXCollections.observableList(cliente.getListaMascotas());
		tblMascotas.setItems(listaObservable);
		colNombre.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getNombre()));
		colEdad.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getEdad().toString()));
		colSexo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getSexo().toString()));
		colTipo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getTipo().toString()));
		colRaza.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getRaza()));
		tblMascotas.refresh();
	}
}
