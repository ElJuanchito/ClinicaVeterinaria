package co.edu.uniquindio.clinicaVeterinaria.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import co.edu.uniquindio.clinicaVeterinaria.exceptions.AtencionNoExistenteException;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.ClienteExistenteException;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.ClienteNoExistenteException;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.MascotaNoEncontradaExpcetion;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.MascotaYaExistenteException;

/**
 * 
 * @author ElJuancho
 */
public class Clinica {
	private Veterinario[] veterinarios;
	private Map<Long, AtencionVeterinaria> citas;
	private Map<Long, Factura> facturas;
	private Map<String, Cliente> clientes;

	/**
	 * Constructor de la clase <b>Clinica</b>
	 */
	public Clinica() {
		veterinarios = new Veterinario[4];
		citas = new HashMap<Long, AtencionVeterinaria>();
		facturas = new HashMap<Long, Factura>();
		clientes = new HashMap<String, Cliente>();
	}

	public Veterinario[] getVeterinarios() {
		return veterinarios;
	}

	public void setVeterinarios(Veterinario[] veterinarios) {
		this.veterinarios = veterinarios;
	}

	public Set<AtencionVeterinaria> getCitas() {
		return citas;
	}

	public void setCitas(Set<AtencionVeterinaria> citas) {
		this.citas = citas;
	}

	public Map<Long, Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(Map<Long, Factura> facturas) {
		this.facturas = facturas;
	}

	public Map<String, Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(Map<String, Cliente> clientes) {
		this.clientes = clientes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(veterinarios);
		result = prime * result + Objects.hash(citas, clientes, facturas);
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
		Clinica other = (Clinica) obj;
		return Objects.equals(citas, other.citas) && Objects.equals(clientes, other.clientes)
				&& Objects.equals(facturas, other.facturas) && Arrays.equals(veterinarios, other.veterinarios);
	}

	@Override
	public String toString() {
		return "Clinica [veterinarios=" + Arrays.toString(veterinarios) + ", citas=" + citas + ", facturas=" + facturas
				+ ", clientes=" + clientes + "]";
	}

	// CRUD clientes

	// ------------------------------------------------------------------------
	/**
	 * Verifica si el <b>cliente</b> existe en la lista por medio de la cedula,
	 * retorna un valor booleano dependiendo de la busqueda.
	 * 
	 * @param cedula
	 * @return
	 */
	public boolean verificarCliente(String cedula) {
		return clientes.containsKey(cedula) && clientes.get(cedula) != null;
	}

	/**
	 * Busca un <b>cliente</b> en la lista y lo retorna, lanza una exception si el
	 * cliente no existe.
	 * 
	 * @param cedula
	 * @return
	 * @throws ClienteNoExistenteException
	 */
	public Cliente buscarCliente(String cedula) throws ClienteNoExistenteException {
		throwClienteNoEncontrado(cedula);
		return clientes.get(cedula);
	}

	/**
	 * Lanza una expcetion si el cliente no existe en la lista.
	 * 
	 * @param cedula
	 * @throws ClienteNoExistenteException
	 */
	private void throwClienteNoEncontrado(String cedula) throws ClienteNoExistenteException {
		if (!verificarCliente(cedula))
			throw new ClienteNoExistenteException(
					"El cliente identificado con la cedula: " + cedula + ", no existe en la lista");
	}

	/**
	 * Lanza una expcetion si el cliente ya existe en la lista.
	 * 
	 * @param cedula
	 * @throws ClienteExistenteException
	 */
	private void throwClienteYaExistente(String cedula) throws ClienteExistenteException {
		if (verificarCliente(cedula))
			throw new ClienteExistenteException(
					"El cliente identificado con la cedula: " + cedula + ", ya existe en la lista");
	}

	/**
	 * Agrega un nuevo cliente a la lista. lanza una exception si el cliente ya
	 * existe.
	 * 
	 * @param cliente
	 * @throws ClienteExistenteException
	 */
	public void agregarCliente(Cliente cliente) throws ClienteExistenteException {
		throwClienteYaExistente(cliente.getCedula());
		clientes.put(cliente.getCedula(), cliente);
	}

	/**
	 * Elimina un cliente de la lista. Lanza una exception si el cliente no existe.
	 * 
	 * @param cliente
	 * @throws ClienteNoExistenteException
	 */
	public void eliminarCliente(Cliente cliente) throws ClienteNoExistenteException {
		throwClienteNoEncontrado(cliente.getCedula());
		clientes.remove(cliente.getCedula());
	}

