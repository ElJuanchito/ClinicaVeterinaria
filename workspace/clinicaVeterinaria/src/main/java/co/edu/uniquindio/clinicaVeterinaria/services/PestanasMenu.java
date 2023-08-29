package co.edu.uniquindio.clinicaVeterinaria.services;

public enum PestanasMenu {
	INICIO("registroCliente"), CLIENTE("registroCliente"), MASCOTA("registroMascota"), CITA("creacionAtencion"), FACTURA("FinalizarAtencion"),MORE("masFunciones"), HCLINICO("historialClinico"), HCITAS("historialCitas");

	private String fxml;

	private PestanasMenu(String fxml) {
		this.fxml = fxml;
	}

	public String getFxml() {
		return fxml;
	}
}