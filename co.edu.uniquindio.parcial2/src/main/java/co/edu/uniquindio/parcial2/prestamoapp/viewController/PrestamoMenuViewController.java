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

public class PrestamoMenuViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private SplitPane sp_GestionPrestamo;

    @FXML
    private Tab tab_cliente;

    @FXML
    private SplitPane sp_ParcialDos;

    @FXML
    private Tab tab_EntregaParcial2;

    @FXML
    private Tab tab_empleado;

    @FXML
    private SplitPane sp_GestionEmpleado;

    @FXML
    private Tab tab_administrativa;

    @FXML
    private SplitPane sp_Administrativa;

    @FXML
    private Tab tab_objeto;

    @FXML
    private SplitPane sp_GestionObjeto;

    @FXML
    private TabPane tabPane_menuPrincipal;

    @FXML
    private SplitPane sp_GestionClientes;

    @FXML
    private Tab tab_prestamo;

    @FXML
    void initialize() {
        configurarCambioDeTab();
    }

    /**
     * Metodo para configurar el cambio de tab
     */
    private void configurarCambioDeTab() {
        tabPane_menuPrincipal.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (newTab == tab_cliente) {
                cargarVistaGestionCliente();
            } else if (newTab == tab_empleado) {
                cargarVistaGestionEmpleado();
            } else if (newTab == tab_objeto) {
                cargarVistaGestionObjeto();
            } else if (newTab == tab_prestamo) {
                cargarVistaGestionPrestamo();
            } else if (newTab == tab_administrativa) {
                cargarVistaGestionAdministrativa();
            } else if (newTab == tab_EntregaParcial2) {
                cargarVistaParcial();
            }
        });
    }

    private void cargarVistaGestionCliente() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/parcial2/prestamoapp/GestionCliente.fxml"));
            AnchorPane nuevaVista = loader.load();
            GestionClienteViewController viewController = loader.getController();
            sp_GestionClientes.getItems().setAll(nuevaVista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarVistaGestionEmpleado() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/parcial2/prestamoapp/GestionEmpleado.fxml"));
            AnchorPane nuevaVista = loader.load();
            GestionEmpleadoViewController viewController = loader.getController();
            sp_GestionEmpleado.getItems().setAll(nuevaVista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarVistaGestionObjeto() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/parcial2/prestamoapp/GestionObjeto.fxml"));
            AnchorPane nuevaVista = loader.load();
            GestionObjetoViewController viewController = loader.getController();
            sp_GestionObjeto.getItems().setAll(nuevaVista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarVistaGestionPrestamo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/parcial2/prestamoapp/GestionPrestamo.fxml"));
            AnchorPane nuevaVista = loader.load();
            GestionPrestamoViewController viewController = loader.getController();
            sp_GestionPrestamo.getItems().setAll(nuevaVista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarVistaGestionAdministrativa() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/parcial2/prestamoapp/MenuAdministrativa.fxml"));
            AnchorPane nuevaVista = loader.load();
            MenuAdministrativaViewController viewController = loader.getController();
            sp_Administrativa.getItems().setAll(nuevaVista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarVistaParcial() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/parcial2/prestamoapp/MenuParcial.fxml"));
            AnchorPane nuevaVista = loader.load();
            sp_ParcialDos.getItems().setAll(nuevaVista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}