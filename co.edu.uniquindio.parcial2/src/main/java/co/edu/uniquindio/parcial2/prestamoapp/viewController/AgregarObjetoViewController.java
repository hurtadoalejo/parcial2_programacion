package co.edu.uniquindio.parcial2.prestamoapp.viewController;

import java.io.IOException;
import java.net.URL;
import java.util.IllegalFormatCodePointException;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.uniquindio.parcial2.prestamoapp.controller.AgregarObjetoController;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.PrestamoDto;
import co.edu.uniquindio.parcial2.prestamoapp.model.EstadoPrestamo;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import static co.edu.uniquindio.parcial2.prestamoapp.utils.PrestamoConstantes.*;

public class AgregarObjetoViewController {

    private PrestamoDto prestamoDto;
    AgregarObjetoController agregarObjetoController;
    ObservableList<String> listaObjetos = FXCollections.observableArrayList();
    String objetoSeleccionado;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ap_principal;

    @FXML
    private Button btn_Eliminar;

    @FXML
    private TableColumn<String, String> tc_IdObjeto;

    @FXML
    private Button btn_Agregar;

    @FXML
    private TableView<String> tableObjeto;

    @FXML
    private Button btn_Menu;

    @FXML
    private ComboBox<String> cb_IdObjeto;

    @FXML
    void onAgregarObjeto() {
        agregarObjeto();
    }

    @FXML
    void onVolverMenu() {
        cambiarVista();
    }

    @FXML
    void onEliminarObjeto() {
        eliminarObjeto();
    }

    @FXML
    void initialize() {
        agregarObjetoController = new AgregarObjetoController();
        cb_IdObjeto.getItems().addAll(agregarObjetoController.obtenerObjetosDisponibles());
    }

    @FXML
    private void cambiarVista() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("/co/edu/uniquindio/parcial2/prestamoapp/GestionPrestamo.fxml"));
            AnchorPane gestionPrestamo = loader.load();

            SplitPane splitPane = (SplitPane) ap_principal.getParent().getParent();
            splitPane.getItems().setAll(gestionPrestamo);

        } catch (IOException e) {
            System.err.println("Error al cambiar la vista: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setPrestamoDto(PrestamoDto prestamoDto) {
        this.prestamoDto = prestamoDto;
        initView();
    }

    private void initView() {
        listaObjetos.addAll(prestamoDto.listaObjetos());
        tableObjeto.getItems().clear();
        tableObjeto.setItems(listaObjetos);
        initDataBinding();
        listenerSelection();
    }

    private void agregarObjeto() {
        if (prestamoDto.estadoPrestamo() == EstadoPrestamo.ENTREGADO) {
            mostrarMensaje(TITULO_PRESTAMO_NO_MODIFICAR, HEADER,
                    BODY_PRESTAMO_NO_MODIFICAR, Alert.AlertType.WARNING);
            return;
        }
        if (verificarCampos()) {
            if (agregarObjetoController.agregarObjetoPrestamo(prestamoDto.numeroPrestamo(),
                    cb_IdObjeto.getSelectionModel().getSelectedItem())) {
                String idObjeto = cb_IdObjeto.getSelectionModel().getSelectedItem();
                prestamoDto.listaObjetos().add(idObjeto);
                listaObjetos.add(idObjeto);
                limpiarSeleccion();
                cb_IdObjeto.getItems().clear();
                cb_IdObjeto.getItems().addAll(agregarObjetoController.obtenerObjetosDisponibles());
                mostrarMensaje(TITULO_OBJETO_AGREGADO, HEADER,
                        BODY_OBJETO_AGREGADO, Alert.AlertType.INFORMATION);
            }
        }
        else {
            mostrarMensaje(TITULO_INCOMPLETO, HEADER,
                    BODY_INCOMPLETO, Alert.AlertType.WARNING);
        }
    }

    private void eliminarObjeto() {
        if (prestamoDto.estadoPrestamo() == EstadoPrestamo.ENTREGADO) {
            mostrarMensaje(TITULO_PRESTAMO_NO_MODIFICAR, HEADER,
                    BODY_PRESTAMO_NO_MODIFICAR, Alert.AlertType.WARNING);
            return;
        }
        if (objetoSeleccionado != null) {
            if (mostrarMensajeConfirmacion("¿Está seguro de eliminar el objeto del prestamo?") &&
                    agregarObjetoController.eliminarObjetoPrestamo(prestamoDto.numeroPrestamo(), objetoSeleccionado)) {
                prestamoDto.listaObjetos().remove(objetoSeleccionado);
                listaObjetos.remove(objetoSeleccionado);
                limpiarSeleccion();
                cb_IdObjeto.getItems().clear();
                cb_IdObjeto.getItems().addAll(agregarObjetoController.obtenerObjetosDisponibles());
                mostrarMensaje(TITULO_OBJETO_DEVUELTO, HEADER,
                        BODY_OBJETO_DEVUELTO, Alert.AlertType.INFORMATION);
            }
        }
        else {
            mostrarMensaje(TITULO_OBJETO_NO_SELECCIONADO, HEADER,
                    BODY_OBJETO_NO_SELECCIONADO, Alert.AlertType.WARNING);
        }
    }

    public boolean verificarCampos() {
        return !cb_IdObjeto.getSelectionModel().isEmpty();
    }

    public void limpiarSeleccion() {
        tableObjeto.getSelectionModel().clearSelection();
        cb_IdObjeto.getSelectionModel().clearSelection();
    }

    private void initDataBinding() {
        tc_IdObjeto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
    }

    private void listenerSelection() {
        tableObjeto.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            objetoSeleccionado = newSelection;
        });
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }
}