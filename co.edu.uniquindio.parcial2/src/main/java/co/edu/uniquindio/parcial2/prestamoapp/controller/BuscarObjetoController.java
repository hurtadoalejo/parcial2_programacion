package co.edu.uniquindio.parcial2.prestamoapp.controller;

import co.edu.uniquindio.parcial2.prestamoapp.factory.ModelFactory;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.ObjetoDto;

public class BuscarObjetoController {
    ModelFactory modelFactory;

    public BuscarObjetoController() {
        modelFactory = ModelFactory.getInstancia();
    }

    public ObjetoDto obtenerObjeto(String idObjeto) {
        return modelFactory.obtenerObjeto(idObjeto);
    }
}