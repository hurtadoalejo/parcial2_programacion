package co.edu.uniquindio.parcial2.prestamoapp.model;

import java.util.ArrayList;
import java.util.List;

public class Empleado extends Persona {
    private PrestamoObjeto ownedByPrestamoUq;
    private List<Prestamo> listaPrestamosAsociados;

    public Empleado(String nombre, String apellido, String cedula, int edad, PrestamoObjeto ownedByPrestamoUq) {
        super(nombre, apellido, cedula, edad);
        listaPrestamosAsociados = new ArrayList<>();
        this.ownedByPrestamoUq = ownedByPrestamoUq;
    }

    public Empleado() {
        listaPrestamosAsociados = new ArrayList<>();
    }

    public PrestamoObjeto getOwnedByPrestamoUq() {
        return ownedByPrestamoUq;
    }

    public void setOwnedByPrestamoUq(PrestamoObjeto ownedByPrestamoUq) {
        this.ownedByPrestamoUq = ownedByPrestamoUq;
    }

    public List<Prestamo> getListaPrestamosAsociados() {
        return listaPrestamosAsociados;
    }

    public void setListaPrestamosAsociados(List<Prestamo> listaPrestamosAsociados) {
        this.listaPrestamosAsociados = listaPrestamosAsociados;
    }
}