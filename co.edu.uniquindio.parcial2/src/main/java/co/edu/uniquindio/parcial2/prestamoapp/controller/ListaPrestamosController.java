package co.edu.uniquindio.parcial2.prestamoapp.controller;

import co.edu.uniquindio.parcial2.prestamoapp.factory.ModelFactory;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.PrestamoDto;
import co.edu.uniquindio.parcial2.prestamoapp.model.Prestamo;

import java.util.List;

public class ListaPrestamosController {
    ModelFactory modelFactory;

    public ListaPrestamosController() {
        modelFactory = ModelFactory.getInstancia();
    }

    public List<PrestamoDto> obtenerPrestamos() {
        return modelFactory.obtenerPrestamos();
    }
}