package co.edu.uniquindio.clinicaVeterinaria.controllers;

import co.edu.uniquindio.clinicaVeterinaria.dao.ClinicaDao;
import co.edu.uniquindio.clinicaVeterinaria.model.Clinica;
import co.edu.uniquindio.clinicaVeterinaria.model.Veterinario;

/**
 * 
 * @Author ElJuancho
 */
public class ModelFactoryController {

	private Clinica clinica;
	private Veterinario veterinario;

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
		if (clinica == null) {
			this.clinica = new Clinica();
			saveData();
		}

		loadData();
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

	public void setVeterinario(int i) {
		Veterinario[] veterinarios = getClinica().getVeterinarios();
		veterinario = veterinarios[i < 0 || i >= veterinarios.length ? 0 : i];
	}

	public Veterinario getVeterinario() {
		return veterinario;
	}
}
