module co.edu.uniquindio.clinicaVeterinaria {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.uniquindio.clinicaVeterinaria to javafx.fxml;
    exports co.edu.uniquindio.clinicaVeterinaria.application;
}
