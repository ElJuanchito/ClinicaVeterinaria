package co.edu.uniquindio.clinicaVeterinaria.tests;

import co.edu.uniquindio.clinicaVeterinaria.controllers.ModelFactoryController;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.ClienteNoExistenteException;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.FacturaNoEcontradaException;
import co.edu.uniquindio.clinicaVeterinaria.exceptions.MascotaNoEncontradaExpcetion;
import co.edu.uniquindio.clinicaVeterinaria.model.Cliente;
import co.edu.uniquindio.clinicaVeterinaria.model.Factura;
import java.util.List;

public class TestListas {

    public static void main(String args[]) throws ClienteNoExistenteException, MascotaNoEncontradaExpcetion, FacturaNoEcontradaException {
        //ModelFactoryController.getInstance().loadData();
        //System.out.println(ModelFactoryController.getInstance().getClinica().getListClientes());

      // System.out.println(ModelFactoryController.getInstance().getClinica().buscarCliente("200").buscarMascota("1"));

        //System.out.println(ModelFactoryController.getInstance().getClinica().getListaCitas());

        System.out.println(ModelFactoryController.getInstance().getClinica().buscarMascota("200", "2"));

        System.out.println(ModelFactoryController.getInstance().getClinica().getListaFacturas());

        ModelFactoryController.getInstance().getClinica().buscarFactura(null).setId(1L);
    }
}
