package co.edu.uniquindio.clinicaVeterinaria.controllers;

import co.edu.uniquindio.clinicaVeterinaria.dao.ClinicaDao;
import co.edu.uniquindio.clinicaVeterinaria.model.Clinica;
import co.edu.uniquindio.clinicaVeterinaria.model.Veterinario;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

/**
 * 
 * @Author ElJuancho
 */
public class ModelFactoryController {

	private Clinica clinica;
	private Veterinario veterinario;
	private SimpleObjectProperty<Image> propImgVeterinario;

	public ModelFactoryController() {
		propImgVeterinario = new SimpleObjectProperty<>();
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
		System.out.println("Datos cargados");
	}

	/**
	 * Guarda los datos de la clinica.
	 * 
	 * @author ElJuancho
	 */
	public void saveData() {
		ClinicaDao dao = new ClinicaDao();
		dao.saveData(clinica);
		System.out.println("Datos guardados");
	}

	public Veterinario buscarVeterinario(String codigo) {
		return getClinica().buscarVeterinario(codigo);
	}

	public void setVeterinario(String codigo) {
		veterinario = getClinica().buscarVeterinario(codigo);
		propImgVeterinario.setValue(veterinario.getFoto());
	}

	public Veterinario getVeterinario() {
		if (veterinario == null)
			setVeterinario("0001");
		return veterinario;
	}

	public SimpleObjectProperty<Image> getVeterinarioFotoProp() {
		getVeterinario();
		return propImgVeterinario;
	}
}
