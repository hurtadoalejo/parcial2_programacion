package co.edu.uniquindio.parcial2.prestamoapp.controller;

import co.edu.uniquindio.parcial2.prestamoapp.factory.ModelFactory;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.ObjetoDto;

import java.util.List;

public class GestionObjetoController {
    ModelFactory modelFactory;

    public GestionObjetoController() {
        modelFactory = ModelFactory.getInstancia();
    }

    public List<ObjetoDto> obtenerObjetos() {
        return modelFactory.obtenerObjetos();
    }

    public boolean agregarObjeto(ObjetoDto objetoDto) {
        return modelFactory.agregarObjeto(objetoDto);
    }

    public boolean eliminarObjeto(String idObjeto) {
        return modelFactory.eliminarObjeto(idObjeto);
    }

    public boolean actualizarObjeto(String idObjeto, ObjetoDto nuevoObjeto) {
        return modelFactory.actualizarObjeto(idObjeto, nuevoObjeto);
    }
}