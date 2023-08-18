package co.edu.uniquindio.clinicaVeterinaria.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import co.edu.uniquindio.clinicaVeterinaria.exceptions.MascotaNoEncontradaExpcetion;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.MascotaYaExistenteException;

/**
 * @author juanp
 */

public class Cliente extends Persona{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cedula;
	private String direccion;
	private Map<String, Mascota> mascotas;

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
		this.mascotas = new HashMap<String, Mascota>();
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
	
	/**
	 * Verifica si una mascota ya existe en la lista. Retorna un valor booleano dependiendo de la busqueda.
	 * @param codigo
	 * @return
	 */
	public boolean verificarMascota(String codigo) {
		return mascotas.containsKey(codigo) && mascotas.get(codigo) != null;
	}
	
	/**
	 * Lanza una expcetion si el mascota no existe en la lista.
	 * @param codigo
	 * @throws MascotaNoEncontradaExpcetion
	 */
	private void throwMascotaNoEncontrada(String codigo) throws MascotaNoEncontradaExpcetion {
		if(!verificarMascota(codigo)) throw new MascotaNoEncontradaExpcetion("La mascota identificada con el codigo: " + codigo + ", no existe en la lista");
	}
	
	/**
	 * Lanza una expcetion si el mascota ya existe en la lista.
	 * @param codigo
	 * @throws MascotaYaExistenteException
	 */
	private void throwMascotaYaExistente(String codigo) throws MascotaYaExistenteException{
		if(verificarMascota(codigo)) throw new MascotaYaExistenteException("La mascota identificada con el codigo: " + codigo + ", ya existe en la lista");
	}
	
	/**
	 * Busca una <b>mascota</b> en la lista y lo retorna, lanza una exception si la mascota no existe.
	 * @param codigo
	 * @return
	 * @throws MascotaNoEncontradaExpcetion
	 */
	public Mascota buscarMascota(String codigo) throws MascotaNoEncontradaExpcetion {
		throwMascotaNoEncontrada(codigo);
		return mascotas.get(codigo);
	}
	
	/**
	 * Agrega una nueva mascota a la lista. Lanza una exception si ya existe.
	 * @param mascota
	 * @throws MascotaYaExistenteException
	 */
	public void agregarMascota(Mascota mascota) throws MascotaYaExistenteException {
		throwMascotaYaExistente(mascota.getCodigo());
		mascotas.put(mascota.getCodigo(), mascota);
	}
	
	/**
	 * Elimina una mascota de la lista y la retorna. Lanza una exception si no existe en la lista.
	 * @param codigo
	 * @return
	 * @throws MascotaNoEncontradaExpcetion
	 */
	public Mascota eliminarMascota(String codigo) throws MascotaNoEncontradaExpcetion {
		throwMascotaNoEncontrada(codigo);
		return mascotas.remove(codigo);
	}
	
	/**
	 * actualiza los datos de la mascota. Lanza una exception si no existe
	 * @param mascota
	 * @throws MascotaYaExistenteException
	 */
	public void actualizarMascota(Mascota mascota) throws MascotaNoEncontradaExpcetion {
		throwMascotaNoEncontrada(mascota.getCodigo());
		mascotas.put(mascota.getCodigo(), mascota);
	}

}
