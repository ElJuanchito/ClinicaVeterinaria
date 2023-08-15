package co.edu.uniquindio.clinicaVeterinaria.model;

import java.util.Objects;

public abstract class Persona {
	private String nombre;
	private String correo;
	private String telefono;

	/**
	 * Constructor base de la clase <b>Persona</b>
	 */
	public Persona() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructor con parametros de la clase <b>Persona</b>
	 * @param nombre
	 * @param correo
	 * @param telefono
	 */
	public Persona(String nombre, String correo, String telefono) {
		super();
		this.nombre = nombre;
		this.correo = correo;
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public int hashCode() {
		return Objects.hash(correo, nombre, telefono);
	}

}
