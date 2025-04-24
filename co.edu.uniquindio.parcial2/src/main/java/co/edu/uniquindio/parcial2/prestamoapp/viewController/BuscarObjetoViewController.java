package co.edu.uniquindio.parcial2.prestamoapp.viewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.parcial2.prestamoapp.controller.BuscarObjetoController;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.ObjetoDto;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import static co.edu.uniquindio.parcial2.prestamoapp.utils.PrestamoConstantes.*;

public class BuscarObjetoViewController {

    BuscarObjetoController buscarObjetoController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lb_VecesPrestado;

    @FXML
    private Button btn_Limpiar;

    @FXML
    private Label lb_NombreObjeto;

    @FXML
    private Button btn_BuscarObjeto;

    @FXML
    private Label lb_IdObjeto;

    @FXML
    private Label lb_DisponibilidadObjeto;

    @FXML
    private TextField txt_IdObjeto;

    @FXML
    void onLimpiar() {
        limpiarCampos();
    }

    @FXML
    void onBuscarObjeto() {
        buscarObjeto();
    }

    @FXML
    void initialize() {
        buscarObjetoController = new BuscarObjetoController();
    }

    private void buscarObjeto() {
        if (!txt_IdObjeto.getText().isEmpty()) {
            if (isInteger(txt_IdObjeto.getText())) {
                ObjetoDto objetoDto = buscarObjetoController.obtenerObjeto(txt_IdObjeto.getText());
                if (objetoDto != null) {
                    mostrarInformacionObjeto(objetoDto);
                    mostrarMensaje(TITULO_OBJETO_ENCONTRADO, HEADER,
                            BODY_OBJETO_ENCONTRADO, Alert.AlertType.INFORMATION);
                } else {
                    mostrarMensaje(TITULO_OBJETO_NO_ENCONTRADO, HEADER,
                            BODY_OBJETO_NO_ENCONTRADO, Alert.AlertType.ERROR);
                }
            }
            else {
                mostrarMensaje(TITULO_INCORRECTO_ID, HEADER,
                        BODY_INCORRECTO_ID, Alert.AlertType.WARNING);
            }
        } else {
            mostrarMensaje(TITULO_INCOMPLETO, HEADER, BODY_INCOMPLETO_ID, Alert.AlertType.WARNING);
        }
    }

    private void limpiarCampos() {
        txt_IdObjeto.setText("");
        lb_VecesPrestado.setText("");
        lb_NombreObjeto.setText("");
        lb_IdObjeto.setText("");
        lb_DisponibilidadObjeto.setText("");
    }

    public void mostrarInformacionObjeto(ObjetoDto objetoDto) {
        lb_NombreObjeto.setText(objetoDto.nombre());
        lb_IdObjeto.setText(objetoDto.idObjeto());
        lb_DisponibilidadObjeto.setText(objetoDto.disponibilidadObjeto().name());
        lb_VecesPrestado.setText(String.valueOf(objetoDto.cantidadPrestado()));
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