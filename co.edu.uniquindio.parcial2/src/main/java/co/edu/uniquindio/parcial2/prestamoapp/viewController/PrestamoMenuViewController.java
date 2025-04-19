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
    private Tab tab_cliente;

    @FXML
    private Tab tab_empleado;

    @FXML
    private Tab tab_objeto;

    @FXML
    private Tab tab_administrador;

    @FXML
    private TabPane tabPane_menuPrincipal;

    @FXML
    private SplitPane sp_GestionClientes;

    @FXML
    private Tab tab_prestamo;

    /**
     * Metodo para configurar el cambio de tab
     */
    private void configurarCambioDeTab() {
        tabPane_menuPrincipal.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (newTab == tab_cliente) {
                cargarVistaGestionCliente();
            } else if (newTab == tab_empleado) {
                System.out.println();
            } else if (newTab == tab_objeto) {
                System.out.println();
            } else if (newTab == tab_prestamo) {
                System.out.println();
            } else if (newTab == tab_administrador) {
                System.out.println();
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

    @FXML
    void initialize() {
        assert tab_cliente != null : "fx:id=\"tab_cliente\" was not injected: check your FXML file 'PrestamoMenu.fxml'.";
        assert tab_empleado != null : "fx:id=\"tab_empleado\" was not injected: check your FXML file 'PrestamoMenu.fxml'.";
        assert tab_objeto != null : "fx:id=\"tab_objeto\" was not injected: check your FXML file 'PrestamoMenu.fxml'.";
        assert tab_administrador != null : "fx:id=\"tab_administrador\" was not injected: check your FXML file 'PrestamoMenu.fxml'.";
        assert tabPane_menuPrincipal != null : "fx:id=\"tabPane_menuPrincipal\" was not injected: check your FXML file 'PrestamoMenu.fxml'.";
        assert sp_GestionClientes != null : "fx:id=\"sp_GestionClientes\" was not injected: check your FXML file 'PrestamoMenu.fxml'.";
        assert tab_prestamo != null : "fx:id=\"tab_prestamo\" was not injected: check your FXML file 'PrestamoMenu.fxml'.";

    }
}