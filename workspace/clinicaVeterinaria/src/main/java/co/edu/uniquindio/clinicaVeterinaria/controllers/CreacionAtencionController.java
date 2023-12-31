package co.edu.uniquindio.clinicaVeterinaria.controllers;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import co.edu.uniquindio.clinicaVeterinaria.exceptions.ClienteNoExistenteException;
import co.edu.uniquindio.clinicaVeterinaria.model.AtencionVeterinaria;
import co.edu.uniquindio.clinicaVeterinaria.model.Cliente;
import co.edu.uniquindio.clinicaVeterinaria.model.Mascota;
import co.edu.uniquindio.clinicaVeterinaria.model.Veterinario;
import co.edu.uniquindio.clinicaVeterinaria.services.FxUtility;
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
import javafx.util.StringConverter;

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
	private ComboBox<Veterinario> cbVeterinario;

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

	@FXML
	private Label txtMascota;

	@FXML
	private TableColumn<Mascota, String> colCodigo;

	private Mascota mascota;

	private Cliente cliente;

	private ObservableList<Mascota> listaObservable;

	@FXML
	void initialize() {
		ModelFactoryController.getInstance().loadData();
		cbVeterinario.getItems().addAll(ModelFactoryController.getInstance().getClinica().getVeterinarios());
		establecerNombres();
		FxUtility.setAsIntegerTextfield(txtCliente);
		FxUtility.setAsHourTextField(txtTime);
		FxUtility.setMaxLengthHourSize(txtTime, 5);
	}

	@FXML
	void buscarEvent(ActionEvent event) {
		buscarAction();
	}

	@FXML
	void crearEvent(ActionEvent event) {
		crearAction();
	}

	@FXML
	void seleccionarEvent(MouseEvent event) {
		seleccionarAction();
	}

	private void crearAction() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime tiempo = LocalTime.parse(txtTime.getText().trim(), formatter);

		LocalDateTime fechita = LocalDateTime.of(txtDate.getValue(), tiempo);

		AtencionVeterinaria cita = new AtencionVeterinaria(fechita, mascota, cbVeterinario.getValue());

		if (!verificarCampos()) {
			Menucontroller.getInstance().crearAlerta("Llene todos los campos");
			return;
		}
		ModelFactoryController.getInstance().getClinica().agregarCita(cita);
		ModelFactoryController.getInstance().saveData();
		Menucontroller.getInstance().crearAlerta("Cita creada con exito");
		vaciarCampos();
	}

	private void seleccionarAction() {
		mascota = tblMascotas.getSelectionModel().getSelectedItem();

		if(mascota == null) return;
		txtMascota.setText(mascota.getNombre());
	}

	private void buscarAction() {
		try {
			cliente = ModelFactoryController.getInstance().getClinica().buscarCliente(txtCliente.getText());
			actualizarTabla();
		} catch (ClienteNoExistenteException e) {
			Menucontroller.getInstance().crearAlerta("El cliente con " + txtCliente.getText() + " no existe.");
		}
	}

	private void actualizarTabla() {
		ModelFactoryController.getInstance().loadData();
		listaObservable = FXCollections.observableList(cliente.getListaMascotas());
		tblMascotas.setItems(listaObservable);
		colCodigo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCodigo()));
		colNombre.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getNombre()));
		colEdad.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getEdad().toString()));
		colSexo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getSexo().toString()));
		colTipo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getTipo().toString()));
		colRaza.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getRaza()));
		tblMascotas.refresh();
	}

	private void establecerNombres() {
		cbVeterinario.setConverter(new StringConverter<Veterinario>() {
			@Override
			public String toString(Veterinario veterinario) {
				return veterinario != null ? veterinario.getNombre() : "";
			}

			@Override
			public Veterinario fromString(String string) {
				return null;
			}
		});
	}

	private boolean verificarCampos() {
		if (txtCliente.getText().trim().isEmpty() || txtDate == null || txtTime.getText().trim().isEmpty()
				|| cbVeterinario.getValue() == null || txtMascota.getText().isEmpty()) {
			return false;
		}
		return true;
	}
	
	private void vaciarCampos() {
		txtDate.setValue(null);
		cbVeterinario.setValue(null);
		txtCliente.clear();
		txtTime.clear();
		txtMascota.setText("");
	}

}
