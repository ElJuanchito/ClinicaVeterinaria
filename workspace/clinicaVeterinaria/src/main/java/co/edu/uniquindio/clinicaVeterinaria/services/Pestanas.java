package co.edu.uniquindio.clinicaVeterinaria.services;

public enum Pestanas {
	INICIO("principal"), LOGIN("profileSelector");

	private String fxml;

	private Pestanas(String fxml) {
		this.fxml = fxml;
	}

	public String getFxml() {
		return fxml;
	}
}