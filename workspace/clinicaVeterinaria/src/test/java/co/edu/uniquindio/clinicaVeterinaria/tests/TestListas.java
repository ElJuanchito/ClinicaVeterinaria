package co.edu.uniquindio.clinicaVeterinaria.tests;

import org.junit.Before;
import org.junit.Test;

import co.edu.uniquindio.clinicaVeterinaria.controllers.ModelFactoryController;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.ClienteNoExistenteException;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.FacturaNoEcontradaException;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.MascotaNoEncontradaExpcetion;

public class TestListas {

	@Before
	public void antes() {
		ModelFactoryController.getInstance().loadData();
	}

	@Test
	public void testBuscarMascotaCliente() throws MascotaNoEncontradaExpcetion, ClienteNoExistenteException {
		System.out.println(ModelFactoryController.getInstance().getClinica().getListClientes());
		System.out.println(ModelFactoryController.getInstance().getClinica().buscarCliente("200").buscarMascota("1"));
	}

	public void testBuscarMascota() throws ClienteNoExistenteException, MascotaNoEncontradaExpcetion {
		System.out.println(ModelFactoryController.getInstance().getClinica().getListaCitas());
		System.out.println(ModelFactoryController.getInstance().getClinica().buscarMascota("200", "2"));
	}

	@Test
	public static void testFcturas() throws FacturaNoEcontradaException {
		System.out.println(ModelFactoryController.getInstance().getClinica().getListaFacturas());
		ModelFactoryController.getInstance().getClinica().buscarFactura(null).setId(1L);
	}
}
