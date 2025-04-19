package co.edu.uniquindio.parcial2.prestamoapp.service;

import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.ClienteDto;
import co.edu.uniquindio.parcial2.prestamoapp.model.Cliente;

import java.util.List;

public interface IPrestamoMapping {
    List<ClienteDto> getClientesDto(List<Cliente> listaClientes);
    ClienteDto clienteToClienteDto(Cliente cliente);
    Cliente clienteDtoToCliente(ClienteDto clienteDto);
}
