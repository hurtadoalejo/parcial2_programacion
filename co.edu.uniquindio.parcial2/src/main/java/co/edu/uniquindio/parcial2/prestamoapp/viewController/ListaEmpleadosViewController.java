package co.edu.uniquindio.parcial2.prestamoapp.viewController;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.parcial2.prestamoapp.controller.ListaEmpleadosController;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.EmpleadoDto;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static co.edu.uniquindio.parcial2.prestamoapp.utils.PrestamoConstantes.*;

public class ListaEmpleadosViewController {

    ListaEmpleadosController listaEmpleadosController;
    ObservableList<EmpleadoDto> listaEmpleados = FXCollections.observableArrayList();
    private FilteredList<EmpleadoDto> listaFiltrada;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<EmpleadoDto, Integer> tc_Edad;

    @FXML
    private TableColumn<EmpleadoDto, Integer> tc_CantidadPrestamos;

    @FXML
    private Button btn_Filtrar;

    @FXML
    private TableColumn<EmpleadoDto, String> tc_Cedula;

    @FXML
    private Button btn_Limpiar;

    @FXML
    private TableColumn<EmpleadoDto, String> tc_Nombre;

    @FXML
    private TextField txt_Rango;

    @FXML
    private TableView<EmpleadoDto> tableEmpleado;

    @FXML
    private TableColumn<EmpleadoDto, String> tc_Apellido;

    @FXML
    void onFiltrar() {
        filtrarLista();
    }

    @FXML
    void onLimpiar() {
        limpiarSeleccion();
    }

    @FXML
    void initialize() {
        listaEmpleadosController = new ListaEmpleadosController();
        initView();
        crearListaFiltrada();
    }

    private void crearListaFiltrada() {
        listaFiltrada = new FilteredList<>(listaEmpleados, empleadoDto -> true);
        tableEmpleado.setItems(listaFiltrada);
        listaFiltrada.setPredicate(empleadoDto -> false);
    }

    private void initView() {
        initDataBinding();
        obtenerEmpleados();
        tableEmpleado.getItems().clear();
        tableEmpleado.setItems(listaEmpleados);
    }

    private void obtenerEmpleados() {
        List<EmpleadoDto> listaEmpleadosModel = listaEmpleadosController.obtenerEmpleados();
        listaEmpleados.addAll(listaEmpleadosModel);
    }

    private void initDataBinding() {
        tc_Nombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        tc_Apellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().apellido()));
        tc_Cedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().cedula()));
        tc_Edad.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().edad()).asObject());
        tc_CantidadPrestamos.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().cantidadPrestamos()).asObject());
    }

    private void limpiarSeleccion() {
        txt_Rango.setText("");
        listaFiltrada.setPredicate(objeto -> false);
        tableEmpleado.getSelectionModel().clearSelection();
    }

    private void filtrarLista() {
        if (verificarCampos()) {
            configurarListaFiltrada();
        } else {
            mostrarMensaje(TITULO_INCORRECTO_FILTRAR, HEADER,
                    BODY_INCORRECTO_FILTRAR, Alert.AlertType.WARNING);
        }
    }

    private boolean verificarCampos() {
        if (!txt_Rango.getText().isEmpty()) {
            return true;
        } else {
            return isInteger(txt_Rango.getText());
        }
    }

    private void configurarListaFiltrada() {
        int numeroPrestamos = Integer.parseInt(txt_Rango.getText());
        listaFiltrada.setPredicate(empleadoDto -> empleadoDto.cantidadPrestamos() == numeroPrestamos);
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

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }
}