package co.edu.uniquindio.parcial2.prestamoapp.mapping.dto;

import co.edu.uniquindio.parcial2.prestamoapp.model.EstadoPrestamo;

import java.time.LocalDate;

public record PrestamoDto(
        String numeroPrestamo, String descripcion,
        LocalDate fechaPrestamo, LocalDate fechaEntrega,
        String cedulaEmpleado, String cedulaCliente, EstadoPrestamo estadoPrestamo
) {
}
