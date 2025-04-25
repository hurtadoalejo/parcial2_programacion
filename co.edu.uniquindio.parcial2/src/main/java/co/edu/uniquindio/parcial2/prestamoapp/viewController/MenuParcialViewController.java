package co.edu.uniquindio.parcial2.prestamoapp.viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class MenuParcialViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private SplitPane sp_primero;

    @FXML
    private SplitPane sp_segundo;

    @FXML
    private SplitPane sp_tercero;

    @FXML
    private Tab tab_segundo;

    @FXML
    private Tab tab_primero;

    @FXML
    private TabPane tabPane_menuPrincipal;

    @FXML
    private Tab tab_tercero;

    @FXML
    void initialize() {
        configurarCambioDeTab();
        cargarVistaListaEmpleados();
        cargarVistaListaObjetos();
        cargarVistaListaPrestamos();
    }

    private void configurarCambioDeTab() {
        tabPane_menuPrincipal.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (newTab == tab_primero) {
                cargarVistaListaEmpleados();
            } else if (newTab == tab_segundo) {
                cargarVistaListaObjetos();
            } else if (newTab == tab_tercero) {
                cargarVistaListaPrestamos();
            }
        });
    }

    private void cargarVistaListaEmpleados() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/parcial2/prestamoapp/ListaEmpleados.fxml"));
            AnchorPane nuevaVista = loader.load();
            sp_primero.getItems().setAll(nuevaVista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarVistaListaObjetos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/parcial2/prestamoapp/ListaObjetos.fxml"));
            AnchorPane nuevaVista = loader.load();
            sp_segundo.getItems().setAll(nuevaVista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarVistaListaPrestamos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/parcial2/prestamoapp/ListaPrestamos.fxml"));
            AnchorPane nuevaVista = loader.load();
            sp_tercero.getItems().setAll(nuevaVista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}