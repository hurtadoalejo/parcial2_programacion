package co.edu.uniquindio.parcial2.prestamoapp.service;

import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.ClienteDto;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.ObjetoDto;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.PrestamoDto;
import co.edu.uniquindio.parcial2.prestamoapp.model.Cliente;
import co.edu.uniquindio.parcial2.prestamoapp.model.Empleado;

import java.util.List;

public interface IModelFactoryService {
    List<ClienteDto> obtenerClientes();
    Cliente obtenerCliente(String cedula);
    List<String> obtenerCedulasClientes();
    boolean agregarCliente(ClienteDto clienteDto);
    boolean actualizarCliente(String cedulaVieja, ClienteDto clienteDto);
    boolean eliminarCliente(String cedula);

    List<EmpleadoDto> obtenerEmpleados();
    Empleado obtenerEmpleado(String cedula);
    List<String> obtenerCedulasEmpleados();
    boolean agregarEmpleado(EmpleadoDto empleado);
    boolean eliminarEmpleado(String cedula);
    boolean actualizarEmpleado(String cedulaEmpleado, EmpleadoDto nuevoEmpleado);

    List<ObjetoDto> obtenerObjetos();
    List<String> obtenerObjetosDisponibles();
    boolean agregarObjeto(ObjetoDto objetoDto);
    boolean eliminarObjeto(String idObjeto);
    boolean actualizarObjeto(String idObjeto, ObjetoDto nuevoObjeto);

    List<PrestamoDto> obtenerPrestamos();
    boolean agregarPrestamo(PrestamoDto prestamoDto);
    boolean eliminarPrestamo(String numeroPrestamo);
    boolean actualizarPrestamo(String numeroPrestamo, PrestamoDto nuevoPrestamo);
}