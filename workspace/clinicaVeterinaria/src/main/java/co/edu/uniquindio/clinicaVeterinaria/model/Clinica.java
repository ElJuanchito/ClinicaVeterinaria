package co.edu.uniquindio.clinicaVeterinaria.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import co.edu.uniquindio.clinicaVeterinaria.exceptions.AtencionExistenteException;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.AtencionNoExistenteException;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.ClienteExistenteException;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.ClienteNoExistenteException;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.FacturaNoEcontradaException;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.FacturaYaExistenteException;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.MascotaNoEncontradaExpcetion;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.MascotaYaExistenteException;

/**
 * 
 * @author ElJuancho
 */
public class Clinica implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	public Map<Long, AtencionVeterinaria> getCitas() {
		return citas;
	}

	public void setCitas(Map<Long, AtencionVeterinaria> citas) {
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
	 * Verifica si la <b>AtencionVeterinaria</b> existe en la lista por medio del
	 * codigo, retorna un valor booleano dependiendo de la busqueda.
	 * 
	 * @param codigo
	 * @return
	 * @author ElJuancho
	 */
	public boolean verificarAtencion(Long codigo) {
		return citas.containsKey(codigo) && citas.get(codigo) != null;
	}

	/**
	 * Lanza una exception si la atencion veterinaria no existe en la lista.
	 * 
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
	 * @param codigo
	 * @throws AtencionExistenteException
	 * @author ElJuancho
	 */
	private void throwCitaYaExistente(Long codigo) throws AtencionExistenteException {
		if (verificarAtencion(codigo))
			throw new AtencionExistenteException(
					"La atencion veterinaria con codigo: " + codigo + ", ya existe en la lista");
	}

	/**
	 * Agrega una nueva atencion veterinaria a la lista de citas. Lanza una
	 * exception si ya existe.
	 * 
	 * @param cita
	 * @throws AtencionExistenteException
	 * @author ElJuancho
	 */
	public void agregarCita(AtencionVeterinaria cita) throws AtencionExistenteException {
		throwCitaYaExistente(cita.getCodigo());
		citas.put(cita.getCodigo(), cita);
	}

	/**
	 * Busca y retorna una atencion veterinaria en la lista de citas. Lanza una
	 * exception si la atencion no existe.
	 * 
	 * @param codigo
	 * @return
	 * @throws AtencionNoExistenteException
	 * @author ElJuancho
	 */
	public AtencionVeterinaria buscarCita(Long codigo) throws AtencionNoExistenteException {
		throwCitaNoEncontrada(codigo);
		return citas.get(codigo);
	}

	/**
	 * Elimina y retorna una atencion veterinaria de la lista. Lanza una exception
	 * 
	 * @param codigo
	 * @return
	 * @throws AtencionNoExistenteException
	 * @author ElJuancho
	 */
	public AtencionVeterinaria eliminarCita(Long codigo) throws AtencionNoExistenteException {
		throwCitaNoEncontrada(codigo);
		return citas.remove(codigo);
	}

	/**
	 * Actualiza los datos de una cita. Lanza una exception si no existe.
	 * 
	 * @param cita
	 * @throws AtencionNoExistenteException
	 * @author ElJuancho
	 */
	public void actualizarCita(AtencionVeterinaria cita) throws AtencionNoExistenteException {
		throwCitaNoEncontrada(cita.getCodigo());
		citas.put(cita.getCodigo(), cita);
	}

	/**
	 * Actualiza el estado de una cita. Lanza una exception si no existe en la
	 * lista.
	 * 
	 * @param codigo
	 * @param estado
	 * @throws AtencionNoExistenteException
	 * @author ElJuancho
	 */
	public void actualizarEstadoCita(Long codigo, Estado estado) throws AtencionNoExistenteException {
		throwCitaNoEncontrada(codigo);
		AtencionVeterinaria aux = buscarCita(codigo);
		aux.setEstado(estado);
		actualizarCita(aux);
	}

	// CRUD Facturas

	// ------------------------------------------------------------------------------

	/**
	 * Verifica si la <b>factura</b> existe en la lista por medio del id, retorna un
	 * valor booleano dependiendo de la busqueda.
	 * 
	 * @param id
	 * @return
	 * @author ElJuancho
	 */
	public boolean verificarFactura(Long id) {
		return facturas.containsKey(id) && facturas.get(id) != null;
	}

	/**
	 * Lanza una exception si la factura no existe en la lista.
	 * 
	 * @param id
	 * @throws FacturaNoEcontradaException
	 * @author ElJuancho
	 */
	private void throwFacturaNoEncontrada(Long id) throws FacturaNoEcontradaException {
		if (!verificarFactura(id))
			throw new FacturaNoEcontradaException("La factura identificada con el id " + id + "no existe en la lista");
	}

	/**
	 * Lanza una exception si la factura ya existe en la lista.
	 * 
	 * @param id
	 * @throws FacturaYaExistenteException
	 * @author ElJuancho
	 */
	private void throwFacturaYaExistente(Long id) throws FacturaYaExistenteException {
		if (verificarFactura(id))
			throw new FacturaYaExistenteException("La factura identificada con el id " + id + "ya existe en la lista");
	}

	/**
	 * Busca una factura en la lista. Lanza una exception si la factura no existe en
	 * la lista.
	 * 
	 * @param id
	 * @return
	 * @throws FacturaNoEcontradaException
	 * @author ElJuancho
	 */
	public Factura buscarFactura(Long id) throws FacturaNoEcontradaException {
		throwFacturaNoEncontrada(id);
		return facturas.get(id);
	}

	/**
	 * Agrega un factura a la lista. Lanza una exception si la factura ya existe.
	 * 
	 * @param factura
	 * @throws FacturaYaExistenteException
	 * @author ElJuancho
	 */
	public void agregarFactura(Factura factura) throws FacturaYaExistenteException {
		throwFacturaYaExistente(factura.getId());
		facturas.put(factura.getId(), factura);
	}

	/**
	 * Elimina una factura de la lista. Lanza una exception si la factura no existe.
	 * 
	 * @param id
	 * @return
	 * @throws FacturaNoEcontradaException
	 * @author ElJuancho
	 */
	public Factura eliminarFactura(Long id) throws FacturaNoEcontradaException {
		throwFacturaNoEncontrada(id);
		return facturas.remove(id);
	}

	// ------------------------------------------------------------------------

	// La clínica desea contar con una funcionalidad que le permita obtener el
	// historial clínico de una mascota dada la cédula del dueño y el nombre de la
	// mascota.

	/**
	 * Retorna una lista de citas deacuerdo al nombre de la mascota y a la cedula
	 * del dueno.
	 * 
	 * @param cedula
	 * @param nombre
	 * @return
	 * @author ElJuancho
	 */
	public List<AtencionVeterinaria> obtenerHistorialClinico(String cedula, String nombre) {
		return citas.values().stream().filter(
				c -> c.getMascota().getNombre().equals(nombre) && c.getMascota().getDueno().getCedula().equals(cedula))
				.collect(Collectors.toList());
	}

	// La clínica desea saber cuántas citas se han solicitado en un rango de días.
	// Se debe indicar la fecha de inicio y de fin.
	
	/**
	 * Retorna una lista con las citas solicitadas en un rango de fechas.
	 * @param inicio
	 * @param fin
	 * @return
	 * @author ElJuancho
	 */
	public List<AtencionVeterinaria> citasEnRangodeDias(LocalDate inicio, LocalDate fin) {
		return citas.values().stream().filter(cita -> cita.enRangoDeFecha(inicio, fin)).collect(Collectors.toList());
	}
}
