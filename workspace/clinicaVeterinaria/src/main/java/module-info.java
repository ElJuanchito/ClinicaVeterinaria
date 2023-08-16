module co.edu.uniquindio.clinicaVeterinaria {
    requires javafx.controls;
    requires javafx.fxml;

    requires jpro.webapi;

    opens co.edu.uniquindio.clinicaVeterinaria.view to javafx.fxml;
    exports co.edu.uniquindio.clinicaVeterinaria.application;
}
