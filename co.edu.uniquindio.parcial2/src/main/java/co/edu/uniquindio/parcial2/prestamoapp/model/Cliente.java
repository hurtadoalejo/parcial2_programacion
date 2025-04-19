package co.edu.uniquindio.parcial2.prestamoapp.model;

import co.edu.uniquindio.parcial2.prestamoapp.model.builder.ClienteBuilder;;

public class Cliente {

    private String cedula;
    private String nombre;
    private String apellido;
    private String email;
    private String telefonoFijo;
    private String telefonoCelular;
    private String direccion;
    private int edad;

    public Cliente(String cedula,
                   String nombre,
                   String apellido,
                   String email,
                   String telefonoFijo,
                   String telefonoCelular,
                   String direccion,
                   int edad) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefonoFijo = telefonoFijo;
        this.telefonoCelular = telefonoCelular;
        this.direccion = direccion;
        this.edad = edad;
    }

    public static ClienteBuilder builder(){
        return new ClienteBuilder();
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getEdad() {
        return edad;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", telefonoFijo='" + telefonoFijo + '\'' +
                ", telefonoCelular='" + telefonoCelular + '\'' +
                ", direccion='" + direccion + '\'' +
                ", edad=" + edad +
                '}';
    }
}