package co.edu.uniquindio.parcial2.prestamoapp.controller;

import co.edu.uniquindio.parcial2.prestamoapp.factory.ModelFactory;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.ClienteDto;

public class BuscarClienteController {
    ModelFactory modelFactory;

    public BuscarClienteController() {
        modelFactory = ModelFactory.getInstancia();
    }

    public ClienteDto obtenerCliente(String cedula) {
        return modelFactory.obtenerCliente(cedula);
    }
}