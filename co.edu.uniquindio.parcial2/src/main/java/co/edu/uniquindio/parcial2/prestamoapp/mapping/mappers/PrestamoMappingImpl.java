package co.edu.uniquindio.parcial2.prestamoapp.mapping.mappers;

import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.ClienteDto;
import co.edu.uniquindio.parcial2.prestamoapp.model.Cliente;
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
}
