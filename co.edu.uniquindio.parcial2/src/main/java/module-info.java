module co.edu.uniquindio.parcial2.prestamoapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.parcial2.prestamoapp to javafx.fxml;
    exports co.edu.uniquindio.parcial2.prestamoapp;
}