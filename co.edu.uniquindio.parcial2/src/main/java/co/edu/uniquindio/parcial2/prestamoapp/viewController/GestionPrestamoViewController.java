package co.edu.uniquindio.parcial2.prestamoapp.viewController;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.uniquindio.parcial2.prestamoapp.controller.GestionPrestamoController;
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

public class GestionPrestamoViewController {

    GestionPrestamoController gestionPrestamoController;
    ObservableList<PrestamoDto> listaPrestamos = FXCollections.observableArrayList();
    PrestamoDto prestamoSeleccionado;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_Entregar;

    @FXML
    private AnchorPane ap_principal;

    @FXML
    private TableColumn<PrestamoDto, String> tc_NumeroPrestamo;

    @FXML
    private Button btn_Eliminar;

    @FXML
    private TableColumn<PrestamoDto, String> tc_FechaPrestamo;

    @FXML
    private Button btn_Agregar;

    @FXML
    private ComboBox<String> cb_EmpleadoAsociado;

    @FXML
    private TableColumn<PrestamoDto, String> tc_EstadoPrestamo;

    @FXML
    private DatePicker datePicker_FechaEntrega;

    @FXML
    private TextField txt_NumeroPrestamo;

    @FXML
    private Button btn_Limpiar;

    @FXML
    private Button btn_Actualizar;

    @FXML
    private Button btn_AgregarObjetos;

    @FXML
    private TableView<PrestamoDto> tablePrestamo;

    @FXML
    private TableColumn<PrestamoDto, String> tc_Descripcion;

    @FXML
    private ComboBox<String> cb_ClienteAsociado;

    @FXML
    private TableColumn<PrestamoDto, String> tc_FechaEntrega;

    @FXML
    private DatePicker datePicker_FechaPrestamo;

    @FXML
    private TextField txt_Descripcion;

    @FXML
    private TableColumn<PrestamoDto, String> tc_Cliente;

    @FXML
    private TableColumn<PrestamoDto, String> tc_Empleado;

    @FXML
    void onLimpiar() {
        limpiarSeleccion();
    }

    @FXML
    void onAgregarPrestamo() {
        agregarPrestamo();
    }

    @FXML
    void onActualizarPrestamo() {
        actualizarPrestamo();
    }

    @FXML
    void onEntregarPrestamo() {
        entregarPrestamo();
    }

    @FXML
    void onEliminarPrestamo() {
        eliminarPrestamo();
    }

    @FXML
    void onAgregarObjetos() {
        cambiarVista();
    }

