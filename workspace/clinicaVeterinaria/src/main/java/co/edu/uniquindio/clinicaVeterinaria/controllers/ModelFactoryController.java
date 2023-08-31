package co.edu.uniquindio.clinicaVeterinaria.controllers;

import java.util.List;

import co.edu.uniquindio.clinicaVeterinaria.exceptions.ClienteNoExistenteException;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.MascotaNoEncontradaExpcetion;
import co.edu.uniquindio.clinicaVeterinaria.model.Cliente;
import co.edu.uniquindio.clinicaVeterinaria.model.Clinica;
import co.edu.uniquindio.clinicaVeterinaria.model.Mascota;
import co.edu.uniquindio.clinicaVeterinaria.model.Veterinario;
import co.edu.uniquindio.clinicaVeterinaria.services.ClinicaDao;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

/**
 * 
 * @Author ElJuancho
 */
public class ModelFactoryController {

	private Clinica clinica;
	private SimpleObjectProperty<Image> propImgVeterinario;
	private SimpleObjectProperty<Mascota> propMascotaCreacionCita;
	private SimpleObjectProperty<Cliente> propClienteCreacionCita;
	private SimpleObjectProperty<Veterinario> propVeterinarioSel;

	public ModelFactoryController() {
		propImgVeterinario = new SimpleObjectProperty<>();
		propMascotaCreacionCita = new SimpleObjectProperty<>();
		propClienteCreacionCita = new SimpleObjectProperty<>();
		propVeterinarioSel = new SimpleObjectProperty<>();

		propClienteCreacionCita.addListener((observable, oldValue, newValue) -> propMascotaCreacionCita.setValue(null));
	}

	public static class Singleton {
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	public static ModelFactoryController getInstance() {
		return Singleton.eINSTANCE;
	}

	/**
	 * Obtiene una instancia de la clinica por medio del patron Singleton.
	 * 
	 * @return
	 * @author ElJuancho
	 */
	public Clinica getClinica() {
		loadData();
		if (clinica == null) {
			this.clinica = new Clinica();
			saveData();
		}

		return clinica;

	}

	/**
	 * Carga los datos y actualiza la clinica.
	 * 
	 * @author ElJuancho
	 */
	public void loadData() {
		ClinicaDao dao = new ClinicaDao();
		clinica = dao.loadData();
	}

	/**
	 * Guarda los datos de la clinica.
	 * 
	 * @author ElJuancho
	 */
	public void saveData() {
		ClinicaDao dao = new ClinicaDao();
		dao.saveData(clinica);
	}

	public Veterinario buscarVeterinario(String codigo) {
		return getClinica().buscarVeterinario(codigo);
	}

	public void setVeterinario(String codigo) {
		propVeterinarioSel.setValue(getClinica().buscarVeterinario(codigo));
		propImgVeterinario.setValue(propVeterinarioSel.getValue().getFoto());
	}

	public Veterinario getVeterinario() {
		if (propVeterinarioSel.getValue() == null)
			setVeterinario("0001");
		return propVeterinarioSel.getValue();
	}

	public List<Cliente> filtrarClienteCedu(String cad) {
		return getClinica().filtrarClienteCedu(cad);
	}

	public void setMascota(String codigo) throws ClienteNoExistenteException, MascotaNoEncontradaExpcetion {
		if (getCliente() == null)
			throw new ClienteNoExistenteException("El cliente no ha sido seleccionado");
		propMascotaCreacionCita.setValue(getClinica().buscarMascota(getCliente().getCedula(), codigo));
	}

	public Mascota getMascota() {
		return propMascotaCreacionCita.getValue();
	}

	public void setCliente(String cedula) throws ClienteNoExistenteException {
		propClienteCreacionCita.setValue(getClinica().buscarCliente(cedula));
	}

	public Cliente getCliente() {
		return propClienteCreacionCita.getValue();
	}

	public List<Mascota> filtrarMascotaPorCliente(String nombre) throws ClienteNoExistenteException {
		if (getCliente() == null)
			throw new ClienteNoExistenteException("El cliente no ha sido seleccionado");
		return getClinica().filtrarMascotaPorCliente(getCliente().getCedula(), nombre);
	}

	public SimpleObjectProperty<Image> getVeterinarioFotoProp() {
		getVeterinario();
		return propImgVeterinario;
	}

	public SimpleObjectProperty<Cliente> getPropClienteCita() {
		return propClienteCreacionCita;
	}

	public SimpleObjectProperty<Mascota> getPropMascotaCita() {
		return propMascotaCreacionCita;
	}

	public SimpleObjectProperty<Veterinario> getPropVeterinarioSel() {
		return propVeterinarioSel;
	}
}
