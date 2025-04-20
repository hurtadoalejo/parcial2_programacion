package co.edu.uniquindio.parcial2.prestamoapp.utils;

import co.edu.uniquindio.parcial2.prestamoapp.model.Cliente;
import co.edu.uniquindio.parcial2.prestamoapp.model.Empleado;
import co.edu.uniquindio.parcial2.prestamoapp.model.Objeto;
import co.edu.uniquindio.parcial2.prestamoapp.model.PrestamoObjeto;

public class DataUtil {

    public static PrestamoObjeto inicializarDatos() {
        PrestamoObjeto prestamoObjeto = new PrestamoObjeto();
        Cliente cliente1 = Cliente.builder()
                .cedula("1094")
                .nombre("juan")
                .apellido("arias")
                .direccion("armenia")
                .edad(17)
                .email("jsjsj@gmail.com")
                .build();

        Cliente cliente2 = Cliente.builder()
                .cedula("1095")
                .nombre("Ana")
                .apellido("cardenas")
                .direccion("quimbaya")
                .edad(25)
                .email("jsjsj2@gmail.com")
                .build();

        Cliente cliente3 = Cliente.builder()
                .cedula("1096")
                .nombre("Pedro")
                .apellido("perez")
                .direccion("armenia")
                .edad(40)
                .email("jsjsj3@gmail.com")
                .build();

        prestamoObjeto.getListaClientes().add(cliente1);
        prestamoObjeto.getListaClientes().add(cliente2);
        prestamoObjeto.getListaClientes().add(cliente3);

        Empleado empleado = new Empleado();
        empleado.setCedula("1097");
        empleado.setNombre("Juan");
        empleado.setApellido("Perez");
        empleado.setEdad(25);
        empleado.setOwnedByPrestamoUq(prestamoObjeto);

        prestamoObjeto.getListaEmpleados().add(empleado);

        Objeto objeto = new Objeto();
        objeto.setIdObjeto("1520");
        objeto.setNombre("Escoba");
        objeto.setOwnedByPrestamoUq(prestamoObjeto);

        prestamoObjeto.getListaObjetos().add(objeto);

        return prestamoObjeto;
    }
}