package co.edu.uniquindio.clinicaVeterinaria.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class FinalizarAtencionController {

    @FXML
    private TableColumn<?, ?> columnaCodigoAtencion;

    @FXML
    private TextArea txtTratamientoAtencion;

    @FXML
    private TextField txtVeterinarioAtencion;

    @FXML
    private TableColumn<?, ?> columnaHoraAtencion;

    @FXML
    private TableColumn<?, ?> columnaSexoAtencion;

    @FXML
    private Button btnFinalizarAtencion;

    @FXML
    private TextArea txtDiagnosticoAtencion;

    @FXML
    private DatePicker dateFechaAtencion;

    @FXML
    private TableColumn<?, ?> columnaTipoAtencion;

    @FXML
    private TableColumn<?, ?> columnaFechaAtencion;

    @FXML
    private TextField txtMascotaAtencion;

    @FXML
    private TableColumn<?, ?> columnaMascotaAtencion;

    @FXML
    private TableColumn<?, ?> columnaVeterinarioAtencion;

    @FXML
    private ComboBox<?> comboEstadoAtencion;

    @FXML
    private TextField txtHoraAtencion;

    @FXML
    void finalizarAtencion(MouseEvent event) {

    }

}