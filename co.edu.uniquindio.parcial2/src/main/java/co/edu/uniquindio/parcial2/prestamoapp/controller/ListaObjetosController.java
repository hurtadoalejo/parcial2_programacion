package co.edu.uniquindio.parcial2.prestamoapp.controller;

import co.edu.uniquindio.parcial2.prestamoapp.factory.ModelFactory;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.ObjetoDto;

import java.util.List;

public class ListaObjetosController {
    ModelFactory modelFactory;

    public ListaObjetosController() {
        modelFactory = ModelFactory.getInstancia();
    }

    public List<ObjetoDto> obtenerObjetos() {
        return modelFactory.obtenerObjetos();
    }
}