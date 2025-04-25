package co.edu.uniquindio.parcial2.prestamoapp.mapping.mappers;

import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.ClienteDto;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.ObjetoDto;
import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.PrestamoDto;
import co.edu.uniquindio.parcial2.prestamoapp.model.Cliente;
import co.edu.uniquindio.parcial2.prestamoapp.model.Empleado;
import co.edu.uniquindio.parcial2.prestamoapp.model.Objeto;
import co.edu.uniquindio.parcial2.prestamoapp.model.Prestamo;
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
        if (cliente == null) {
            return null;
        }
        return new ClienteDto(
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getCedula(),
                cliente.getEmail(),
                cliente.getDireccion(),
                cliente.getListaPrestamosAsociados().size());
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
                empleado.getEdad(),
                empleado.getListaPrestamosAsociados().size());

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

    @Override
    public List<ObjetoDto> getObjetosDto(List<Objeto> listaObjetos) {
        if (listaObjetos == null) {
            return null;
        }
        List<ObjetoDto> listaObjetosDto = new ArrayList<ObjetoDto>();
        for (Objeto objeto : listaObjetos) {
            listaObjetosDto.add(objetoToObjetoDto(objeto));
        }
        return listaObjetosDto;
    }

    @Override
    public ObjetoDto objetoToObjetoDto(Objeto objeto) {
        if (objeto == null) {
            return null;
        }
        return new ObjetoDto(
                objeto.getNombre(),
                objeto.getIdObjeto(),
                objeto.getDisponibilidadObjeto(),
                objeto.getListaPrestamosAsociados().size());
    }

    @Override
    public Objeto objetoDtoToObjeto(ObjetoDto objetoDto) {
        Objeto objeto = new Objeto();
        objeto.setNombre(objetoDto.nombre());
        objeto.setIdObjeto(objetoDto.idObjeto());
        objeto.setDisponibilidadObjeto(objetoDto.disponibilidadObjeto());
        return objeto;
    }

    @Override
    public List<PrestamoDto> getPrestamosDto(List<Prestamo> listaPrestamos) {
        if (listaPrestamos == null) {
            return null;
        }
        List<PrestamoDto> listaPrestamosDto = new ArrayList<PrestamoDto>();
        for (Prestamo prestamo : listaPrestamos) {
            listaPrestamosDto.add(prestamoToPrestamoDto(prestamo));
        }
        return listaPrestamosDto;
    }

    @Override
    public PrestamoDto prestamoToPrestamoDto(Prestamo prestamo) {
        return new PrestamoDto(
                prestamo.getNumeroPrestamo(),
                prestamo.getDescripcion(),
                prestamo.getFechaPrestamo(),
                prestamo.getFechaEntrega(),
                prestamo.getEmpleadoAsociado().getCedula(),
                prestamo.getClienteAsociado().getCedula(),
                prestamo.getEstadoPrestamo(),
                prestamo.obtenerObjetosPrestados());
    }

    @Override
    public Prestamo prestamoDtoToPrestamo(PrestamoDto prestamoDto, Empleado empleado, Cliente cliente) {
        Prestamo prestamo = new Prestamo();
        prestamo.setNumeroPrestamo(prestamoDto.numeroPrestamo());
        prestamo.setDescripcion(prestamoDto.descripcion());
        prestamo.setFechaPrestamo(prestamoDto.fechaPrestamo());
        prestamo.setFechaEntrega(prestamoDto.fechaEntrega());
        prestamo.setEmpleadoAsociado(empleado);
        prestamo.setClienteAsociado(cliente);
        prestamo.setEstadoPrestamo(prestamoDto.estadoPrestamo());
        return prestamo;
    }
}