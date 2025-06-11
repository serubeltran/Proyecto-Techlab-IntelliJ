package com.techlab.inicio;

import java.util.Scanner;

import com.techlab.inicio.servicios.*;
import static com.techlab.inicio.util.Uti.leerEntero;
import static com.techlab.inicio.util.Uti.mostrarMenu;

public class Principal {

    private static GestorStock gestor = new GestorStock();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("===== SISTEMA DE GESTIÓN DE PRODUCTOS =====");
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch(opcion) {
                case 1: menu.agregarProducto(); break;
                case 2: gestor.mostrarProductos(); break;
                case 3: menu.buscarProducto(); break;
                case 4: menu.actualizarProducto(); break;
                case 5: menu.eliminarProducto(); break;
                case 6: menu.crearPedido(); break;
                case 7: gestor.mostrarPedidos(); break;
                case 0:
                    System.out.println("¡Gracias por usar el sistema!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 0);
        scanner.close();
    }
}
