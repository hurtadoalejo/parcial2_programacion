package co.edu.uniquindio.parcial2.prestamoapp.mapping.dto;

public record ClienteDto(
        String nombre,
        String apellido,
        String cedula,
        String email,
        String direccion
) {
}