package co.edu.uniquindio.parcial2.prestamoapp.factory;

import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.ClienteDto;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.mappers.PrestamoMappingImpl;
import co.edu.uniquindio.parcial2.prestamoapp.model.Cliente;
import co.edu.uniquindio.parcial2.prestamoapp.model.Empleado;
import co.edu.uniquindio.parcial2.prestamoapp.model.PrestamoObjeto;
import co.edu.uniquindio.parcial2.prestamoapp.service.IModelFactoryService;
import co.edu.uniquindio.parcial2.prestamoapp.service.IPrestamoMapping;
import co.edu.uniquindio.parcial2.prestamoapp.utils.DataUtil;

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
    public boolean agregarEmpleado(EmpleadoDto empleadoDto) {
        Empleado empleado = mapper.empleadoDtoToEmpleado(empleadoDto);
        return prestamoObjeto.agregarEmpleado(empleado);
    }

    @Override
    public boolean eliminarEmpleado(String cedula) {
        return prestamoObjeto.eliminarEmpleado(cedula);
    }

    @Override
    public boolean actualizarEmpleado(String cedulaEmpleado, EmpleadoDto nuevoEmpleadoDto) {
        Empleado empleado = mapper.empleadoDtoToEmpleado(nuevoEmpleadoDto);
        return prestamoObjeto.actualizarEmpleado(cedulaEmpleado, empleado);
    }
}