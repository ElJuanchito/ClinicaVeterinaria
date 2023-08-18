module co.edu.uniquindio.clinicaVeterinaria {
    requires javafx.controls;
    requires javafx.fxml;
    requires jpro.webapi;
	requires java.desktop;

    exports co.edu.uniquindio.clinicaVeterinaria.application;
    opens co.edu.uniquindio.clinicaVeterinaria.controllers to javafx.fxml;
    exports co.edu.uniquindio.clinicaVeterinaria.controllers to javafx.fxml;
}
