package co.edu.uniquindio.clinicaVeterinaria.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;

public class CreacionAtencionController {

    @FXML
    private TableColumn<?, ?> columnaEdadMascota;

    @FXML
    private Label lblFechaAtencion;

    @FXML
    private Label lblVeterinario;

    @FXML
    private TableColumn<?, ?> columnaSexoMascota;

    @FXML
    private TableColumn<?, ?> columnaTipoMascota;

    @FXML
    private TableColumn<?, ?> columaRazaMascota;

    @FXML
    private TableColumn<?, ?> columnaNombreMascota;

    @FXML
    private Button btnCrearAtencion;

    @FXML
    void crearAtencion(MouseEvent event) {

    }

}
