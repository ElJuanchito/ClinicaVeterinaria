package co.edu.uniquindio.clinicaVeterinaria.services;

import java.io.IOException;

import co.edu.uniquindio.clinicaVeterinaria.controllers.PdfFacturaController;
import co.edu.uniquindio.clinicaVeterinaria.model.Factura;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.Printer.MarginType;
import javafx.print.PrinterJob;
import javafx.scene.Parent;

public class GeneracionPdf {
	private Parent root;
	private Factura factura;

	public GeneracionPdf(Factura factura) {
		this.factura = factura;
		generarPlantilla();
	}

	private void generarPlantilla() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/co/edu/uniquindio/clinicaVeterinaria/view/pdfFactura.fxml"));
		PdfFacturaController controller = new PdfFacturaController();
		controller.init(factura);
		loader.setController(controller);
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ejecutarImpresion() {
		Platform.runLater(() -> {
			PrinterJob job = PrinterJob.createPrinterJob();
			Printer printer = Printer.getDefaultPrinter();
			PageLayout lay = printer.createPageLayout(Paper.A3, PageOrientation.PORTRAIT, MarginType.DEFAULT);
			job.setPrinter(printer);
			if (job != null) {
				boolean success = job.printPage(lay, root);
				if (success) {
					job.endJob();
				}
			}
		});
	}
}
