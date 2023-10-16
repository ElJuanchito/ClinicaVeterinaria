open module co.edu.uniquindio.clinicaVeterinaria {
	requires javafx.controls;
	requires javafx.fxml;
	requires jpro.webapi;
	requires transitive javafx.graphics;
	requires transitive one.jpro.routing.core;
	requires org.apache.pdfbox;
	requires de.sandec.jmemorybuddy;
	requires javafx.swing;
	requires java.desktop;
	requires javafx.base;

	exports co.edu.uniquindio.clinicaVeterinaria.application;
	exports co.edu.uniquindio.clinicaVeterinaria.model;
	exports co.edu.uniquindio.clinicaVeterinaria.services;

	exports co.edu.uniquindio.clinicaVeterinaria.controllers to javafx.fxml;
}
