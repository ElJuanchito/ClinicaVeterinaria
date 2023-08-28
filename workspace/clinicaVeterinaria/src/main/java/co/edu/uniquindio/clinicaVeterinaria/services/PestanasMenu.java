package co.edu.uniquindio.clinicaVeterinaria.services;

public enum PestanasMenu {
	INICIO("principal"), LOGIN("profileSelector"), CLIENTE("registroCliente"), MASCOTA("registroMascota"),
	CITA("creacionAtencion"), FACTURA("FinalizarAtencion"), MORE("masFunciones"), HFECHAS("historialCitas"),
	HCLINICO("historialClinico");

	private String fxml;

	private PestanasMenu(String fxml) {
		this.fxml = fxml;
	}

	public String getFxml() {
		return fxml;
	}
}