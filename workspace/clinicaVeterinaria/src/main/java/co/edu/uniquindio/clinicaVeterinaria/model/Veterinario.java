package co.edu.uniquindio.clinicaVeterinaria.model;

import java.util.Objects;

public class Veterinario extends Persona{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo;

	/**
	 * Constructor con parametros de la clase <b>Veterinario</b>
	 * @param nombre
	 * @param correo
	 * @param telefono
	 * @param codigo
	 */
	public Veterinario(String nombre, String correo, String telefono, String codigo) {
		super(nombre, correo, telefono);
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(codigo);
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
		Veterinario other = (Veterinario) obj;
		return Objects.equals(codigo, other.codigo);
	}

	@Override
	public String toString() {
		return "Veterinario [codigo=" + codigo + ", getNombre()=" + getNombre() + ", getCorreo()=" + getCorreo()
				+ ", getTelefono()=" + getTelefono() + ", getClass()=" + getClass() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
}
