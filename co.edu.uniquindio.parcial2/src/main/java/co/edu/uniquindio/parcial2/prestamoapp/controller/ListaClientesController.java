package co.edu.uniquindio.parcial2.prestamoapp.controller;

import co.edu.uniquindio.parcial2.prestamoapp.factory.ModelFactory;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.ClienteDto;

import java.util.List;

public class ListaClientesController {
    ModelFactory modelFactory;

    public ListaClientesController() {
        modelFactory = ModelFactory.getInstancia();
    }

    public List<ClienteDto> obtenerClientes() {
        return modelFactory.obtenerClientes();
    }
}