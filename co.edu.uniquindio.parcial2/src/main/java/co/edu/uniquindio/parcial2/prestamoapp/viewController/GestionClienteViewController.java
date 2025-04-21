package co.edu.uniquindio.parcial2.prestamoapp.viewController;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.uniquindio.parcial2.prestamoapp.controller.GestionClienteController;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.ClienteDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static co.edu.uniquindio.parcial2.prestamoapp.utils.PrestamoConstantes.*;

public class GestionClienteViewController {

    GestionClienteController gestionClienteController;
    ObservableList<ClienteDto> listaClientes = FXCollections.observableArrayList();
    ClienteDto clienteSeleccionado;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<ClienteDto, String> tc_Email;

    @FXML
    private TableColumn<ClienteDto, String> tc_Cedula;

    @FXML
    private Button btn_Eliminar;

    @FXML
    private TextField txt_Nombre;

    @FXML
    private Button btn_Agregar;

    @FXML
    private TextField txt_Apellido;

    @FXML
    private TextField txt_Email;

    @FXML
    private TextField txt_Cedula;

    @FXML
    private TextField txt_Direccion;

    @FXML
    private TableView<ClienteDto> tableCliente;

    @FXML
    private Button btn_Limpiar;

    @FXML
    private Button btn_Actualizar;

    @FXML
    private TableColumn<ClienteDto, String> tc_Nombre;

    @FXML
    private TableColumn<ClienteDto, String> tc_Direccion;

    @FXML
    private TableColumn<ClienteDto, String> tc_Apellido;

    @FXML
    void onLimpiar() {
        limpiarSeleccion();
    }

    @FXML
    void onAgregarCliente() {
        agregarCliente();
    }

    @FXML
    void onActualizarCliente() {
        actualizarCliente();
    }

    @FXML
    void onEliminarCliente() {
        eliminarCliente();
    }

    @FXML
    void initialize() {
        gestionClienteController = new GestionClienteController();
        initView();
    }

    private void initView() {
        initDataBinding();
        obtenerClientes();
        tableCliente.getItems().clear();
        tableCliente.setItems(listaClientes);
        listenerSelection();
    }

    private void obtenerClientes() {
        listaClientes.addAll(gestionClienteController.obtenerClientes());
    }

    private void initDataBinding() {
        tc_Nombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        tc_Apellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().apellido()));
        tc_Cedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().cedula()));
        tc_Email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().email()));
        tc_Direccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().direccion()));
    }

    private void listenerSelection() {
        tableCliente.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            clienteSeleccionado = newSelection;
            mostrarInformacionCliente(clienteSeleccionado);
        });
    }

    private boolean verificarCampos() {
        return !txt_Nombre.getText().isBlank() &&
                !txt_Cedula.getText().isBlank() &&
                !txt_Direccion.getText().isBlank() &&
                !txt_Apellido.getText().isBlank() &&
                !txt_Email.getText().isBlank();
    }

    private void limpiarSeleccion() {
        tableCliente.getSelectionModel().clearSelection();
        limpiarCampos();
    }

    private void limpiarCampos() {
        txt_Nombre.setText("");
        txt_Apellido.setText("");
        txt_Cedula.setText("");
        txt_Email.setText("");
        txt_Direccion.setText("");
    }

    private ClienteDto crearClienteDto() {
        return new ClienteDto(
                txt_Nombre.getText(),
                txt_Apellido.getText(),
                txt_Cedula.getText(),
                txt_Email.getText(),
                txt_Direccion.getText(), 0);
    }

    private void agregarCliente() {
        if(verificarCampos()){
            ClienteDto clienteDto = crearClienteDto();
            if(gestionClienteController.agregarCliente(clienteDto)){
                listaClientes.addAll(clienteDto);
                limpiarCampos();
                mostrarMensaje(TITULO_CLIENTE_AGREGADO, HEADER, BODY_CLIENTE_AGREGADO, Alert.AlertType.INFORMATION);
            }else{
                mostrarMensaje(TITULO_CLIENTE_NO_AGREGADO, HEADER, BODY_CLIENTE_NO_AGREGADO,Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje(TITULO_INCOMPLETO, HEADER, BODY_INCOMPLETO,Alert.AlertType.WARNING);
        }
    }

    private void actualizarCliente() {
        if (clienteSeleccionado != null) {
            if(verificarCampos()){
                ClienteDto clienteDto = crearClienteDto();
                if(gestionClienteController.actualizarCliente(clienteSeleccionado.cedula(), clienteDto)){
                    intercambiarInstanciasCliente(clienteSeleccionado, clienteDto);
                    limpiarCampos();
                    mostrarMensaje(TITULO_CLIENTE_ACTUALIZADO, HEADER,
                            BODY_CLIENTE_ACTUALIZADO, Alert.AlertType.INFORMATION);
                }else{
                    mostrarMensaje(TITULO_CLIENTE_NO_ACTUALIZADO, HEADER,
                            BODY_CLIENTE_NO_ACTUALIZADO,Alert.AlertType.ERROR);
                }
            }else{
                mostrarMensaje(TITULO_INCOMPLETO, HEADER, BODY_INCOMPLETO,Alert.AlertType.WARNING);
            }
        }
        else {
            mostrarMensaje(TITULO_CLIENTE_NO_SELECCIONADO, HEADER,
                    BODY_CLIENTE_NO_SELECCIONADO, Alert.AlertType.WARNING);
        }
    }

    private void eliminarCliente() {
        if(clienteSeleccionado != null){
            if(mostrarMensajeConfirmacion("¿Está seguro de eliminar el cliente") &&
                    gestionClienteController.eliminarCliente(clienteSeleccionado.cedula())){
                listaClientes.remove(clienteSeleccionado);
                limpiarCampos();
                mostrarMensaje(TITULO_CLIENTE_ELIMINADO, HEADER, BODY_CLIENTE_ELIMINADO,Alert.AlertType.INFORMATION);
            }
        }
    }

    private void intercambiarInstanciasCliente(ClienteDto clienteSeleccionado, ClienteDto clienteDto) {
        int indiceCliente = listaClientes.indexOf(clienteSeleccionado);
        listaClientes.set(indiceCliente, clienteDto);
    }

    private void mostrarInformacionCliente(ClienteDto clienteSeleccionado) {
        if(clienteSeleccionado != null){
            txt_Nombre.setText(clienteSeleccionado.nombre());
            txt_Apellido.setText(clienteSeleccionado.apellido());
            txt_Cedula.setText(clienteSeleccionado.cedula());
            txt_Email.setText(clienteSeleccionado.email());
            txt_Direccion.setText(clienteSeleccionado.direccion());
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