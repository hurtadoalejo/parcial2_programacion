module co.edu.uniquindio.parcial2.prestamoapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.httpserver;


    opens co.edu.uniquindio.parcial2.prestamoapp to javafx.fxml;
    exports co.edu.uniquindio.parcial2.prestamoapp;

    opens co.edu.uniquindio.parcial2.prestamoapp.controller to javafx.fxml;
    exports co.edu.uniquindio.parcial2.prestamoapp.controller;

    opens co.edu.uniquindio.parcial2.prestamoapp.viewController to javafx.fxml;
    exports co.edu.uniquindio.parcial2.prestamoapp.viewController;
    exports co.edu.uniquindio.parcial2.prestamoapp.model;
    opens co.edu.uniquindio.parcial2.prestamoapp.model to javafx.fxml;
}