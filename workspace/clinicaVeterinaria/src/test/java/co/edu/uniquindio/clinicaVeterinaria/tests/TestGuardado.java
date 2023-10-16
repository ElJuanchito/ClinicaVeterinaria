
package co.edu.uniquindio.clinicaVeterinaria.tests;

import org.junit.Test;

import co.edu.uniquindio.clinicaVeterinaria.model.Clinica;
import co.edu.uniquindio.clinicaVeterinaria.services.ClinicaDao;

public class TestGuardado {

	@Test
	public void pruebaGuardado() {
		Clinica clinica = new Clinica();

		ClinicaDao dao = new ClinicaDao();
		dao.saveData(clinica);
	}
}
