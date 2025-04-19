package co.edu.uniquindio.parcial2.prestamoapp.model;

import co.edu.uniquindio.parcial2.prestamoapp.Empleado;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Prestamo {
    private String numeroPrestamo;
    private Date fechaPrestamo;
    private Date fechaEntrega;
    private String descripcion;
    private Empleado empleadoAsociado;
    private Cliente clienteAsociado;
    List<Objeto> listaObjetosAsociados = new ArrayList<>();

    public Prestamo() {
    }

    public String getNumeroPrestamo() {
        return numeroPrestamo;
    }

    public void setNumeroPrestamo(String numeroPrestamo) {
        this.numeroPrestamo = numeroPrestamo;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Empleado getEmpleadoAsociado() {
        return empleadoAsociado;
    }

    public void setEmpleadoAsociado(Empleado empleadoAsociado) {
        this.empleadoAsociado = empleadoAsociado;
    }

    public Cliente getClienteAsociado() {
        return clienteAsociado;
    }

    public void setClienteAsociado(Cliente clienteAsociado) {
        this.clienteAsociado = clienteAsociado;
    }

    public List<Objeto> getListaObjetosAsociados() {
        return listaObjetosAsociados;
    }

    public void setListaObjetosAsociados(List<Objeto> listaObjetosAsociados) {
        this.listaObjetosAsociados = listaObjetosAsociados;
    }
}