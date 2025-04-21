package co.edu.uniquindio.parcial2.prestamoapp.utils;

import co.edu.uniquindio.parcial2.prestamoapp.model.*;

import java.time.LocalDate;

public class DataUtil {

    public static PrestamoObjeto inicializarDatos() {
        PrestamoObjeto prestamoObjeto = new PrestamoObjeto();
        Cliente cliente1 = Cliente.builder()
                .cedula("1000")
                .nombre("Esteban")
                .apellido("Valencia")
                .direccion("Armenia")
                .edad(35)
                .email("esteban@gmail.com")
                .build();

        Cliente cliente2 = Cliente.builder()
                .cedula("1001")
                .nombre("Alejandro")
                .apellido("Hurtado")
                .direccion("Quimbaya")
                .edad(21)
                .email("alejo@gmail.com")
                .build();

        Cliente cliente3 = Cliente.builder()
                .cedula("1002")
                .nombre("Jorge")
                .apellido("Valencia")
                .direccion("Armenia")
                .edad(20)
                .email("jorge@gmail.com")
                .build();

        prestamoObjeto.getListaClientes().add(cliente1);
        prestamoObjeto.getListaClientes().add(cliente2);
        prestamoObjeto.getListaClientes().add(cliente3);

        Empleado empleado = new Empleado("Raul", "Yulbraynner", "1003", 29, prestamoObjeto);
        Empleado empleado2 = new Empleado("Diana", "Rivera", "1004", 30, prestamoObjeto);

        prestamoObjeto.getListaEmpleados().add(empleado);
        prestamoObjeto.getListaEmpleados().add(empleado2);

        Objeto objeto = new Objeto("Escoba", "1", prestamoObjeto);
        Objeto objeto2 = new Objeto("Silla", "2", prestamoObjeto);
        Objeto objeto3 = new Objeto("Mesa", "3", prestamoObjeto);
        Objeto objeto4 = new Objeto("Lampara", "4", prestamoObjeto);

        prestamoObjeto.getListaObjetos().add(objeto);
        prestamoObjeto.getListaObjetos().add(objeto2);
        prestamoObjeto.getListaObjetos().add(objeto3);
        prestamoObjeto.getListaObjetos().add(objeto4);

        Prestamo prestamo1 = new Prestamo("1", LocalDate.of(2025, 4, 1),
                "Prestamo para aula", empleado, cliente1);
        prestamoObjeto.agregarPrestamo(prestamo1);
        prestamoObjeto.agregarObjetoPrestamo("1", "1");
        prestamoObjeto.agregarObjetoPrestamo("1", "2");
        prestamoObjeto.agregarObjetoPrestamo("1", "3");
        prestamoObjeto.agregarObjetoPrestamo("1", "4");
        prestamoObjeto.entregarPrestamo("1", LocalDate.of(2025, 4, 3));

        Prestamo prestamo2 = new Prestamo("2", LocalDate.of(2025, 4, 3),
                "Prestamo para pasillo", empleado, cliente1);
        prestamoObjeto.agregarPrestamo(prestamo2);
        prestamoObjeto.agregarObjetoPrestamo("2", "1");
        prestamoObjeto.agregarObjetoPrestamo("2", "2");
        prestamoObjeto.agregarObjetoPrestamo("2", "3");
        prestamoObjeto.entregarPrestamo("2", LocalDate.of(2025, 4, 6));

        Prestamo prestamo3 = new Prestamo("3", LocalDate.of(2025, 4, 6),
                "Prestamo para pasillo", empleado, cliente1);
        prestamoObjeto.agregarPrestamo(prestamo3);
        prestamoObjeto.agregarObjetoPrestamo("3", "1");
        prestamoObjeto.agregarObjetoPrestamo("3", "2");
        prestamoObjeto.entregarPrestamo("3", LocalDate.of(2025, 4, 9));

        Prestamo prestamo4 = new Prestamo("4", LocalDate.of(2025, 4, 9),
                "Prestamo para secretaria", empleado2, cliente2);
        prestamoObjeto.agregarPrestamo(prestamo4);
        prestamoObjeto.agregarObjetoPrestamo("4", "1");
        prestamoObjeto.agregarObjetoPrestamo("4", "2");
        prestamoObjeto.agregarObjetoPrestamo("4", "3");
        prestamoObjeto.agregarObjetoPrestamo("4", "4");
        prestamoObjeto.entregarPrestamo("4", LocalDate.of(2025, 4, 12));

        Prestamo prestamo5 = new Prestamo("5", LocalDate.of(2025, 4, 12),
                "Prestamo para evento", empleado2, cliente2);
        prestamoObjeto.agregarPrestamo(prestamo5);
        prestamoObjeto.agregarObjetoPrestamo("5", "1");
        prestamoObjeto.entregarPrestamo("5", LocalDate.of(2025, 4, 15));

        Prestamo prestamo6 = new Prestamo("6", LocalDate.of(2025, 4, 15),
                "Prestamo para coliseo", empleado2, cliente3);
        prestamoObjeto.agregarPrestamo(prestamo6);
        prestamoObjeto.agregarObjetoPrestamo("6", "1");

        return prestamoObjeto;
    }
}