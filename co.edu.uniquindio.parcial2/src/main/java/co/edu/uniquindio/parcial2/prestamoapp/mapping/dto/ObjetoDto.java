package co.edu.uniquindio.parcial2.prestamoapp.mapping.dto;

import co.edu.uniquindio.parcial2.prestamoapp.model.DisponibilidadObjeto;

public record ObjetoDto(
        String nombre, String idObjeto, DisponibilidadObjeto disponibilidadObjeto, int cantidadPrestado
) {
}
