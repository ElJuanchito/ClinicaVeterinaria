package co.edu.uniquindio.clinicaVeterinaria.services;

public enum PestanasMenu {
	INICIO("registroCliente"), CLIENTE("registroCliente"), MASCOTA("registroMascota"), CITA("tablaClienteCita"), TMASCOTA("tablaMascotaCita"), CONCRETAR("concretarAtencion"), FACTURA("FinalizarAtencion"),MORE("masFunciones"), HCLINICO("historialClinico"), HCITAS("historialCitas");

	private String fxml;

	private PestanasMenu(String fxml) {
		this.fxml = fxml;
	}

	public String getFxml() {
		return fxml;
	}

	@Override
	public String toString() {
		return "PestanasMenu[" + fxml + "]";
	}
}