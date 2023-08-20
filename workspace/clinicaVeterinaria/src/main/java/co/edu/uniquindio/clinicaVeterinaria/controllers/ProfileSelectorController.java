package co.edu.uniquindio.clinicaVeterinaria.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ProfileSelectorController {

    @FXML
    private Button btnVet4;

    @FXML
    private ImageView imgVet2;

    @FXML
    private ImageView Imag;

    @FXML
    private Label lblTitle;

    @FXML
    private ImageView imgVet1;
    
    
    @FXML
    public void initialize (){
        ImageView imageView = new ImageView(getClass().getResource("/co/edu/uniquindio/clinicaVeterinaria/sources/imgTry.jpeg").toExternalForm());
        btnVet4.setGraphic(imageView);
        btnVet4.setContentDisplay(ContentDisplay.CENTER);
    }
    

}