package co.edu.uniquindio.parcial2.prestamoapp;

import co.edu.uniquindio.parcial2.prestamoapp.model.Persona;
import co.edu.uniquindio.parcial2.prestamoapp.model.PrestamoObjeto;

public class Empleado extends Persona {
    PrestamoObjeto ownedByPrestamoUq;

    public Empleado() {
    }

    public PrestamoObjeto getOwnedByPrestamoUq() {
        return ownedByPrestamoUq;
    }

    public void setOwnedByPrestamoUq(PrestamoObjeto ownedByPrestamoUq) {
        this.ownedByPrestamoUq = ownedByPrestamoUq;
    }

}