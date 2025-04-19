package co.edu.uniquindio.parcial2.prestamoapp.controller;

import co.edu.uniquindio.parcial2.prestamoapp.factory.ModelFactory;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.ClienteDto;

import java.util.List;

public class GestionClienteController {
    ModelFactory modelFactory;

    public GestionClienteController(){
        modelFactory = ModelFactory.getInstancia();
    }

    public List<ClienteDto> obtenerClientes() {
        return modelFactory.obtenerClientes();
    }

    public boolean agregarCliente(ClienteDto clienteDto) {
        return modelFactory.agregarCliente(clienteDto);
    }

    public boolean actualizarCliente(String cedulaClienteViejo, ClienteDto clienteDto) {
        return modelFactory.actualizarCliente(cedulaClienteViejo, clienteDto);
    };

    public boolean eliminarCliente(String cedula) {
        return modelFactory.eliminarCliente(cedula);
    }
}