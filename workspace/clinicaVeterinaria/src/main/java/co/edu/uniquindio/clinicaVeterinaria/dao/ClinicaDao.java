/**
 * 
 */
package co.edu.uniquindio.clinicaVeterinaria.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import co.edu.uniquindio.clinicaVeterinaria.model.Clinica;
import co.edu.uniquindio.clinicaVeterinaria.utils.UtilsSerializable;

/**
 * 
 * @Author ElJuancho
 */
public class ClinicaDao {
	
	private final String FILE = UtilsSerializable.getArchivo();

	public ClinicaDao() {

	}

	/**
	 * Guarda la clinica en un archivo por medio de serializacion.
	 * @param clinica
	 * @author ElJuancho
	 */
	public void saveData(Clinica clinica) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
			oos.writeObject(clinica);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Obtiene la clinica del archivo.
	 * @return
	 * @author ElJuancho
	 */
	public Clinica loadData(){
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
			return (Clinica) ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
