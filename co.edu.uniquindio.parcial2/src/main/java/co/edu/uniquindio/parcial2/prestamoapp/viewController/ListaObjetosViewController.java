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
import co.edu.uniquindio.parcial2.prestamoapp.model.Objeto;
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
    private TableColumn<ObjetoDto, String> tc_IdObjeto;

    @FXML
    private RadioButton rb_todos;

    @FXML
    private RadioButton rd_prestados;

    @FXML
    private TableView<ObjetoDto> tableObjeto;

    @FXML
    private TableColumn<ObjetoDto, String> tc_Nombre;

    @FXML
    private TableColumn<ObjetoDto, String> tc_DisponibilidadObjeto;

    @FXML
    private RadioButton rd_noPrestados;

    @FXML
    void initialize() {
        listaObjetosController = new ListaObjetosController();
        configurarGrupoRadioButtons();
        initView();
        rb_todos.setSelected(true);
        listenerTodos();
    }

    private void configurarGrupoRadioButtons() {
        ToggleGroup grupo = new ToggleGroup();
        rb_todos.setToggleGroup(grupo);
        rd_prestados.setToggleGroup(grupo);
        rd_noPrestados.setToggleGroup(grupo);
        rd_prestados.setOnAction(event -> listenerTodos());
        rb_todos.setOnAction(event -> listenerTodos());
        rd_noPrestados.setOnAction(event -> listenerTodos());
    }

    private void listenerTodos() {
        if (rb_todos.isSelected()) {
            listaFiltrada.setPredicate(objetoDto -> true);
        } else if (rd_prestados.isSelected()) {
            listaFiltrada.setPredicate(objetoDto -> objetoDto.disponibilidadObjeto() == DisponibilidadObjeto.PRESTADO);
        } else if (rd_noPrestados.isSelected()) {
            listaFiltrada.setPredicate(objetoDto -> objetoDto.disponibilidadObjeto() == DisponibilidadObjeto.DISPONIBLE);
        }
    }

    private void initView() {
        initDataBinding();
        obtenerObjetos();
        crearListaFiltrada();
    }

    private void crearListaFiltrada() {
        listaFiltrada = new FilteredList<>(listaObjetos, objetoDto -> false);
        tableObjeto.setItems(listaFiltrada);
    }

    private void obtenerObjetos() {
        listaObjetos.clear();
        listaObjetos.addAll(listaObjetosController.obtenerObjetos());
    }

    private void obtenerObjetosDisponibles() {
        listaObjetos.clear();
        listaObjetos.addAll(listaObjetosController.obtenerListaObjetosDisponibles());
    }

    private void obtenerObjetosNoDisponibles() {
        listaObjetos.clear();
        listaObjetos.addAll(listaObjetosController.obtenerListaObjetosNoDisponibles());
    }

    private void initDataBinding() {
        tc_Nombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        tc_IdObjeto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().idObjeto()));
        tc_DisponibilidadObjeto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().disponibilidadObjeto().name()));
        tc_cantidadPrestado.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().cantidadPrestado()).asObject());
    }
}