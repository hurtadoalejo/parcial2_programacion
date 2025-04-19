package co.edu.uniquindio.parcial2.prestamoapp.viewController;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.uniquindio.parcial2.prestamoapp.controller.GestionObjetoController;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.ObjetoDto;
import co.edu.uniquindio.parcial2.prestamoapp.model.DisponibilidadObjeto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static co.edu.uniquindio.parcial2.prestamoapp.utils.PrestamoConstantes.*;

public class GestionObjetoViewController {

    GestionObjetoController gestionObjetoController;
    ObservableList<ObjetoDto> listaObjetos = FXCollections.observableArrayList();
    ObjetoDto objetoSeleccionado;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_Limpiar;

    @FXML
    private Button btn_Actualizar;

    @FXML
    private Button btn_Eliminar;

    @FXML
    private TableColumn<ObjetoDto, String> tc_IdObjeto;

    @FXML
    private TextField txt_Nombre;

    @FXML
    private Button btn_Agregar;

    @FXML
    private TableView<ObjetoDto> tableObjeto;

    @FXML
    private TableColumn<ObjetoDto, String> tc_Nombre;

    @FXML
    private TableColumn<ObjetoDto, String> tc_DisponibilidadObjeto;

    @FXML
    private TextField txt_IdObjeto;

    @FXML
    void onLimpiar() {
        limpiarSeleccion();
    }

    @FXML
    void onAgregarObjeto() {
        agregarObjeto();
    }

    @FXML
    void onActualizarObjeto() {
        actualizarObjeto();
    }

    @FXML
    void onEliminarObjeto() {
        eliminarObjeto();
    }

    @FXML
    void initialize() {
        gestionObjetoController = new GestionObjetoController();
        initView();
    }

    private void initView() {
        initDataBinding();
        obtenerObjetos();
        tableObjeto.getItems().clear();
        tableObjeto.setItems(listaObjetos);
        listenerSelection();
    }

    private void obtenerObjetos() {
        listaObjetos.addAll(gestionObjetoController.obtenerObjetos());
    }

    private void initDataBinding() {
        tc_Nombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        tc_IdObjeto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().idObjeto()));
        tc_DisponibilidadObjeto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().disponibilidadObjeto().name()));
    }

    private void listenerSelection() {
        tableObjeto.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            objetoSeleccionado = newSelection;
            mostrarInformacionObjeto(objetoSeleccionado);
        });
    }

    private boolean verificarCampos() {
        return !txt_Nombre.getText().isBlank() &&
                !txt_IdObjeto.getText().isBlank();
    }

    private void limpiarSeleccion() {
        tableObjeto.getSelectionModel().clearSelection();
        limpiarCampos();
    }

    private void limpiarCampos() {
        txt_Nombre.setText("");
        txt_IdObjeto.setText("");
    }

    private ObjetoDto crearObjetoDto() {
        return new ObjetoDto(
                txt_Nombre.getText(),
                txt_IdObjeto.getText(),
                DisponibilidadObjeto.DISPONIBLE);
    }

    private ObjetoDto crearObjetoDto(DisponibilidadObjeto disponibilidadObjeto) {
        return new ObjetoDto(
                txt_Nombre.getText(),
                txt_IdObjeto.getText(),
                disponibilidadObjeto);
    }

    private void agregarObjeto() {
        if(verificarCampos()){
            ObjetoDto objetoDto = crearObjetoDto();
            if(gestionObjetoController.agregarObjeto(objetoDto)){
                listaObjetos.addAll(objetoDto);
                limpiarCampos();
                mostrarMensaje(TITULO_OBJETO_AGREGADO, HEADER,
                        BODY_OBJETO_AGREGADO, Alert.AlertType.INFORMATION);
            }else{
                mostrarMensaje(TITULO_OBJETO_NO_AGREGADO, HEADER,
                        BODY_OBJETO_NO_AGREGADO,Alert.AlertType.ERROR);
            }
        }
        else {
            mostrarMensaje(TITULO_INCOMPLETO, HEADER, BODY_INCOMPLETO,Alert.AlertType.WARNING);
        }
    }

    private void actualizarObjeto() {
        if (objetoSeleccionado != null) {
            if(verificarCampos()){
                ObjetoDto objetoDto = crearObjetoDto(objetoSeleccionado.disponibilidadObjeto());
                if(gestionObjetoController.actualizarObjeto(objetoSeleccionado.idObjeto(), objetoDto)){
                    intercambiarInstanciasObjeto(objetoSeleccionado, objetoDto);
                    limpiarCampos();
                    mostrarMensaje(TITULO_OBJETO_ACTUALIZADO, HEADER,
                            BODY_OBJETO_ACTUALIZADO, Alert.AlertType.INFORMATION);
                }else{
                    mostrarMensaje(TITULO_OBJETO_NO_ACTUALIZADO, HEADER,
                            BODY_OBJETO_NO_ACTUALIZADO,Alert.AlertType.ERROR);
                }
            } else {
                mostrarMensaje(TITULO_INCOMPLETO, HEADER, BODY_INCOMPLETO,Alert.AlertType.WARNING);
            }
        }
        else {
            mostrarMensaje(TITULO_OBJETO_NO_SELECCIONADO, HEADER,
                    BODY_OBJETO_NO_SELECCIONADO, Alert.AlertType.WARNING);
        }
    }

    private void eliminarObjeto() {
        if (objetoSeleccionado != null) {
            if (mostrarMensajeConfirmacion("¿Está seguro de eliminar el objeto") &&
                    gestionObjetoController.eliminarObjeto(objetoSeleccionado.idObjeto())) {
                listaObjetos.remove(objetoSeleccionado);
                limpiarCampos();
                mostrarMensaje(TITULO_OBJETO_ELIMINADO, HEADER,
                        BODY_OBJETO_ELIMINADO, Alert.AlertType.INFORMATION);
            }
        }
        else {
            mostrarMensaje(TITULO_OBJETO_NO_SELECCIONADO, HEADER,
                    BODY_OBJETO_NO_SELECCIONADO, Alert.AlertType.WARNING);
        }
    }

    private void intercambiarInstanciasObjeto(ObjetoDto objetoSeleccionado, ObjetoDto objetoDto) {
        int indiceObjeto = listaObjetos.indexOf(objetoSeleccionado);
        listaObjetos.set(indiceObjeto, objetoDto);
    }

    private void mostrarInformacionObjeto(ObjetoDto objetoSeleccionado) {
        if(objetoSeleccionado != null){
            txt_Nombre.setText(objetoSeleccionado.nombre());
            txt_IdObjeto.setText(objetoSeleccionado.idObjeto());
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