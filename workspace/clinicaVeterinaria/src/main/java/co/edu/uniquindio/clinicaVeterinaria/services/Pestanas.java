package co.edu.uniquindio.clinicaVeterinaria.services;

public enum Pestanas {
	INICIO("principal"), LOGIN("profileSelector"), CLIENTE("registroCliente"), MASCOTA("registroMascota"), CITA("creacionAtencion"), FACTURA("FinalizarAtencion");

	private String fxml;

	private Pestanas(String fxml) {
		this.fxml = fxml;
	}

	public String getFxml() {
		return fxml;
	}
}