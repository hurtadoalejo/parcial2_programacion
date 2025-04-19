package co.edu.uniquindio.parcial2.prestamoapp.service;

import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.ClienteDto;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.parcial2.prestamoapp.model.Empleado;

import java.util.List;

public interface IModelFactoryService {
    List<ClienteDto> obtenerClientes();

    boolean agregarCliente(ClienteDto clienteDto);

    boolean actualizarCliente(String cedulaVieja, ClienteDto clienteDto);

    boolean eliminarCliente(String cedula);

    List<EmpleadoDto> obtenerEmpleados();

    boolean agregarEmpleado(EmpleadoDto empleado);

    boolean eliminarEmpleado(String cedula);

    boolean actualizarEmpleado(String cedulaEmpleado, EmpleadoDto nuevoEmpleado);
}