    @FXML
    private void cambiarVista() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("/co/edu/uniquindio/parcial2/prestamoapp/AgregarObjeto.fxml"));
            AnchorPane agregarObjeto = loader.load();
            AgregarObjetoViewController viewController = loader.getController();
            viewController.setPrestamoDto(prestamoSeleccionado);
            SplitPane splitPane = (SplitPane) ap_principal.getParent().getParent();
            splitPane.getItems().setAll(agregarObjeto);

        } catch (IOException e) {
            System.err.println("Error al cambiar la vista: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        gestionPrestamoController = new GestionPrestamoController();
        cb_ClienteAsociado.getItems().addAll(gestionPrestamoController.obtenerCedulasClientes());
        cb_EmpleadoAsociado.getItems().addAll(gestionPrestamoController.obtenerCedulasEmpleados());
        initView();
        btn_AgregarObjetos.setVisible(false);
        datePicker_FechaEntrega.setDisable(true);
    }

    private void initView() {
        initDataBinding();
        obtenerPrestamos();
        tablePrestamo.getItems().clear();
        tablePrestamo.setItems(listaPrestamos);
        listenerSelection();
    }

    private void obtenerPrestamos() {
        listaPrestamos.addAll(gestionPrestamoController.obtenerPrestamos());
    }

    private void initDataBinding() {
        tc_NumeroPrestamo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().numeroPrestamo()));
        tc_Descripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().descripcion()));
        tc_FechaPrestamo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().fechaPrestamo().toString()));
        tc_FechaEntrega.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().fechaEntrega() != null ? cellData.getValue().fechaEntrega().toString() : ""));
        tc_Empleado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().cedulaEmpleado()));
        tc_Cliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().cedulaCliente()));
        tc_EstadoPrestamo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().estadoPrestamo().name()));
    }

    private void listenerSelection() {
        tablePrestamo.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            prestamoSeleccionado = newSelection;
            mostrarInformacionPrestamo(prestamoSeleccionado);
        });
    }

    private boolean verificarCampos() {
        return !txt_NumeroPrestamo.getText().isBlank() &&
                !txt_Descripcion.getText().isBlank() &&
                !cb_ClienteAsociado.getSelectionModel().isEmpty() &&
                !cb_EmpleadoAsociado.getSelectionModel().isEmpty() &&
                datePicker_FechaPrestamo.getValue() != null;
    }

    private boolean verificarCamposCompletos() {
        return !txt_NumeroPrestamo.getText().isBlank() &&
                !txt_Descripcion.getText().isBlank() &&
                !cb_ClienteAsociado.getSelectionModel().isEmpty() &&
                !cb_EmpleadoAsociado.getSelectionModel().isEmpty() &&
                datePicker_FechaPrestamo.getValue() != null &&
                datePicker_FechaEntrega.getValue() != null;
    }

    private void limpiarSeleccion() {
        tablePrestamo.getSelectionModel().clearSelection();
        btn_AgregarObjetos.setVisible(false);
        datePicker_FechaEntrega.setDisable(true);
        limpiarCampos();
    }

    private void limpiarCampos() {
        txt_NumeroPrestamo.setText("");
        txt_Descripcion.setText("");
        cb_ClienteAsociado.getSelectionModel().clearSelection();
        cb_EmpleadoAsociado.getSelectionModel().clearSelection();
        datePicker_FechaPrestamo.setValue(null);
        datePicker_FechaEntrega.setValue(null);
        txt_NumeroPrestamo.setDisable(false);
        txt_Descripcion.setDisable(false);
        cb_ClienteAsociado.setDisable(false);
        cb_EmpleadoAsociado.setDisable(false);
        datePicker_FechaPrestamo.setDisable(false);
        datePicker_FechaEntrega.setDisable(true);
        btn_AgregarObjetos.setVisible(false);
    }

    private PrestamoDto crearPrestamoDto() {
        return new PrestamoDto(
                txt_NumeroPrestamo.getText(),
                txt_Descripcion.getText(),
                datePicker_FechaPrestamo.getValue(),
                null,
                cb_EmpleadoAsociado.getSelectionModel().getSelectedItem(),
                cb_ClienteAsociado.getSelectionModel().getSelectedItem(),
                EstadoPrestamo.PENDIENTE,
                new ArrayList<String>());
    }

    private PrestamoDto crearPrestamoDto(PrestamoDto prestamoDto) {
        return new PrestamoDto(
                txt_NumeroPrestamo.getText(),
                txt_Descripcion.getText(),
                datePicker_FechaPrestamo.getValue(),
                null,
                cb_EmpleadoAsociado.getSelectionModel().getSelectedItem(),
                cb_ClienteAsociado.getSelectionModel().getSelectedItem(),
                prestamoDto.estadoPrestamo(),
                prestamoDto.listaObjetos());
    }

    private PrestamoDto crearPrestamoDto(PrestamoDto prestamoDto, LocalDate fechaEntrega) {
        return new PrestamoDto(
                prestamoDto.numeroPrestamo(),
                prestamoDto.descripcion(),
                prestamoDto.fechaPrestamo(),
                fechaEntrega,
                prestamoDto.cedulaEmpleado(),
                prestamoDto.cedulaCliente(),
                EstadoPrestamo.ENTREGADO,
                prestamoDto.listaObjetos());
    }

    private void agregarPrestamo() {
        if (verificarCampos()) {
            PrestamoDto prestamoDto = crearPrestamoDto();
            if (gestionPrestamoController.agregarPrestamo(prestamoDto)) {
                listaPrestamos.addAll(prestamoDto);
                limpiarCampos();
                mostrarMensaje(TITULO_PRESTAMO_AGREGADO, HEADER,
                        BODY_PRESTAMO_AGREGADO, Alert.AlertType.INFORMATION);
            }
            else {
                mostrarMensaje(TITULO_PRESTAMO_NO_AGREGADO, HEADER,
                        BODY_PRESTAMO_NO_AGREGADO,Alert.AlertType.ERROR);
            }
        }
        else {
            mostrarMensaje(TITULO_INCOMPLETO, HEADER, BODY_INCOMPLETO,Alert.AlertType.WARNING);
        }
    }

    private void actualizarPrestamo() {
        if (prestamoSeleccionado != null) {
            if (prestamoSeleccionado.estadoPrestamo() == EstadoPrestamo.ENTREGADO) {
                mostrarMensaje(TITULO_PRESTAMO_NO_MODIFICAR, HEADER,
                        BODY_PRESTAMO_NO_MODIFICAR, Alert.AlertType.WARNING);
                return;
            }
            if (verificarCampos()) {
                PrestamoDto prestamoDto = crearPrestamoDto(prestamoSeleccionado);
                if (gestionPrestamoController.actualizarPrestamo(prestamoSeleccionado.numeroPrestamo(), prestamoDto)) {
                    intercambiarInstanciasPrestamo(prestamoSeleccionado, prestamoDto);
                    limpiarCampos();
                    mostrarMensaje(TITULO_PRESTAMO_ACTUALIZADO, HEADER,
                            BODY_PRESTAMO_ACTUALIZADO, Alert.AlertType.INFORMATION);
                } else {
                    mostrarMensaje(TITULO_PRESTAMO_NO_ACTUALIZADO, HEADER,
                            BODY_PRESTAMO_NO_ACTUALIZADO,Alert.AlertType.ERROR);
                }
            }
            else {
                mostrarMensaje(TITULO_INCOMPLETO, HEADER, BODY_INCOMPLETO,Alert.AlertType.WARNING);
            }
        }
        else {
            mostrarMensaje(TITULO_PRESTAMO_NO_SELECCIONADO, HEADER,
                    BODY_PRESTAMO_NO_SELECCIONADO, Alert.AlertType.WARNING);
        }
    }

    private void eliminarPrestamo() {
        if (prestamoSeleccionado != null) {
            if (prestamoSeleccionado.estadoPrestamo() == EstadoPrestamo.ENTREGADO) {
                mostrarMensaje(TITULO_PRESTAMO_NO_MODIFICAR, HEADER,
                        BODY_PRESTAMO_NO_MODIFICAR, Alert.AlertType.WARNING);
                return;
            }
            if (mostrarMensajeConfirmacion("¿Está seguro de eliminar el prestamo?") &&
                    gestionPrestamoController.eliminarPrestamo(prestamoSeleccionado.numeroPrestamo())) {
                listaPrestamos.remove(prestamoSeleccionado);
                limpiarCampos();
                mostrarMensaje(TITULO_PRESTAMO_ELIMINADO, HEADER,
                        BODY_PRESTAMO_ELIMINADO, Alert.AlertType.INFORMATION);
            } else {
                mostrarMensaje(TITULO_PRESTAMO_NO_MODIFICAR, HEADER,
                        BODY_PRESTAMO_NO_MODIFICAR, Alert.AlertType.ERROR);
            }
        }
        else {
            mostrarMensaje(TITULO_PRESTAMO_NO_SELECCIONADO, HEADER,
                    BODY_PRESTAMO_NO_SELECCIONADO, Alert.AlertType.WARNING);
        }
    }

    private void entregarPrestamo() {
        if (prestamoSeleccionado != null) {
            if (prestamoSeleccionado.estadoPrestamo() == EstadoPrestamo.ENTREGADO) {
                mostrarMensaje(TITULO_PRESTAMO_NO_MODIFICAR, HEADER,
                        BODY_PRESTAMO_NO_MODIFICAR, Alert.AlertType.WARNING);
                return;
            }
            if (verificarCamposCompletos()) {
                if (!prestamoSeleccionado.listaObjetos().isEmpty()) {
                    if (verificarFechasValidas()) {
                        if (gestionPrestamoController.entregarPrestamo(prestamoSeleccionado.numeroPrestamo(),
                                datePicker_FechaEntrega.getValue())) {
                            PrestamoDto prestamoDto = crearPrestamoDto(prestamoSeleccionado,
                                    datePicker_FechaEntrega.getValue());
                            intercambiarInstanciasPrestamo(prestamoSeleccionado, prestamoDto);
                            limpiarSeleccion();
                            mostrarMensaje(TITULO_PRESTAMO_ENTREGADO, HEADER,
                                    BODY_PRESTAMO_ENTREGADO, Alert.AlertType.INFORMATION);
                        }
                    } else {
                        mostrarMensaje(TITULO_PRESTAMO_FECHAS_INVALIDAS, HEADER,
                                BODY_PRESTAMO_FECHAS_INVALIDAS, Alert.AlertType.WARNING);
                    }
                } else {
                    mostrarMensaje(TITULO_PRESTAMO_SIN_OBJETOS, HEADER,
                            BODY_PRESTAMO_SIN_OBJETOS,Alert.AlertType.WARNING);
                }
            }
            else {
                mostrarMensaje(TITULO_INCOMPLETO, HEADER, BODY_INCOMPLETO,Alert.AlertType.WARNING);
            }
        }
        else {
            mostrarMensaje(TITULO_PRESTAMO_NO_SELECCIONADO, HEADER,
                    BODY_PRESTAMO_NO_SELECCIONADO, Alert.AlertType.WARNING);
        }
    }

    private boolean verificarFechasValidas() {
        return datePicker_FechaEntrega.getValue().isAfter(prestamoSeleccionado.fechaPrestamo());
    }

    private void intercambiarInstanciasPrestamo(PrestamoDto prestamoSeleccionado, PrestamoDto prestamoDto) {
        int indicePrestamo = listaPrestamos.indexOf(prestamoSeleccionado);
        listaPrestamos.set(indicePrestamo, prestamoDto);
    }

    private void mostrarInformacionPrestamo(PrestamoDto prestamoSeleccionado) {
        if(prestamoSeleccionado != null){
            txt_NumeroPrestamo.setText(prestamoSeleccionado.numeroPrestamo());
            txt_Descripcion.setText(prestamoSeleccionado.descripcion());
            cb_ClienteAsociado.getSelectionModel().select(prestamoSeleccionado.cedulaCliente());
            cb_EmpleadoAsociado.getSelectionModel().select(prestamoSeleccionado.cedulaEmpleado());
            datePicker_FechaPrestamo.setValue(prestamoSeleccionado.fechaPrestamo());
            datePicker_FechaEntrega.setValue(prestamoSeleccionado.fechaEntrega());
            btn_AgregarObjetos.setVisible(true);
            datePicker_FechaEntrega.setDisable(false);
            desabilitarBotonesPrestamo(prestamoSeleccionado);
        }
    }

    private void desabilitarBotonesPrestamo(PrestamoDto prestamoSeleccionado) {
        if (prestamoSeleccionado.estadoPrestamo() == EstadoPrestamo.PENDIENTE) {
            btn_Entregar.setDisable(false);
            datePicker_FechaEntrega.setDisable(false);
        }
        else if (prestamoSeleccionado.estadoPrestamo() == EstadoPrestamo.ENTREGADO) {
            txt_NumeroPrestamo.setDisable(true);
            txt_Descripcion.setDisable(true);
            cb_ClienteAsociado.setDisable(true);
            cb_EmpleadoAsociado.setDisable(true);
            datePicker_FechaPrestamo.setDisable(true);
            datePicker_FechaEntrega.setDisable(true);
        }
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