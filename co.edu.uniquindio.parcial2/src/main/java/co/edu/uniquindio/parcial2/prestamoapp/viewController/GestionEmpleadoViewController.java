package co.edu.uniquindio.parcial2.prestamoapp.viewController;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.uniquindio.parcial2.prestamoapp.controller.GestionEmpleadoController;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.ClienteDto;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.EmpleadoDto;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static co.edu.uniquindio.parcial2.prestamoapp.utils.PrestamoConstantes.*;

public class GestionEmpleadoViewController {

    GestionEmpleadoController gestionEmpleadoController;
    ObservableList<EmpleadoDto> listaEmpleados = FXCollections.observableArrayList();
    EmpleadoDto empleadoSeleccionado;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<EmpleadoDto, String> tc_Cedula;

    @FXML
    private Button btn_Eliminar;

    @FXML
    private TextField txt_Nombre;

    @FXML
    private Button btn_Agregar;

    @FXML
    private TableColumn<EmpleadoDto, Integer> tc_Edad;

    @FXML
    private TextField txt_Apellido;

    @FXML
    private TextField txt_Edad;

    @FXML
    private TextField txt_Cedula;

    @FXML
    private Button btn_Limpiar;

    @FXML
    private Button btn_Actualizar;

    @FXML
    private TableColumn<EmpleadoDto, String> tc_Nombre;

    @FXML
    private TableView<EmpleadoDto> tableEmpleado;

    @FXML
    private TableColumn<EmpleadoDto, String> tc_Apellido;

    @FXML
    void onLimpiar() {
        limpiarSeleccion();
    }

    @FXML
    void onAgregarEmpleado() {
        agregarEmpleado();
    }

    @FXML
    void onActualizarEmpleado() {
        actualizarEmpleado();
    }

    @FXML
    void onEliminarEmpleado() {
        eliminarEmpleado();
    }

    @FXML
    void initialize() {
        gestionEmpleadoController = new GestionEmpleadoController();
        initView();
    }

    private void initView() {
        initDataBinding();
        obtenerEmpleados();
        tableEmpleado.getItems().clear();
        tableEmpleado.setItems(listaEmpleados);
        listenerSelection();
    }

    private void obtenerEmpleados() {
        listaEmpleados.addAll(gestionEmpleadoController.obtenerEmpleados());
    }

    private void initDataBinding() {
        tc_Nombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        tc_Apellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().apellido()));
        tc_Cedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().cedula()));
        tc_Edad.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().edad()).asObject());
    }

    private void listenerSelection() {
        tableEmpleado.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            empleadoSeleccionado = newSelection;
            mostrarInformacionEmpleado(empleadoSeleccionado);
        });
    }

    private boolean verificarCampos() {
        return !txt_Nombre.getText().isBlank() &&
                !txt_Cedula.getText().isBlank() &&
                !txt_Apellido.getText().isBlank() &&
                isInteger(txt_Edad.getText());
    }

    private void limpiarSeleccion() {
        tableEmpleado.getSelectionModel().clearSelection();
        limpiarCampos();
    }

    private void limpiarCampos() {
        txt_Nombre.setText("");
        txt_Apellido.setText("");
        txt_Cedula.setText("");
        txt_Edad.setText("");
    }

    private EmpleadoDto crearEmpleadoDto() {
        return new EmpleadoDto(
                txt_Nombre.getText(),
                txt_Apellido.getText(),
                txt_Cedula.getText(),
                Integer.parseInt(txt_Edad.getText()));
    }

    private void agregarEmpleado() {
        if (verificarCampos()) {
            EmpleadoDto empleadoDto = crearEmpleadoDto();
            if (gestionEmpleadoController.agregarEmpleado(empleadoDto)) {
                listaEmpleados.addAll(empleadoDto);
                limpiarCampos();
                mostrarMensaje(TITULO_EMPLEADO_AGREGADO, HEADER,
                        BODY_EMPLEADO_AGREGADO, Alert.AlertType.INFORMATION);
            }
            else {
                mostrarMensaje(TITULO_EMPLEADO_NO_AGREGADO, HEADER,
                        BODY_EMPLEADO_NO_AGREGADO, Alert.AlertType.ERROR);
            }
        }
        else {
            mostrarMensaje(TITULO_INCOMPLETO, HEADER, BODY_INCOMPLETO,Alert.AlertType.WARNING);
        }
    }

    private void actualizarEmpleado() {
        if (empleadoSeleccionado != null) {
            if (verificarCampos()) {
                EmpleadoDto empleadoDto = crearEmpleadoDto();
                if (gestionEmpleadoController.actualizarEmpleado(empleadoSeleccionado.cedula(), empleadoDto)) {
                    intercambiarInstanciasEmpleado(empleadoSeleccionado, empleadoDto);
                    limpiarCampos();
                    mostrarMensaje(TITULO_EMPLEADO_ACTUALIZADO, HEADER,
                            BODY_EMPLEADO_ACTUALIZADO, Alert.AlertType.INFORMATION);
                }
                else {
                    mostrarMensaje(TITULO_EMPLEADO_NO_ACTUALIZADO, HEADER,
                            BODY_EMPLEADO_NO_ACTUALIZADO, Alert.AlertType.ERROR);
                }
            }
            else {
                mostrarMensaje(TITULO_INCOMPLETO, HEADER, BODY_INCOMPLETO,Alert.AlertType.WARNING);
            }
        }
        else {
            mostrarMensaje(TITULO_EMPLEADO_NO_SELECCIONADO, HEADER,
                    BODY_EMPLEADO_NO_SELECCIONADO, Alert.AlertType.WARNING);
        }
    }

    private void eliminarEmpleado() {
        if (empleadoSeleccionado != null) {
            if (mostrarMensajeConfirmacion("¿Está seguro de eliminar el empleado") &&
                    gestionEmpleadoController.eliminarEmpleado(empleadoSeleccionado.cedula())) {
                listaEmpleados.remove(empleadoSeleccionado);
                limpiarCampos();
                mostrarMensaje(TITULO_EMPLEADO_ELIMINADO, HEADER,
                        BODY_EMPLEADO_ELIMINADO,Alert.AlertType.INFORMATION);
            }
        }
        else {
            mostrarMensaje(TITULO_EMPLEADO_NO_SELECCIONADO, HEADER,
                    BODY_EMPLEADO_NO_SELECCIONADO, Alert.AlertType.WARNING);
        }
    }

    private void intercambiarInstanciasEmpleado(EmpleadoDto empleadoSeleccionado, EmpleadoDto empleadoDto) {
        int indiceEmpleado = listaEmpleados.indexOf(empleadoSeleccionado);
        listaEmpleados.set(indiceEmpleado, empleadoDto);
    }

    private void mostrarInformacionEmpleado(EmpleadoDto empleadoSeleccionado) {
        if(empleadoSeleccionado != null){
            txt_Nombre.setText(empleadoSeleccionado.nombre());
            txt_Apellido.setText(empleadoSeleccionado.apellido());
            txt_Cedula.setText(empleadoSeleccionado.cedula());
            txt_Edad.setText(String.valueOf(empleadoSeleccionado.edad()));
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

    public static boolean isInteger(String text){
        if (text == null || text.isEmpty()) {
            return false;
        }
        try {
            int numero = Integer.parseInt(text);
            return numero > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}