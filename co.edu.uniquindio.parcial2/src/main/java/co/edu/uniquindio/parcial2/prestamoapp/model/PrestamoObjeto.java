package co.edu.uniquindio.parcial2.prestamoapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrestamoObjeto {

    List<Cliente> listaClientes = new ArrayList<>();
    List<Empleado> listaEmpleados = new ArrayList<>();
    List<Objeto> listaObjetos = new ArrayList<>();
    List<Prestamo> listaPrestamos = new ArrayList<>();

    private String nombre;

    public PrestamoObjeto() {
        listaClientes = new ArrayList<>();
        listaEmpleados = new ArrayList<>();
        listaObjetos = new ArrayList<>();
        listaPrestamos = new ArrayList<>();
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public List<Objeto> getListaObjetos() {
        return listaObjetos;
    }

    public void setListaObjetos(List<Objeto> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public List<Prestamo> getListaPrestamos() {
        return listaPrestamos;
    }

    public void setListaPrestamos(List<Prestamo> listaPrestamos) {
        this.listaPrestamos = listaPrestamos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
            if (obtenerCliente(nuevoCliente.getCedula()) == null ||
                    nuevoCliente.getCedula().equalsIgnoreCase(cedulaClienteViejo)){
                intercambiarInstanciaClienteModelo(clienteViejo, nuevoCliente);
                return true;
            }
        }
        return false;
    }

    private void intercambiarInstanciaClienteModelo(Cliente clienteViejo, Cliente nuevoCliente) {
        intercambiarInstanciaCliente(clienteViejo, nuevoCliente);
        intercambiarInstanciaClientePrestamo(clienteViejo, nuevoCliente);
    }

    private void intercambiarInstanciaCliente(Cliente clienteViejo, Cliente nuevoCliente) {
        int indiceCliente = getListaClientes().indexOf(clienteViejo);
        getListaClientes().set(indiceCliente, nuevoCliente);
    }

    private void intercambiarInstanciaClientePrestamo(Cliente clienteViejo, Cliente nuevoCliente) {
        for (Prestamo prestamo: clienteViejo.getListaPrestamosAsociados()) {
            prestamo.setClienteAsociado(nuevoCliente);
        }
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

    public boolean agregarObjeto(Objeto objeto){
        if (obtenerObjeto(objeto.getIdObjeto()) == null) {
            listaObjetos.add(objeto);
            return true;
        }
        return false;
    }

    public boolean eliminarObjeto(String idObjeto){
        Objeto objetoEncontrado = obtenerObjeto(idObjeto);
        if (objetoEncontrado != null) {
            listaObjetos.remove(objetoEncontrado);
            return true;
        }
        return false;
    }

    public boolean actualizarObjeto(String idObjeto, Objeto nuevoObjeto){
        Objeto objetoViejo = obtenerObjeto(idObjeto);
        if (objetoViejo != null) {
            if (obtenerObjeto(nuevoObjeto.getIdObjeto()) == null ||
            nuevoObjeto.getIdObjeto().equalsIgnoreCase(idObjeto)){
                objetoViejo.setNombre(nuevoObjeto.getNombre());
                objetoViejo.setIdObjeto(nuevoObjeto.getIdObjeto());
                return true;
            }
        }
        return false;
    }

    public Objeto obtenerObjeto(String idObjeto){
        for (Objeto objeto: listaObjetos) {
            if(objeto.getIdObjeto().equalsIgnoreCase(idObjeto)){
                return objeto;
            }
        }
        return null;
    }

    public boolean agregarEmpleado(Empleado empleado) {
        if (obtenerEmpleado(empleado.getCedula()) == null) {
            listaEmpleados.add(empleado);
            return true;
        }
        return false;
    }

    public boolean eliminarEmpleado(String cedula) {
        Empleado empleadoEncontrado = obtenerEmpleado(cedula);
        if (empleadoEncontrado != null) {
            listaEmpleados.remove(empleadoEncontrado);
            return true;
        }
        return false;
    }

    public boolean actualizarEmpleado(String cedulaEmpleado, Empleado nuevoEmpleado) {
        Empleado empleadoViejo = obtenerEmpleado(cedulaEmpleado);
        if (empleadoViejo != null) {
            if (obtenerEmpleado(nuevoEmpleado.getCedula()) == null ||
            nuevoEmpleado.getCedula().equalsIgnoreCase(cedulaEmpleado)){
                empleadoViejo.setNombre(nuevoEmpleado.getNombre());
                empleadoViejo.setApellido(nuevoEmpleado.getApellido());
                empleadoViejo.setCedula(nuevoEmpleado.getCedula());
                empleadoViejo.setEdad(nuevoEmpleado.getEdad());
                return true;
            }
        }
        return false;
    }

    public Empleado obtenerEmpleado(String cedulaEmpleado) {
        for (Empleado empleado: listaEmpleados) {
            if(empleado.getCedula().equalsIgnoreCase(cedulaEmpleado)){
                return empleado;
            }
        }
        return null;
    }

    public boolean agregarPrestamo(Prestamo prestamo) {
        if (obtenerPrestamo(prestamo.getNumeroPrestamo()) == null &&
                verificarDisponibilidadObjetos(prestamo) &&
                !prestamo.getListaObjetosAsociados().isEmpty() &&
                !prestamo.isEntregado()) {
            listaPrestamos.add(prestamo);
            prestamo.getClienteAsociado().getListaPrestamosAsociados().add(prestamo);
            prestamo.getEmpleadoAsociado().getListaPrestamosAsociados().add(prestamo);
            cambiarEstadoDisponibilidadObjetosOcupados(prestamo);
            return true;
        }
        return false;
    }

    public boolean eliminarPrestamo(String numeroPrestamo) {
        Prestamo prestamoEncontrado = obtenerPrestamo(numeroPrestamo);
        if (prestamoEncontrado != null && !prestamoEncontrado.isEntregado()) {
            listaPrestamos.remove(prestamoEncontrado);
            prestamoEncontrado.getClienteAsociado().getListaPrestamosAsociados().remove(prestamoEncontrado);
            prestamoEncontrado.getEmpleadoAsociado().getListaPrestamosAsociados().remove(prestamoEncontrado);
            return true;
        }
        return false;
    }

    public boolean actualizarPrestamo(String numeroPrestamo, Prestamo nuevoPrestamo) {
        Prestamo prestamoViejo = obtenerPrestamo(numeroPrestamo);
        if (prestamoViejo != null && !prestamoViejo.isEntregado()) {
            if (obtenerPrestamo(nuevoPrestamo.getNumeroPrestamo()) == null ||
                    nuevoPrestamo.getNumeroPrestamo().equalsIgnoreCase(numeroPrestamo)) {
                prestamoViejo.setNumeroPrestamo(nuevoPrestamo.getNumeroPrestamo());
                prestamoViejo.setFechaPrestamo(nuevoPrestamo.getFechaPrestamo());
                prestamoViejo.setFechaEntrega(nuevoPrestamo.getFechaEntrega());
                prestamoViejo.setDescripcion(nuevoPrestamo.getDescripcion());
                cambiarEmpleadoPrestamo(prestamoViejo, nuevoPrestamo);
                cambiarClientePrestamo(prestamoViejo, nuevoPrestamo);
            }
        }
        return false;
    }

    public boolean entregarPrestamo(String numeroPrestamo, Date fechaEntrega) {
        Prestamo prestamoEncontrado = obtenerPrestamo(numeroPrestamo);
        if (prestamoEncontrado != null && !prestamoEncontrado.isEntregado()) {
            if (verificarFechasPrestamo(prestamoEncontrado, fechaEntrega)) {
                prestamoEncontrado.setEntregado(true);
                cambiarEstadoDisponibilidadObjetosLibres(prestamoEncontrado);
                return true;
            }
        }
        return false;
    }

    public Prestamo obtenerPrestamo(String numeroPrestamo) {
        for (Prestamo prestamo: listaPrestamos) {
            if(prestamo.getNumeroPrestamo().equalsIgnoreCase(numeroPrestamo)){
                return prestamo;
            }
        }
        return null;
    }

    private boolean verificarDisponibilidadObjetos(Prestamo prestamo) {
        for (Objeto objeto: prestamo.getListaObjetosAsociados()) {
            if (objeto.isPrestado()) {
                return false;
            }
        }
        return true;
    }

    private void cambiarEstadoDisponibilidadObjetosOcupados(Prestamo prestamo) {
        for (Objeto objeto: prestamo.getListaObjetosAsociados()) {
            objeto.setPrestado(true);
        }
    }

    private void cambiarEstadoDisponibilidadObjetosLibres(Prestamo prestamo) {
        for (Objeto objeto: prestamo.getListaObjetosAsociados()) {
            objeto.setPrestado(false);
        }
    }

    private void cambiarClientePrestamo(Prestamo prestamoViejo, Prestamo nuevoPrestamo) {
        Cliente clienteViejo = prestamoViejo.getClienteAsociado();
        Cliente clienteNuevo = nuevoPrestamo.getClienteAsociado();
        if (!clienteViejo.getCedula().equalsIgnoreCase(clienteNuevo.getCedula())) {
            clienteViejo.getListaPrestamosAsociados().remove(prestamoViejo);
            clienteNuevo.getListaPrestamosAsociados().add(nuevoPrestamo);
            prestamoViejo.setClienteAsociado(clienteNuevo);
        }
    }

    private void cambiarEmpleadoPrestamo(Prestamo prestamoViejo, Prestamo nuevoPrestamo) {
        Empleado empleadoViejo = prestamoViejo.getEmpleadoAsociado();
        Empleado empleadoNuevo = nuevoPrestamo.getEmpleadoAsociado();
        if (!empleadoViejo.getCedula().equalsIgnoreCase(empleadoNuevo.getCedula())) {
            empleadoViejo.getListaPrestamosAsociados().remove(prestamoViejo);
            empleadoNuevo.getListaPrestamosAsociados().add(nuevoPrestamo);
            prestamoViejo.setEmpleadoAsociado(empleadoNuevo);
        }
    }

    private boolean verificarFechasPrestamo(Prestamo prestamo, Date fechaEntrega) {
        Date fechaPrestamo = prestamo.getFechaPrestamo();
        return fechaEntrega.after(fechaPrestamo);
    }
}