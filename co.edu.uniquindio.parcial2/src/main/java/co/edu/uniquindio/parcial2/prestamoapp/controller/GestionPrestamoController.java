package co.edu.uniquindio.parcial2.prestamoapp.controller;

import co.edu.uniquindio.parcial2.prestamoapp.factory.ModelFactory;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.PrestamoDto;

import java.time.LocalDate;
import java.util.List;

public class GestionPrestamoController {
    ModelFactory modelFactory;

    public GestionPrestamoController() {
        modelFactory = ModelFactory.getInstancia();
    }

    public List<PrestamoDto> obtenerPrestamos() {
        return modelFactory.obtenerPrestamos();
    }

    public boolean agregarPrestamo(PrestamoDto prestamoDto) {
        return modelFactory.agregarPrestamo(prestamoDto);
    }

    public boolean entregarPrestamo(String numeroPrestamo, LocalDate fechaEntrega) {
        return modelFactory.entregarPrestamo(numeroPrestamo, fechaEntrega);
    }

    public boolean eliminarPrestamo(String numeroPrestamo) {
        return modelFactory.eliminarPrestamo(numeroPrestamo);
    }

    public boolean actualizarPrestamo(String numeroPrestamo, PrestamoDto nuevoPrestamo) {
        return modelFactory.actualizarPrestamo(numeroPrestamo, nuevoPrestamo);
    }

    public List<String> obtenerCedulasClientes() {
        return modelFactory.obtenerCedulasClientes();
    }

    public List<String> obtenerCedulasEmpleados() {
        return modelFactory.obtenerCedulasEmpleados();
    }
}