package co.edu.uniquindio.parcial2.prestamoapp.model;

import co.edu.uniquindio.parcial2.prestamoapp.Empleado;

import java.util.ArrayList;
import java.util.List;

public class PrestamoObjeto {

    List<Cliente> listaClientes = new ArrayList<>();
    List<Empleado> listaEmpleados = new ArrayList<>();
    List<Objeto> listaObjetos = new ArrayList<>();
    List<Prestamo> listaPrestamos = new ArrayList<>();

    private String nombre;

    public PrestamoObjeto() {
    }

    public boolean crearCliente(String cedula,
                                String nombre,
                                String apellido,
                                String email,
                                String telefonoFijo,
                                String telefonoCelular,
                                String direccion){
        Cliente clienteEncontrado = obtenerCliente(cedula);
        if(clienteEncontrado == null){
            Cliente cliente = getBuildCliente(cedula, nombre, apellido, email, telefonoFijo, telefonoCelular, direccion);
            getListaClientes().add(cliente);
            return true;
        }else{
            return  false;
        }
    }

    public boolean crearCliente(Cliente nuevoCliente){
        Cliente clienteEncontrado = obtenerCliente(nuevoCliente.getCedula());
        if(clienteEncontrado == null){
            getListaClientes().add(nuevoCliente);
            return true;
        }else{
            return  false;
        }
    }

    public boolean actualizarCliente(String cedulaClienteViejo, Cliente nuevoCliente){
        Cliente clienteViejo = obtenerCliente(cedulaClienteViejo);
        if (clienteViejo != null) {
            if (!existeCliente(nuevoCliente.getCedula()) ||
                    nuevoCliente.getCedula().equalsIgnoreCase(cedulaClienteViejo)){
                intercambiarInstanciasEnModelo(clienteViejo, nuevoCliente);
                return true;
            }
        }
        return false;
    }

    private void intercambiarInstanciasEnModelo(Cliente clienteViejo, Cliente nuevoCliente) {
        int indiceCliente = getListaClientes().indexOf(clienteViejo);
        getListaClientes().set(indiceCliente, nuevoCliente);
        intercambiarInstanciasClientePrestamo(clienteViejo.getCedula(), nuevoCliente);
    }

    private void intercambiarInstanciasClientePrestamo(String cedula, Cliente nuevoCliente) {
        for (Prestamo prestamo: listaPrestamos) {
            if(prestamo.getClienteAsociado().getCedula().equalsIgnoreCase(cedula)){
                prestamo.setClienteAsociado(nuevoCliente);
            }
        }
    }

    private boolean existeCliente(String cedula){
        return obtenerCliente(cedula) != null;
    }

    private Cliente getBuildCliente(String cedula, String nombre, String apellido, String email, String telefonoFijo, String telefonoCelular, String direccion) {
        return Cliente.builder()
                .nombre(nombre)
                .apellido(apellido)
                .cedula(cedula)
                .direccion(direccion)
                .email(email)
                .telefonoFijo(telefonoFijo)
                .telefonoCelular(telefonoCelular)
                .build();
    }

    private Cliente obtenerCliente(String cedula) {
        Cliente cliente = null;
        for (Cliente cliente1: getListaClientes()) {
            if(cliente1.getCedula().equalsIgnoreCase(cedula)){
                cliente = cliente1;
                break;
            }
        }

        return cliente;
    }


    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String obtenerClientesPorCiudad(String ciudad) {
        String resultado = "";

        for (Cliente cliente: getListaClientes()) {
            if(cliente.getDireccion().equalsIgnoreCase(ciudad)){
                resultado = resultado + cliente.toString()+ "\n";
            }
        }

        return resultado;
    }

    public boolean eliminarCliente(String cedula) {
        Cliente clienteEncontrado = obtenerCliente(cedula);
        if(clienteEncontrado !=null){
            getListaClientes().remove(clienteEncontrado);
            return true;
        }else{
            return false;
        }
    }
}