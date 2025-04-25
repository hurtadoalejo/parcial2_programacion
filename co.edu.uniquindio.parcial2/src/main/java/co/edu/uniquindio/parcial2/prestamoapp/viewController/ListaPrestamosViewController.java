package co.edu.uniquindio.parcial2.prestamoapp.viewController;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import co.edu.uniquindio.parcial2.prestamoapp.controller.ListaPrestamosController;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.PrestamoDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListaPrestamosViewController {

    ListaPrestamosController listaPrestamosController;
    ObservableList<PrestamoDto> listaPrestamos = FXCollections.observableArrayList();
    private LocalDate fechaDesdeSeleccionada;
    private LocalDate fechaHastaSeleccionada;
    private LocalDate fechaExactaSeleccionada;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker dp_fechaFin;

    @FXML
    private TableColumn<?, ?> tc_NumeroPrestamo;

    @FXML
    private Button btn_Limpiar;

    @FXML
    private TableView<PrestamoDto> tablePrestamo;

    @FXML
    private DatePicker dp_fechaExacta;

    @FXML
    private TableColumn<PrestamoDto, String> tc_Descripcion;

    @FXML
    private TableColumn<PrestamoDto, String> tc_FechaPrestamo;

    @FXML
    private TableColumn<PrestamoDto, String> tc_FechaEntrega;

    @FXML
    private DatePicker dp_fechaInicio;

    @FXML
    private TableColumn<PrestamoDto, String> tc_EstadoPrestamo;

    @FXML
    private TableColumn<PrestamoDto, String> tc_Cliente;

    @FXML
    private TableColumn<PrestamoDto, String> tc_Empleado;

    @FXML
    void onLimpiar() {
        limpiarSeleccion();
    }

    @FXML
    void initialize() {
        listaPrestamosController = new ListaPrestamosController();
        initView();
    }

    private void limpiarSeleccion() {
        dp_fechaExacta.setValue(null);
        dp_fechaFin.setValue(null);
        dp_fechaExacta.setValue(null);
    }

    private void crearListaFiltrada(){
        FilteredList<PrestamoDto> listaFiltrada = new FilteredList<>(listaPrestamos, prestamoDto -> true);
        tablePrestamo.setItems(listaFiltrada);
        listenerFechaExacta(listaFiltrada);
        listenerFechaDesde(listaFiltrada);
        listenerFechaExacta(listaFiltrada);
    }

    private void listenerFechaDesde(FilteredList<PrestamoDto> listaFiltrada) {
        dp_fechaFin.valueProperty().addListener((obs, oldDate, newDate) -> {
            fechaDesdeSeleccionada = newDate;
            aplicarFiltro(listaFiltrada);
        });
    }

    private void listenerFechaHasta(FilteredList<PrestamoDto> listaFiltrada) {
        dp_fechaFin.valueProperty().addListener((obs, oldDate, newDate) -> {
            fechaHastaSeleccionada = newDate;
            aplicarFiltro(listaFiltrada);
        });
    }

    private void listenerFechaExacta(FilteredList<PrestamoDto> listaFiltrada) {
        dp_fechaExacta.valueProperty().addListener((obs, oldDate, newDate) -> {
            fechaExactaSeleccionada = newDate;
            aplicarFiltro(listaFiltrada);
        });
    }

    private void aplicarFiltro(FilteredList<PrestamoDto> listaFiltrada) {
        listaFiltrada.setPredicate(prestamoDto -> {

            boolean coincideFechaExacta = (fechaExactaSeleccionada == null || prestamoDto.fechaPrestamo().isEqual(fechaExactaSeleccionada));

            boolean coincideFechaDesde = (fechaDesdeSeleccionada == null ||
                    !prestamoDto.fechaPrestamo().isBefore(fechaDesdeSeleccionada));

            boolean coincideFechaHasta = (fechaHastaSeleccionada == null ||
                    !prestamoDto.fechaPrestamo().isAfter(fechaHastaSeleccionada));

            return coincideFechaExacta && coincideFechaDesde && coincideFechaHasta;
        });
    }

    private void initView() {
        initDataBinding();
        obtenerPrestamos();
        tablePrestamo.getItems().clear();
        tablePrestamo.setItems(listaPrestamos);
    }

    private void obtenerPrestamos() {
        listaPrestamos.addAll(listaPrestamosController.obtenerPrestamos());
    }

    private void initDataBinding() {
        tc_Descripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().descripcion()));
        tc_FechaPrestamo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().fechaPrestamo().toString()));
        tc_FechaEntrega.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().fechaEntrega() != null ? cellData.getValue().fechaEntrega().toString() : ""));
        tc_Empleado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().cedulaEmpleado()));
        tc_Cliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().cedulaCliente()));
        tc_EstadoPrestamo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().estadoPrestamo().name()));
    }
}