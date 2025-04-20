package co.edu.uniquindio.parcial2.prestamoapp.controller;

import co.edu.uniquindio.parcial2.prestamoapp.factory.ModelFactory;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.ObjetoDto;

import java.util.List;

public class AgregarObjetoController {
    ModelFactory modelFactory;

    public AgregarObjetoController() {
        modelFactory = ModelFactory.getInstancia();
    }

    public List<String> obtenerObjetosDisponibles() {
        return modelFactory.obtenerObjetosDisponibles();
    }

    public boolean agregarObjetoPrestamo(String numeroPrestamo, String idObjeto) {
        return modelFactory.agregarObjetoPrestamo(numeroPrestamo, idObjeto);
    }

    public boolean eliminarObjetoPrestamo(String numeroPrestamo, String idObjeto) {
        return modelFactory.eliminarObjetoPrestamo(numeroPrestamo, idObjeto);
    }
}