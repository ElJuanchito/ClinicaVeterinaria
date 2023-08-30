/**
 * 
 */
package co.edu.uniquindio.clinicaVeterinaria.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.clinicaVeterinaria.exceptions.ClienteNoExistenteException;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.MascotaNoEncontradaExpcetion;
import co.edu.uniquindio.clinicaVeterinaria.model.Mascota;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import one.jpro.routing.LinkUtil;

/**
 * 
 * @Author ElJuancho
 */
public class TablaMascotaCitaController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnSiguiente;

	@FXML
	private TableColumn<Mascota, String> colCodigo;

	@FXML
	private TableColumn<Mascota, String> colEdad;

	@FXML
	private TableColumn<Mascota, String> colNombre;

	@FXML
	private TableColumn<Mascota, String> colRaza;

	@FXML
	private TableColumn<Mascota, String> colSexo;

	@FXML
	private TableColumn<Mascota, String> colTipo;

	@FXML
	private TableView<Mascota> tblMascota;

	@FXML
	private TextField txtMascota;
	
	private Mascota mascota;

	@FXML
	void siguienteEvent(ActionEvent event) {
		siguienteAction();
	}


	@FXML
	void seleccionarEvent(MouseEvent event) {
		seleccionarAction();
	}
	
	/**
	 * 
	 * @author ElJuancho
	 */
	private void seleccionarAction() {
		mascota = tblMascota.getSelectionModel().getSelectedItem();

		if (mascota == null)
			return;	
	}


	/**
	 * 
	 * @author ElJuancho
	 */
	private void siguienteAction() {
		if (mascota == null) {
			Menucontroller.getInstance().crearAlerta("Debe seleccionar una mascota");
			return;
		}
		try {
			ModelFactoryController.getInstance().setMascota(mascota.getCodigo());
			LinkUtil.gotoPage(btnSiguiente, "/concretarAtencion");
		} catch (ClienteNoExistenteException e) {
			Menucontroller.getInstance().crearAlerta("El cliente con cedula " + ModelFactoryController.getInstance().getCliente().getCedula() + " no existe");
		} catch (MascotaNoEncontradaExpcetion e) {
			Menucontroller.getInstance().crearAlerta("La mascota con codigo " + mascota.getCodigo() + " no existe");
		}

		
	}
	
	@FXML
	void initialize() {
		ModelFactoryController.getInstance().loadData();
		txtMascota.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				tblMascota.setItems(FXCollections
						.observableArrayList(ModelFactoryController.getInstance().filtrarMascotaPorCliente(newValue)));
			} catch (ClienteNoExistenteException e) {
				e.printStackTrace();
			}
			tblMascota.refresh();
		});
		colNombre.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getNombre()));
		colCodigo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCodigo()));
		colEdad.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getEdad().toString()));
		colRaza.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getRaza()));
		colTipo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getTipo().toString()));
		colSexo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getSexo().toString()));
		tblMascota.refresh();
	}

}