	/**
	 * Actualiza un cliente de la lista. Lanza una exception si el cliente no
	 * existe.
	 * 
	 * @param cliente
	 * @throws ClienteNoExistenteException
	 */
	public void actualizarCliente(Cliente cliente) throws ClienteNoExistenteException {
		throwClienteNoEncontrado(cliente.getCedula());
		clientes.put(cliente.getCedula(), cliente);
	}

	/**
	 * Agrega una mascota a un cliente de la lista. Lanza una exception si el
	 * cliente no existe o si la mascota ya existe en la lista del cliente
	 * seleccionado.
	 * 
	 * @param cliente
	 * @param mascota
	 * @throws ClienteNoExistenteException
	 * @throws MascotaYaExistenteException
	 */
	public void agregarMascota(Cliente cliente, Mascota mascota)
			throws ClienteNoExistenteException, MascotaYaExistenteException {
		throwClienteNoEncontrado(cliente.getCedula());
		cliente.agregarMascota(mascota);
		actualizarCliente(cliente);
	}

	/**
	 * Busca y retorna una mascota en la lista del cliente seleccionado. Lanza una
	 * exception si el cliente o la mascota no existen.
	 * 
	 * @param cliente
	 * @param codigo
	 * @return
	 * @throws ClienteNoExistenteException
	 * @throws MascotaNoEncontradaExpcetion
	 */
	public Mascota buscarMascota(String cedula, String codigo)
			throws ClienteNoExistenteException, MascotaNoEncontradaExpcetion {
		throwClienteNoEncontrado(cedula);
		return buscarCliente(cedula).buscarMascota(codigo);
	}

	/**
	 * Elimina una mascota en la lista del cliente seleccionado. Lanza una exception
	 * si el cliente o la mascota no existen.
	 * 
	 * @param cedula
	 * @param codigo
	 * @return
	 * @throws MascotaNoEncontradaExpcetion
	 * @throws ClienteNoExistenteException
	 * @author ElJuancho
	 */
	public Mascota eliminarMascota(String cedula, String codigo)
			throws MascotaNoEncontradaExpcetion, ClienteNoExistenteException {
		throwClienteNoEncontrado(cedula);
		Cliente aux = buscarCliente(cedula);
		Mascota mascota = aux.eliminarMascota(codigo);
		actualizarCliente(aux);
		return mascota;
	}

	/**
	 * Actualiza los datos de una de las mascotas del cliente seleccionado. Lanza
	 * una exception si el cliente o la mascota no existen.
	 * 
	 * @param cedula
	 * @param mascota
	 * @throws ClienteNoExistenteException
	 * @throws MascotaNoEncontradaExpcetion
	 * @author ElJuancho
	 */
	public void actualizarMascota(String cedula, Mascota mascota)
			throws ClienteNoExistenteException, MascotaNoEncontradaExpcetion {
		throwClienteNoEncontrado(cedula);
		Cliente aux = buscarCliente(cedula);
		aux.actualizarMascota(mascota);
		actualizarCliente(aux);
	}
	// -----------------------------------------------------------------------

	// AtencionVeterinaria

	/**
	 * Verifica si la <b>AtencionVeterinaria</b> existe en la lista por medio del codigo,
	 * retorna un valor booleano dependiendo de la busqueda.
	 * @param codigo
	 * @return
	 * @author ElJuancho
	 */
	public boolean verificarAtencion(Long codigo) {
		return citas.containsKey(codigo) && citas.get(codigo) != null;
	}
	
	/**
	 * Lanza una exception si la atencion veterinaria no existe en la lista.
	 * @param codigo
	 * @throws AtencionNoExistenteException
	 * @author ElJuancho
	 */
	private void throwCitaNoEncontrada(Long codigo) throws AtencionNoExistenteException {
		if (!verificarAtencion(codigo))
			throw new AtencionNoExistenteException(
					"La atencion veterinaria con codigo: " + codigo + ", no existe en la lista");
	}

	/**
	 * Lanza una exception si la atencion veterinaria ya existe en la lista.
	 * 
	 * @param cedula
	 * @throws ClienteExistenteException
	 */
	private void throwClienteYaExistente(String codigo) throws AExistenteException {
		if (verificarAtencion(codigo))
			throw new AtencionExistenteException(
					"La atencion veterinaria con codigo: " + codigo + ", ya existe en la lista");
	}
}
