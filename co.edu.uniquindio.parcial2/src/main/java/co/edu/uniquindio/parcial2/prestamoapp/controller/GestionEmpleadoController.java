package co.edu.uniquindio.parcial2.prestamoapp.controller;

import co.edu.uniquindio.parcial2.prestamoapp.factory.ModelFactory;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.EmpleadoDto;

import java.util.List;

public class GestionEmpleadoController {
    ModelFactory modelFactory;

    public GestionEmpleadoController(){
        modelFactory = ModelFactory.getInstancia();
    }

    public List<EmpleadoDto> obtenerEmpleados() {
        return modelFactory.obtenerEmpleados();
    }

    public boolean agregarEmpleado(EmpleadoDto empleadoDto) {
        return modelFactory.agregarEmpleado(empleadoDto);
    }

    public boolean eliminarEmpleado(String cedula) {
        return modelFactory.eliminarEmpleado(cedula);
    }

    public boolean actualizarEmpleado(String cedulaEmpleado, EmpleadoDto nuevoEmpleado) {
        return modelFactory.actualizarEmpleado(cedulaEmpleado, nuevoEmpleado);
    }
}