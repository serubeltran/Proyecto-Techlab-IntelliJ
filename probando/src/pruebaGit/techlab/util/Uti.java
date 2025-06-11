package com.techlab.inicio.util;

import java.util.Scanner;

public class Uti {
    public static Scanner scanner = new Scanner(System.in);

    // Mostrar Menu Principal
    public static void mostrarMenu() {
        System.out.println("\n=== MENÚ PRINCIPAL - TECHLAB ===");
        System.out.println("1. Agregar producto");
        System.out.println("2. Mostrar todos los productos");
        System.out.println("3. Buscar producto");
        System.out.println("4. Actualizar producto");
        System.out.println("5. Eliminar producto");
        System.out.println("6. Crear pedido");
        System.out.println("7. Mostrar pedidos");
        System.out.println("0. Salir");
        System.out.println("=".repeat(20));
        return;
    }

    // Mostrar Menu Pedidos
    public static void mostrarMenuPedidos() {
        System.out.println("\n=== MENÚ PEDIDOS - TECHLAB ===");
        System.out.println("1. Agregar producto al pedido");
        System.out.println("2. Ver productos disponibles");
        System.out.println("3. Ver pedido actual");
        System.out.println("4. Confirmar pedido");
        System.out.println("5. Cancelar pedido");
        System.out.println("0. Volver al menú principal");
        return;
    }

    // Leer enteros y validar
    public static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número entero válido.");
            }
        }
    }

    // Leer double y validar
    public static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String input = scanner.nextLine();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número decimal válido.");
            }
        }
    }
}
