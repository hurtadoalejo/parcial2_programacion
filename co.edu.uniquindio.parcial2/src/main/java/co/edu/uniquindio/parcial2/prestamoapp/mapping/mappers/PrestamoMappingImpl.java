package co.edu.uniquindio.parcial2.prestamoapp.mapping.mappers;

import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.ClienteDto;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.parcial2.prestamoapp.model.Cliente;
import co.edu.uniquindio.parcial2.prestamoapp.model.Empleado;
import co.edu.uniquindio.parcial2.prestamoapp.service.IPrestamoMapping;

import java.util.ArrayList;
import java.util.List;

public class PrestamoMappingImpl implements IPrestamoMapping {
    @Override
    public List<ClienteDto> getClientesDto(List<Cliente> listaClientes) {
        if(listaClientes == null){
            return null;
        }
        List<ClienteDto> listaClientesDto = new ArrayList<ClienteDto>(listaClientes.size());
        for (Cliente cliente : listaClientes) {
            listaClientesDto.add(clienteToClienteDto(cliente));
        }

        return listaClientesDto;
    }

    @Override
    public ClienteDto clienteToClienteDto(Cliente cliente) {
        return new ClienteDto(
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getCedula(),
                cliente.getEmail(),
                cliente.getDireccion());
    }

    @Override
    public Cliente clienteDtoToCliente(ClienteDto clienteDto) {
        return Cliente.builder()
                .nombre(clienteDto.nombre())
                .apellido(clienteDto.apellido())
                .cedula(clienteDto.cedula())
                .email(clienteDto.email())
                .direccion(clienteDto.direccion())
                .build();
    }

    @Override
    public List<EmpleadoDto> getEmpleadosDto(List<Empleado> listaEmpleados) {
        if (listaEmpleados == null) {
            return null;
        }
        List<EmpleadoDto> listaEmpleadosDto = new ArrayList<EmpleadoDto>();
        for (Empleado empleado : listaEmpleados) {
            listaEmpleadosDto.add(empleadoToEmpleadoDto(empleado));
        }
        return listaEmpleadosDto;
    }

    @Override
    public EmpleadoDto empleadoToEmpleadoDto(Empleado empleado) {
        return new EmpleadoDto(
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getCedula(),
                empleado.getEdad());
    }

    @Override
    public Empleado empleadoDtoToEmpleado(EmpleadoDto empleadoDto) {
        Empleado empleado = new Empleado();
        empleado.setNombre(empleadoDto.nombre());
        empleado.setApellido(empleadoDto.apellido());
        empleado.setCedula(empleadoDto.cedula());
        empleado.setEdad(empleadoDto.edad());
        return empleado;
    }
}
