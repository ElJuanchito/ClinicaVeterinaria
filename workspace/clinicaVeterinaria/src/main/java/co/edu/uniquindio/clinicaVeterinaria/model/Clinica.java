package co.edu.uniquindio.clinicaVeterinaria.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import co.edu.uniquindio.clinicaVeterinaria.exceptions.AtencionNoExistenteException;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.ClienteExistenteException;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.ClienteNoExistenteException;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.FacturaNoEcontradaException;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.MascotaNoEncontradaExpcetion;
import javafx.scene.image.Image;

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
		veterinarios[0] = new Veterinario("Dr. Perdomo", "juanm.perdomo@uqvirtual.edu.co", "3225179118", "0001", new Image(Clinica.class.getResourceAsStream("/co/edu/uniquindio/clinicaVeterinaria/sources/juancho.png")));
		veterinarios[1] = new Veterinario("Dr. Bayter", "breynera.sanchezq@uqvirtual.edu.co", "3006123593", "0002", new Image(Clinica.class.getResourceAsStream("/co/edu/uniquindio/clinicaVeterinaria/sources/alejo.png")));
		veterinarios[2] = new Veterinario("Dr. Quintero", "santiago.quinterou@uqvirtual.edu.co", "3147830068", "0003", new Image(Clinica.class.getResourceAsStream("/co/edu/uniquindio/clinicaVeterinaria/sources/santiago.png")));
		veterinarios[3] = new Veterinario("Dr. Amador", "juanm.amadorr@uqvirtual.edu.co", "3136253916", "0004", new Image(Clinica.class.getResourceAsStream("/co/edu/uniquindio/clinicaVeterinaria/sources/amador.png")));
		citas = new HashMap<Long, AtencionVeterinaria>();
		facturas = new HashMap<Long, Factura>();
		clientes = new HashMap<String, Cliente>();
	}

	public Veterinario buscarVeterinario(String codigo) {
		for (Veterinario veterinario : veterinarios)
			if (veterinario.getCodigo().equals(codigo))
				return veterinario;
		return null;
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
	 * cliente no existe.
	 * 
	 * @param cliente
	 * @param mascota
	 * @throws ClienteNoExistenteException
	 */
	public void agregarMascota(Cliente cliente, Mascota mascota)
			throws ClienteNoExistenteException {
		throwClienteNoEncontrado(cliente.getCedula());
		cliente.agregarMascota(mascota);
		actualizarCliente(cliente);
	}

	/**
	 * Busca y retorna una mascota en la lista del cliente seleccionado. Lanza una
	 * exception si el cliente o la mascota no existen.
	 * 
	 * @param cedula
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
	 * Crea un codigo libre para la Atencion Veterinaria
	 * 
	 * @param codigo
	 * @throws AtencionExistenteException
	 * @author ElJuancho
	 */
	private void crearCodigoLibreAtencion() {
		while (verificarAtencion(AtencionVeterinaria.getLong()))
			AtencionVeterinaria.incrementLong();
	}

	/**
	 * Agrega una nueva atencion veterinaria a la lista de citas. Lanza una
	 * exception si ya existe.
	 * 
	 * @param cita
	 * @author ElJuancho
	 */
	public void agregarCita(AtencionVeterinaria cita) {
		crearCodigoLibreAtencion();
		cita.setCodigo(AtencionVeterinaria.getLong());
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
	 * @author ElJuancho
	 */
	private void buscarIdLibreFactura() {
		while (verificarFactura(Factura.getLong()))
			Factura.incrementLong();
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
	 * Agrega una factura a la lista
	 * 
	 * @param factura
	 * @author ElJuancho
	 */
	public void agregarFactura(Factura factura) {
		buscarIdLibreFactura();
		factura.setId(Factura.getLong());
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
	 * 
	 * @param inicio
	 * @param fin
	 * @return
	 * @author ElJuancho
	 */
	public List<AtencionVeterinaria> citasEnRangoDeDias(LocalDate inicio, LocalDate fin) {
		return citas.values().stream().filter(cita -> cita.enRangoDeFecha(inicio, fin)).collect(Collectors.toList());
	}

	/**
	 * Retorna una List con las citas.
	 * 
	 * @return
	 */
	public List<AtencionVeterinaria> getListaCitas() {
		return new ArrayList<AtencionVeterinaria>(citas.values());
	}

	/**
	 * Retorna una List con las facturas.
	 * 
	 * @return
	 */
	public List<Factura> getListaFacturas() {
		return new ArrayList<Factura>(facturas.values());
	}

	/**
	 * Retorna una List con los clientes.
	 * 
	 * @return
	 */
	public List<Cliente> getListClientes() {
		return new ArrayList<Cliente>(clientes.values());
	}
	
	/**
	 * Retorna una List con los veterinarios.
	 * 
	 * @return
	 */
	public List<Veterinario> getListaVeterinarios() {
		return Arrays.asList(veterinarios);
	}
	
	
	/**
	 *  Retorna una lista filtrada con el parametro ingresado (empieza por)
	 * @param cedula
	 * @return
	 */
	public List<Cliente> filtrarClienteCedu(String cedula) {
		return getListClientes().stream().filter(c -> c.cedulaEmpiezaPor(cedula)).collect(Collectors.toList());
	}
}
