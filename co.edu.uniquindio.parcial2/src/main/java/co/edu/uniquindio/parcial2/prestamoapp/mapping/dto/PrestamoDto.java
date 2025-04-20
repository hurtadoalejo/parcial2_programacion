package co.edu.uniquindio.parcial2.prestamoapp.mapping.dto;

import co.edu.uniquindio.parcial2.prestamoapp.model.EstadoPrestamo;

import java.time.LocalDate;
import java.util.List;

public record PrestamoDto(
        String numeroPrestamo, String descripcion,
        LocalDate fechaPrestamo, LocalDate fechaEntrega,
        String cedulaEmpleado, String cedulaCliente, EstadoPrestamo estadoPrestamo,
        List<String> listaObjetos
) {
}
