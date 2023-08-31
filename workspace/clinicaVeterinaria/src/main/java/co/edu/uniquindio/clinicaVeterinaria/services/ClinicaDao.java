/**
 * 
 */
package co.edu.uniquindio.clinicaVeterinaria.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import co.edu.uniquindio.clinicaVeterinaria.model.Clinica;

/**
 * 
 * @Author ElJuancho
 */
public class ClinicaDao {

	private static final String ARCHIVO = "../../database/persistence.dat";

	/**
	 * @return the archivo
	 */
	public static String getArchivo() {
		return ARCHIVO;
	}

	public ClinicaDao() {

	}

	/**
	 * Guarda la clinica en un archivo por medio de serializacion.
	 * 
	 * @param clinica
	 * @author ElJuancho
	 */
	public void saveData(Clinica clinica) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getArchivo()))) {
			oos.writeObject(clinica);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Obtiene la clinica del archivo.
	 * 
	 * @return
	 * @author ElJuancho
	 */
	public Clinica loadData() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getArchivo()))) {
			return (Clinica) ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
