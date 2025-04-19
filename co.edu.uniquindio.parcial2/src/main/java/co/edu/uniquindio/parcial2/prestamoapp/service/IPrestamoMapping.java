package co.edu.uniquindio.parcial2.prestamoapp.service;

import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.ClienteDto;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.ObjetoDto;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.PrestamoDto;
import co.edu.uniquindio.parcial2.prestamoapp.model.*;

import java.util.List;

public interface IPrestamoMapping {
    List<ClienteDto> getClientesDto(List<Cliente> listaClientes);
    ClienteDto clienteToClienteDto(Cliente cliente);
    Cliente clienteDtoToCliente(ClienteDto clienteDto);

    List<EmpleadoDto> getEmpleadosDto(List<Empleado> listaEmpleados);
    EmpleadoDto empleadoToEmpleadoDto(Empleado empleado);
    Empleado empleadoDtoToEmpleado(EmpleadoDto empleadoDto);

    List<ObjetoDto> getObjetosDto(List<Objeto> listaObjetos);
    ObjetoDto objetoToObjetoDto(Objeto objeto);
    Objeto objetoDtoToObjeto(ObjetoDto objetoDto);

    List<PrestamoDto> getPrestamosDto(List<Prestamo> listaPrestamos);
    PrestamoDto prestamoToPrestamoDto(Prestamo prestamo);
    Prestamo prestamoDtoToPrestamo(PrestamoDto prestamoDto, Empleado empleado, Cliente cliente);
}