package co.edu.uniquindio.clinicaVeterinaria.controllers;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import co.edu.uniquindio.clinicaVeterinaria.model.Cliente;
import co.edu.uniquindio.clinicaVeterinaria.model.Factura;
import co.edu.uniquindio.clinicaVeterinaria.model.Mascota;
import co.edu.uniquindio.clinicaVeterinaria.model.Veterinario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PdfFacturaController {

	@FXML
	private Label lblSexoAtencion;

	@FXML
	private Label lblMascotaAtencion;

	@FXML
	private Label lblFacturaFecha;
	@FXML
	private Label lblFacturaHora;

	@FXML
	private Label lblNombreCliente;

	@FXML
	private Label lblVeterinarioAtencion;

	@FXML
	private Label lblDireccionCliente;

	@FXML
	private Label lblTelefonoCliente;

	@FXML
	private Label lblEstadoAtencion;

	@FXML
	private Label lblTipoAtencion;

	@FXML
	private Label lblTranatemientoAtencion;

	@FXML
	private Label lblDiagnosticoAtencion;

	@FXML
	private Label lblCodFactura;

	@FXML
	private Label lblPrecioAtencion;
	private Factura factura;

	@FXML
	public void initialize() {
		Mascota mascota = factura.getAtencionVeterinaria().getMascota();
		Veterinario veterinario = ModelFactoryController.getInstance().getVeterinario();
		Cliente dueno = mascota.getDueno();
		lblCodFactura.setText(factura.getId() + "");
		lblFacturaFecha.setText(factura.getFecha().format(DateTimeFormatter.ISO_DATE) + "");
		lblFacturaHora.setText(factura.getFecha().format(DateTimeFormatter.ISO_DATE) + "");

		lblNombreCliente.setText(dueno.getNombre());
		lblDireccionCliente.setText(dueno.getDireccion());
		lblTelefonoCliente.setText(dueno.getTelefono());
		lblEstadoAtencion.setText(factura.getAtencionVeterinaria().getEstado().name());
		lblVeterinarioAtencion.setText(veterinario.getNombre());
		lblMascotaAtencion.setText(mascota.getNombre());
		lblTipoAtencion.setText(mascota.getTipo().name());
		lblSexoAtencion.setText(mascota.getSexo().name());
		lblDiagnosticoAtencion.setText(factura.getDiagnostico());
		lblTranatemientoAtencion.setText(factura.getTratamiento());
		lblPrecioAtencion.setText(String.format(Locale.US, "%.2f", factura.getCosto()));

	}

	public void init(Factura factura) {
		this.factura = factura;
	}
}
