package co.edu.uniquindio.parcial2.prestamoapp.controller;

import co.edu.uniquindio.parcial2.prestamoapp.factory.ModelFactory;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.EmpleadoDto;

import java.util.List;

public class ListaEmpleadosController {
    ModelFactory modelFactory;

    public ListaEmpleadosController() {
        modelFactory = ModelFactory.getInstancia();
    }

    public List<EmpleadoDto> obtenerEmpleados() {
        return modelFactory.obtenerEmpleados();
    }
}