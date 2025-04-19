package co.edu.uniquindio.parcial2.prestamoapp.model;

import java.util.ArrayList;
import java.util.List;

public class Objeto {
    private String nombre;
    private String idObjeto;
    PrestamoObjeto ownedByPrestamoUq;
    private DisponibilidadObjeto disponibilidadObjeto;
    private List<Prestamo> listaPrestamosAsociados;

    public Objeto() {
        listaPrestamosAsociados = new ArrayList<>();
    }

    public Objeto(String nombre, String idObjeto) {
        this.nombre = nombre;
        this.idObjeto = idObjeto;
        listaPrestamosAsociados = new ArrayList<>();
    }

    public Objeto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(String idObjeto) {
        this.idObjeto = idObjeto;
    }

    public PrestamoObjeto getOwnedByPrestamoUq() {
        return ownedByPrestamoUq;
    }

    public void setOwnedByPrestamoUq(PrestamoObjeto ownedByPrestamoUq) {
        this.ownedByPrestamoUq = ownedByPrestamoUq;
    }

    public DisponibilidadObjeto getDisponibilidadObjeto() {
        return disponibilidadObjeto;
    }

    public void setDisponibilidadObjeto(DisponibilidadObjeto disponibilidadObjeto) {
        this.disponibilidadObjeto = disponibilidadObjeto;
    }

    public List<Prestamo> getListaPrestamosAsociados() {
        return listaPrestamosAsociados;
    }

    public void setListaPrestamosAsociados(List<Prestamo> listaPrestamosAsociados) {
        this.listaPrestamosAsociados = listaPrestamosAsociados;
    }

    @Override
    public String toString() {
        return "Objeto{" +
                "nombre='" + nombre + '\'' +
                ", idObjeto='" + idObjeto + '\'' +
                '}';
    }
}