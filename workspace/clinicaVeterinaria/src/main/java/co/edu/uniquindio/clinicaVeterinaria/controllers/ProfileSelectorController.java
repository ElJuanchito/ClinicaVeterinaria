package co.edu.uniquindio.clinicaVeterinaria.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.clinicaVeterinaria.model.Veterinario;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import one.jpro.routing.LinkUtil;

public class ProfileSelectorController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label lblVet3;

	@FXML
	private BorderPane panelPrincipal;

	@FXML
	private Label lblVet4;

	@FXML
	private Label lblVet1;

	@FXML
	private Label lblVet2;

	@FXML
	private Circle circulito_1;

	@FXML
	private Circle circulito_2;

	@FXML
	private Circle circulito_3;

	@FXML
	private Circle circulito_4;
	
	@FXML
    private Button btnCerrar;

    @FXML
    void cerrarEvent(ActionEvent event) {
    	cerrarAction();
    }

	private void cerrarAction() {
		Platform.exit();
		
	}

	@FXML
	void selectUser1Event(MouseEvent event) {
		selectUser1Action();
	}

	@FXML
	void selectUser2Event(MouseEvent event) {
		selectUser2Action();
	}

	@FXML
	void selectUser3Event(MouseEvent event) {
		selectUser3Action();
	}

	@FXML
	void selectUser4Event(MouseEvent event) {
		selectUser4Action();
	}

	private void selectUser1Action() {
		seleccionarUsuarioInicio(circulito_1, "0001");
	}

	private void selectUser2Action() {
		seleccionarUsuarioInicio(circulito_2, "0002");
	}

	private void selectUser3Action() {
		seleccionarUsuarioInicio(circulito_3, "0003");
	}

	private void selectUser4Action() {
		seleccionarUsuarioInicio(circulito_4, "0004");
	}

	private void seleccionarUsuarioInicio(Circle circulito, String codigoVet) {
		ModelFactoryController.getInstance().setVeterinario(codigoVet);
		LinkUtil.gotoPage(circulito, "/inicio");
	}

	@FXML
	void initialize() {
		panelPrincipal.setBackground(new Background(new BackgroundImage(
				new Image(ProfileSelectorController.class
						.getResourceAsStream("/co/edu/uniquindio/clinicaVeterinaria/sources/background.png")),
				null, null, null, null)));

		Veterinario[] veterinarios = ModelFactoryController.getInstance().getClinica().getVeterinarios();

		circulito_1.setFill(new ImagePattern(veterinarios[0].getFoto()));
		circulito_1.setStroke(Color.web("#14133b"));
		inicializarCirculos(circulito_1);
		lblVet1.setText(veterinarios[0].getNombre());

		circulito_2.setFill(new ImagePattern(veterinarios[1].getFoto()));
		circulito_2.setStroke(Color.web("#14133b"));
		inicializarCirculos(circulito_2);
		lblVet2.setText(veterinarios[1].getNombre());

		circulito_3.setFill(new ImagePattern(veterinarios[2].getFoto()));
		circulito_3.setStroke(Color.web("#14133b"));
		inicializarCirculos(circulito_3);
		lblVet3.setText(veterinarios[2].getNombre());

		circulito_4.setFill(new ImagePattern(veterinarios[3].getFoto()));
		circulito_4.setStroke(Color.web("#14133b"));
		inicializarCirculos(circulito_4);
		lblVet4.setText(veterinarios[3].getNombre());

	}
	
	private void inicializarCirculos(Circle circulo) {
		circulo.setStyle("-fx-effect: innershadow(gaussian, #6fc1c4, 1em, 0.5, 0.0, 0.0);");
	}

}