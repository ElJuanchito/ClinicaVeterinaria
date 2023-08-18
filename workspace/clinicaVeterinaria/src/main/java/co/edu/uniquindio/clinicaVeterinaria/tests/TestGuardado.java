/**
 * 
 */
package co.edu.uniquindio.clinicaVeterinaria.tests;

import co.edu.uniquindio.clinicaVeterinaria.dao.ClinicaDao;
import co.edu.uniquindio.clinicaVeterinaria.model.Clinica;

/**
 * 
 * @Author ElJuancho
 */
public class TestGuardado {
	
	public static void main(String[] args) {
		Clinica clinica = new Clinica();
		
		ClinicaDao dao = new ClinicaDao();
		dao.saveData(clinica);
	}
}
