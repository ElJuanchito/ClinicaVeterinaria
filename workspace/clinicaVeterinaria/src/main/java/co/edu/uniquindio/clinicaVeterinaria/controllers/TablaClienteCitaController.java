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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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

	private ObservableList<Cliente> listaObservable;
	
	private Cliente cliente;

    @FXML
    void seleccionarEvent(MouseEvent event) {
    	seleccionarAction();
    }

	private void seleccionarAction() {
		cliente = tblCliente.getSelectionModel().getSelectedItem();
    	
    	if (cliente == null) return;
    	
    	lblDueno.setText(cliente.getNombre());
	}

    @FXML
    void siguienteEvent(ActionEvent event) {
    	try {
			ModelFactoryController.getInstance().setCliente(cliente.getCedula());
		} catch (ClienteNoExistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
    }

}

