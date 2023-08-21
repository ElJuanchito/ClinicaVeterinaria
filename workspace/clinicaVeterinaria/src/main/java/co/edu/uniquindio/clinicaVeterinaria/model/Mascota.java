package co.edu.uniquindio.clinicaVeterinaria.model;

import java.io.Serializable;
import java.util.Objects;

public class Mascota implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo;
	private Cliente dueno;
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
	public Mascota(String codigo, Cliente dueno, String nombre, Integer edad, String raza, Tipo tipo, Sexo sexo) {
		super();
		this.codigo = codigo;
		this.dueno = dueno;
		this.nombre = nombre;
		this.edad = edad;
		this.raza = raza;
		this.tipo = tipo;
		this.sexo = sexo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	
	/**
	 * @return the dueno
	 */
	public Cliente getDueno() {
		return dueno;
	}

	/**
	 * @param dueno the dueno to set
	 */
	public void setDueno(Cliente dueno) {
		this.dueno = dueno;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
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
		return Objects.equals(codigo, other.codigo);
	}

	@Override
	public String toString() {
		return "Mascota [codigo=" + codigo + ", nombre=" + nombre + ", edad=" + edad + ", raza=" + raza + ", tipo="
				+ tipo + ", sexo=" + sexo + "]";
	}
	
}
