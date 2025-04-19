package co.edu.uniquindio.parcial2.prestamoapp.service;

import co.edu.uniquindio.parcial2.prestamoapp.mapping.dto.ClienteDto;

import java.util.List;

public interface IModelFactoryService {
    List<ClienteDto> obtenerClientes();

    boolean agregarCliente(ClienteDto clienteDto);

    boolean actualizarCliente(String cedulaVieja, ClienteDto clienteDto);

    boolean eliminarCliente(String cedula);
}