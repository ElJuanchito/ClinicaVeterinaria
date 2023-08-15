package co.edu.uniquindio.clinicaVeterinaria.model;

import java.util.Objects;

public class Mascota {
	private String nombre;
	private Integer edad;
	private String raza;
	private Tipo tipo;
	private Sexo sexo;

	/**
	 * Constructor base de la clase <b>Mascota</b>
	 */
	public Mascota() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor con parametros de la clase <b>Mascota</b>
	 * 
	 * @param nombre
	 * @param edad
	 * @param raza
	 * @param tipo
	 * @param sexo
	 */
	public Mascota(String nombre, Integer edad, String raza, Tipo tipo, Sexo sexo) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.raza = raza;
		this.tipo = tipo;
		this.sexo = sexo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(edad, nombre, raza, sexo, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mascota other = (Mascota) obj;
		return Objects.equals(edad, other.edad) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(raza, other.raza) && sexo == other.sexo && tipo == other.tipo;
	}

	@Override
	public String toString() {
		return "Mascota [nombre=" + nombre + ", edad=" + edad + ", raza=" + raza + ", tipo=" + tipo + ", sexo=" + sexo
				+ "]";
	}
	
	
}
