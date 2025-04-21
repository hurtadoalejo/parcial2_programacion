package co.edu.uniquindio.parcial2.prestamoapp.viewController;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.parcial2.prestamoapp.controller.ListaClientesController;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.ClienteDto;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static co.edu.uniquindio.parcial2.prestamoapp.utils.PrestamoConstantes.*;

public class ListaClientesViewController {

    ListaClientesController listaClientesController;
    ObservableList<ClienteDto> listaClientes = FXCollections.observableArrayList();
    private FilteredList<ClienteDto> listaFiltrada;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<ClienteDto, String> tc_Email;

    @FXML
    private TableColumn<ClienteDto, Integer> tc_CantidadPrestamos;

    @FXML
    private Button btn_Filtrar;

    @FXML
    private TableView<ClienteDto> tableCliente;

    @FXML
    private TableColumn<ClienteDto, String> tc_Cedula;

    @FXML
    private Button btn_Limpiar;

    @FXML
    private TableColumn<ClienteDto, String> tc_Nombre;

    @FXML
    private TextField txt_Rango;

    @FXML
    private TableColumn<ClienteDto, String> tc_Direccion;

    @FXML
    private TableColumn<ClienteDto, String> tc_Apellido;

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
        listaClientesController = new ListaClientesController();
        initView();
        crearListaFiltrada();
    }

    private void crearListaFiltrada() {
        listaFiltrada = new FilteredList<>(listaClientes, p -> true);
        tableCliente.setItems(listaFiltrada);
    }

    private void initView() {
        initDataBinding();
        obtenerClientes();
        tableCliente.getItems().clear();
        tableCliente.setItems(listaClientes);
    }

    private void obtenerClientes() {
        List<ClienteDto> listaClientesModel = listaClientesController.obtenerClientes();
        listaClientesModel.sort((o1, o2) -> Integer.compare(o2.cantidadPrestamos(), o1.cantidadPrestamos()));
        listaClientes.addAll(listaClientesModel);
    }

    private void initDataBinding() {
        tc_Nombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        tc_Apellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().apellido()));
        tc_Cedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().cedula()));
        tc_Email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().email()));
        tc_Direccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().direccion()));
        tc_CantidadPrestamos.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().cantidadPrestamos()).asObject());
    }

    private void limpiarSeleccion() {
        txt_Rango.setText("");
        listaFiltrada.setPredicate(objeto -> true);
        tableCliente.getSelectionModel().clearSelection();
    }

    private void filtrarLista() {
        if (verificarCampos()) {
            configurarListaFiltrada();
        }
        else {
            mostrarMensaje(TITULO_INCORRECTO_FILTRAR, HEADER,
                    BODY_INCORRECTO_FILTRAR, Alert.AlertType.WARNING);
        }
    }

    private boolean verificarCampos() {
        if (txt_Rango.getText().isEmpty()) {
            return true;
        }
        else {
            return isInteger(txt_Rango.getText());
        }
    }

    private void configurarListaFiltrada() {
        Integer minPrestamos = obtenerMinPrestamos();
        listaFiltrada.setPredicate(objeto -> {
            boolean cumplePrestamos;
            return cumplePrestamos = (minPrestamos == null || objeto.cantidadPrestamos() > minPrestamos);
        });
    }

    private Integer obtenerMinPrestamos() {
        String textoRango = txt_Rango.getText().trim();

        if (textoRango.isEmpty()) {
            return null;
        }

        return Integer.parseInt(textoRango);
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