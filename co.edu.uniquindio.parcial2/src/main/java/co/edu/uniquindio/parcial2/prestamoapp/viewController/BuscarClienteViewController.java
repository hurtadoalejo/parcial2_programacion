package co.edu.uniquindio.parcial2.prestamoapp.viewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.parcial2.prestamoapp.controller.BuscarClienteController;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.ClienteDto;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import static co.edu.uniquindio.parcial2.prestamoapp.utils.PrestamoConstantes.*;

public class BuscarClienteViewController {

    BuscarClienteController buscarClienteController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lb_CantidadPrestamos;

    @FXML
    private Button btn_Limpiar;

    @FXML
    private Label lb_NombreCliente;

    @FXML
    private Label lb_ApellidoCliente;

    @FXML
    private TextField txt_IdCliente;

    @FXML
    private Label lb_EmailCliente;

    @FXML
    private Label lb_DireccionCliente;

    @FXML
    private Button btn_BuscarCliente;

    @FXML
    private Label lb_CedulaCliente;

    @FXML
    void onLimpiar() {
        limpiarCampos();
    }

    @FXML
    void onBuscarCliente() {
        buscarCliente();
    }

    @FXML
    void initialize() {
        buscarClienteController = new BuscarClienteController();
    }

    private void buscarCliente() {
        if (!txt_IdCliente.getText().isEmpty()) {
            if (isInteger(txt_IdCliente.getText())) {
                ClienteDto clienteDto = buscarClienteController.obtenerCliente(txt_IdCliente.getText());
                if (clienteDto != null) {
                    mostrarInformacionCliente(clienteDto);
                    mostrarMensaje(TITULO_CLIENTE_ENCONTRADO, HEADER,
                            BODY_CLIENTE_ENCONTRADO, Alert.AlertType.INFORMATION);
                } else {
                    mostrarMensaje(TITULO_CLIENTE_NO_ENCONTRADO, HEADER,
                            BODY_CLIENTE_NO_ENCONTRADO, Alert.AlertType.ERROR);
                }
            } else {
                mostrarMensaje(TITULO_INCORRECTO_CEDULA, HEADER,
                        BODY_INCORRECTO_CEDULA, Alert.AlertType.WARNING);
            }
        } else {
            mostrarMensaje(TITULO_INCOMPLETO, HEADER,
                    BODY_INCOMPLETO_ID, Alert.AlertType.WARNING);
        }
    }

    private void limpiarCampos() {
        txt_IdCliente.setText("");
        lb_CantidadPrestamos.setText("");
        lb_NombreCliente.setText("");
        lb_ApellidoCliente.setText("");
        lb_CedulaCliente.setText("");
        lb_EmailCliente.setText("");
        lb_DireccionCliente.setText("");
    }

    public void mostrarInformacionCliente(ClienteDto clienteDto) {
        lb_NombreCliente.setText(clienteDto.nombre());
        lb_ApellidoCliente.setText(clienteDto.apellido());
        lb_CedulaCliente.setText(clienteDto.cedula());
        lb_EmailCliente.setText(clienteDto.email());
        lb_DireccionCliente.setText(clienteDto.direccion());
        lb_CantidadPrestamos.setText(String.valueOf(clienteDto.cantidadPrestamos()));
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    private boolean isInteger(String texto) {
        if (texto == null || texto.isBlank()) {
            return false;
        }
        try {
            Integer.parseInt(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}