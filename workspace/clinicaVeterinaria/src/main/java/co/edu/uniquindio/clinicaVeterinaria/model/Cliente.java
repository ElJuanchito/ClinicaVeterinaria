package co.edu.uniquindio.clinicaVeterinaria.model;

import java.util.Objects;

public class Cliente extends Persona {
	private String cedula;
	private String direccion;

	/**
	 * Constructor con parametros de la clase <b>Cliente</b>
	 * @param nombre
	 * @param correo
	 * @param telefono
	 * @param cedula
	 * @param direccion
	 */
	public Cliente(String nombre, String correo, String telefono, String cedula, String direccion) {
		super(nombre, correo, telefono);
		this.cedula = cedula;
		this.direccion = direccion;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cedula);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cedula, other.cedula);
	}

	@Override
	public String toString() {
		return "Cliente [cedula=" + cedula + ", direccion=" + direccion + ", getNombre()=" + getNombre()
				+ ", getCorreo()=" + getCorreo() + ", getTelefono()=" + getTelefono() + "]";
	}

}
