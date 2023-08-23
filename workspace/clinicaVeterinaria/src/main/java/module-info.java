module co.edu.uniquindio.clinicaVeterinaria {
	requires javafx.controls;
	requires javafx.fxml;
	requires jpro.webapi;
	requires javafx.graphics;
	requires one.jpro.routing.core;
	requires org.apache.pdfbox;
	requires de.sandec.jmemorybuddy;

	exports co.edu.uniquindio.clinicaVeterinaria.application;
	exports co.edu.uniquindio.clinicaVeterinaria.services;
	exports co.edu.uniquindio.clinicaVeterinaria.tests;

	opens co.edu.uniquindio.clinicaVeterinaria.controllers to javafx.fxml;

	exports co.edu.uniquindio.clinicaVeterinaria.controllers to javafx.fxml;
}
