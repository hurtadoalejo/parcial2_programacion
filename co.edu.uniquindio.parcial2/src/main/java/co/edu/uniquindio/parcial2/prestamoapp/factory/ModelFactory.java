package co.edu.uniquindio.parcial2.prestamoapp.factory;

import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.ClienteDto;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.ObjetoDto;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.PrestamoDto;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.mappers.PrestamoMappingImpl;
import co.edu.uniquindio.parcial2.prestamoapp.model.*;
import co.edu.uniquindio.parcial2.prestamoapp.service.IModelFactoryService;
import co.edu.uniquindio.parcial2.prestamoapp.service.IPrestamoMapping;
import co.edu.uniquindio.parcial2.prestamoapp.utils.DataUtil;

import java.time.LocalDate;
import java.util.List;

public class ModelFactory implements IModelFactoryService {
    private static ModelFactory modelFactory;
    private PrestamoObjeto prestamoObjeto;
    private IPrestamoMapping mapper;

    public static ModelFactory getInstancia() {
        if(modelFactory == null) {
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }

    private ModelFactory(){
        mapper = new PrestamoMappingImpl();
        prestamoObjeto = DataUtil.inicializarDatos();
    }

    @Override
    public List<ClienteDto> obtenerClientes() {
        return mapper.getClientesDto(prestamoObjeto.getListaClientes());
    }

    @Override
    public Cliente obtenerCliente(String cedula) {
        return prestamoObjeto.obtenerCliente(cedula);
    }

    @Override
    public List<String> obtenerCedulasClientes() {
        return prestamoObjeto.obtenerClientesCedulas();
    }

    @Override
    public boolean agregarCliente(ClienteDto clienteDto) {
        Cliente cliente = mapper.clienteDtoToCliente(clienteDto);
        return prestamoObjeto.crearCliente(cliente);
    }

    @Override
    public boolean actualizarCliente(String cedulaVieja, ClienteDto clienteDto) {
        Cliente cliente = mapper.clienteDtoToCliente(clienteDto);
        return prestamoObjeto.actualizarCliente(cedulaVieja, cliente);
    }

    @Override
    public boolean eliminarCliente(String cedula) {
        return prestamoObjeto.eliminarCliente(cedula);
    }

    @Override
    public List<EmpleadoDto> obtenerEmpleados() {
        return mapper.getEmpleadosDto(prestamoObjeto.getListaEmpleados());
    }

    @Override
    public Empleado obtenerEmpleado(String cedula) {
        return prestamoObjeto.obtenerEmpleado(cedula);
    }

    @Override
    public List<String> obtenerCedulasEmpleados() {
        return prestamoObjeto.obtenerEmpleadosCedulas();
    }

    @Override
    public boolean agregarEmpleado(EmpleadoDto empleadoDto) {
        Empleado empleado = mapper.empleadoDtoToEmpleado(empleadoDto);
        empleado.setOwnedByPrestamoUq(prestamoObjeto);
        return prestamoObjeto.agregarEmpleado(empleado);
    }

    @Override
    public boolean eliminarEmpleado(String cedula) {
        return prestamoObjeto.eliminarEmpleado(cedula);
    }

    @Override
    public boolean actualizarEmpleado(String cedulaEmpleado, EmpleadoDto nuevoEmpleadoDto) {
        Empleado empleado = mapper.empleadoDtoToEmpleado(nuevoEmpleadoDto);
        empleado.setOwnedByPrestamoUq(prestamoObjeto);
        return prestamoObjeto.actualizarEmpleado(cedulaEmpleado, empleado);
    }

    @Override
    public List<ObjetoDto> obtenerObjetos() {
        return mapper.getObjetosDto(prestamoObjeto.getListaObjetos());
    }

    @Override
    public List<String> obtenerObjetosDisponibles() {
        return prestamoObjeto.obtenerObjetosDisponibles();
    }

    @Override
    public boolean agregarObjeto(ObjetoDto objetoDto) {
        Objeto objeto = mapper.objetoDtoToObjeto(objetoDto);
        objeto.setOwnedByPrestamoUq(prestamoObjeto);
        return prestamoObjeto.agregarObjeto(objeto);
    }

    @Override
    public boolean eliminarObjeto(String idObjeto) {
        return prestamoObjeto.eliminarObjeto(idObjeto);
    }

    @Override
    public boolean actualizarObjeto(String idObjeto, ObjetoDto nuevoObjeto) {
        Objeto objeto = mapper.objetoDtoToObjeto(nuevoObjeto);
        objeto.setOwnedByPrestamoUq(prestamoObjeto);
        return prestamoObjeto.actualizarObjeto(idObjeto, objeto);
    }

    @Override
    public List<PrestamoDto> obtenerPrestamos() {
        return mapper.getPrestamosDto(prestamoObjeto.getListaPrestamos());
    }

    @Override
    public boolean agregarPrestamo(PrestamoDto prestamoDto) {
        Empleado empleado = obtenerEmpleado(prestamoDto.cedulaEmpleado());
        Cliente cliente = obtenerCliente(prestamoDto.cedulaCliente());
        Prestamo prestamo = mapper.prestamoDtoToPrestamo(prestamoDto, empleado, cliente);
        return prestamoObjeto.agregarPrestamo(prestamo);
    }

    @Override
    public boolean entregarPrestamo(String numeroPrestamo, LocalDate fechaEntrega) {
        return prestamoObjeto.entregarPrestamo(numeroPrestamo, fechaEntrega);
    }

    @Override
    public boolean eliminarPrestamo(String numeroPrestamo) {
        return prestamoObjeto.eliminarPrestamo(numeroPrestamo);
    }

    @Override
    public boolean actualizarPrestamo(String numeroPrestamo, PrestamoDto nuevoPrestamo) {
        Empleado empleado = obtenerEmpleado(nuevoPrestamo.cedulaEmpleado());
        Cliente cliente = obtenerCliente(nuevoPrestamo.cedulaCliente());
        Prestamo prestamo = mapper.prestamoDtoToPrestamo(nuevoPrestamo, empleado, cliente);
        return prestamoObjeto.actualizarPrestamo(numeroPrestamo, prestamo);
    }

    @Override
    public boolean agregarObjetoPrestamo(String numeroPrestamo, String idObjeto) {
        return prestamoObjeto.agregarObjetoPrestamo(numeroPrestamo, idObjeto);
    }

    @Override
    public boolean eliminarObjetoPrestamo(String numeroPrestamo, String idObjeto) {
        return prestamoObjeto.eliminarObjetoPrestamo(numeroPrestamo, idObjeto);
    }
}