package co.edu.uniquindio.parcial2.prestamoapp.viewController;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.parcial2.prestamoapp.controller.ListaObjetosController;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.ObjetoDto;
import co.edu.uniquindio.parcial2.prestamoapp.model.DisponibilidadObjeto;
import com.sun.net.httpserver.Headers;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static co.edu.uniquindio.parcial2.prestamoapp.utils.PrestamoConstantes.*;

public class ListaObjetosViewController {

    ListaObjetosController listaObjetosController;
    ObservableList<ObjetoDto> listaObjetos = FXCollections.observableArrayList();
    private FilteredList<ObjetoDto> listaFiltrada;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<ObjetoDto, Integer> tc_cantidadPrestado;

    @FXML
    private Button btn_Filtrar;

    @FXML
    private Button btn_Limpiar;

    @FXML
    private TableColumn<ObjetoDto, String> tc_IdObjeto;

    @FXML
    private TableView<ObjetoDto> tableObjeto;

    @FXML
    private TableColumn<ObjetoDto, String> tc_Nombre;

    @FXML
    private TextField txt_Rango;

    @FXML
    private TableColumn<ObjetoDto, String> tc_DisponibilidadObjeto;

    @FXML
    private ComboBox<DisponibilidadObjeto> cb_tipoDisponibilidad;

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
        listaObjetosController = new ListaObjetosController();
        initView();
        crearListaFiltrada();
        cb_tipoDisponibilidad.getItems().addAll(DisponibilidadObjeto.values());
    }

    private void crearListaFiltrada() {
        listaFiltrada = new FilteredList<>(listaObjetos, p -> true);
        tableObjeto.setItems(listaFiltrada);
    }

    private void initView() {
        initDataBinding();
        obtenerObjetos();
        tableObjeto.getItems().clear();
        tableObjeto.setItems(listaObjetos);
    }

    private void obtenerObjetos() {
        List<ObjetoDto> listaObjetosModel = listaObjetosController.obtenerObjetos();
        listaObjetosModel.sort((o1, o2) -> Integer.compare(o2.cantidadPrestado(), o1.cantidadPrestado()));
        listaObjetos.addAll(listaObjetosModel);
    }

    private void initDataBinding() {
        tc_Nombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        tc_IdObjeto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().idObjeto()));
        tc_DisponibilidadObjeto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().disponibilidadObjeto().name()));
        tc_cantidadPrestado.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().cantidadPrestado()).asObject());
    }

    private void limpiarSeleccion() {
        txt_Rango.setText("");
        cb_tipoDisponibilidad.getSelectionModel().clearSelection();
        listaFiltrada.setPredicate(objeto -> true);
        tableObjeto.getSelectionModel().clearSelection();
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
        Integer minPrestados = obtenerMinPrestados();
        DisponibilidadObjeto tipoDisponibilidad = cb_tipoDisponibilidad.getSelectionModel().getSelectedItem();

        listaFiltrada.setPredicate(objeto -> {

            boolean cumpleDisponibilidad = (tipoDisponibilidad == null ||
                    objeto.disponibilidadObjeto() == tipoDisponibilidad);

            boolean cumplePrestamos = (minPrestados == null || objeto.cantidadPrestado() > minPrestados);

            return cumpleDisponibilidad && cumplePrestamos;
        });
    }

    private Integer obtenerMinPrestados() {
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