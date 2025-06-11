package com.techlab.inicio.servicios;

import java.util.ArrayList;
import java.util.Scanner;

import com.techlab.inicio.modelo.*;
import com.techlab.inicio.util.*;

import static com.techlab.inicio.util.Uti.*;

public class menu {

    private static GestorStock gestor = new GestorStock();
    private static Scanner scanner = new Scanner(System.in);

    // Menu1 - Opcion 1: agregar producto
    public static void agregarProducto() {
        int tipoProducto = 0;
        boolean salida = false;
        do {
            System.out.println("\n=== AGREGAR PRODUCTO ===");
            System.out.println("Seleccione el tipo de producto:");
            System.out.println("1. Bebida");
            System.out.println("2. Comida");
            System.out.println("3. Electrónica");
            System.out.println("0. Menú anterior");

            tipoProducto = Uti.leerEntero("Seleccione el tipo: ");

            if (tipoProducto == 0) { return; }

            if (tipoProducto < 1 | tipoProducto > 4) {
                System.out.println("Error. Ingrese una opción válida");}
            else {salida = true;}

        } while (!salida);


        System.out.println("Nombre del Producto: ");
        String nombreP = scanner.nextLine();

        if (nombreP.trim().isEmpty()) {
            System.out.println("Error: El nombre no puede estar vacío.");
            return;
        }

        double precioP = leerDouble("Precio del producto: $");
        int stockP = leerEntero("Cantidad en stock: ");

        Producto nuevoP = null;

        switch (tipoProducto) {
            case 1: // Bebida
                double volumen = leerDouble("Volumen en litros: ");
                System.out.println("¿Es alcohólica? (s/n): ");
                boolean alcoholica = scanner.nextLine().equalsIgnoreCase("s");

                nuevoP = new Bebida(nombreP, precioP, stockP, volumen, alcoholica);
                break;

            case 2: // Comida
                double peso = leerDouble("Peso en gramos: ");
                System.out.print("Categoría (lácteo/carne/vegetal/etc.): ");
                String categoria = scanner.nextLine();

                nuevoP = new Comida(nombreP, precioP, stockP, peso, categoria);
                break;

            case 3: // Electronica
                System.out.print("Marca: ");
                String marca = scanner.nextLine();
                int garantia = leerEntero("Garantía en meses: ");

                nuevoP = new Electronica(nombreP, precioP, stockP, marca, garantia);
                break;

            default:
                System.out.println("Tipo de Producto no válido.");
                return;
        }
        gestor.agregarProducto(nuevoP);
    }

    // Menu1 - Opcion 3: buscar producto
    public static void buscarProducto() {
        System.out.println("1. Buscar por ID");
        System.out.println("2. Buscar por nombre");

        int opcion = leerEntero("Seleccione tipo de búsqueda: ");

        switch (opcion) {
            case 1:
                int id = leerEntero("Ingrese el ID del producto: ");
                Producto producto = gestor.buscarxId(id);
                if (producto != null) {
                    System.out.println("\nProducto encontrado:");
                    System.out.println(producto);
                } else {
                    System.out.println("Producto no encontrado con ID: " + id);
                }
                break;

            case 2:
                System.out.print("Ingrese el nombre del producto: ");
                String nombre = scanner.nextLine();

                ArrayList<Producto> productosEncontrados = gestor.buscarxNombre(nombre);

                if (productosEncontrados.isEmpty()) {
                    System.out.println("No se encontraron productos que contengan: " + nombre);
                } else if (productosEncontrados.size() == 1) {
                    System.out.println("\nProducto encontrado:");
                    System.out.println(productosEncontrados.get(0));
                } else {  // Varios productos encontrados
                    gestor.mostrarProductosEncontrados(productosEncontrados);
                }
                break;
            default:
                System.out.println("Opción no válida.");
        }

    }

    // Menu1 - Opcion 4: actualizar producto
    public static void actualizarProducto() {
        System.out.println("\n--- ACTUALIZAR PRODUCTO ---");

        int id = leerEntero("Ingrese el ID del producto a actualizar: ");
        Producto producto = gestor.buscarxId(id);

        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.println("Producto actual:");
        System.out.println(producto);
        System.out.println("\n¿Qué desea actualizar?");
        Double nuevoPrecio = null;
        Integer nuevoStock = null;

        nuevoPrecio = leerDouble("Nuevo precio: $");
        nuevoStock = leerEntero("Nuevo stock: ");

        gestor.actualizarProductos(id, nuevoPrecio, nuevoStock);
    }

    // Menu1 - Opcion 5: eliminar producto
    public static void eliminarProducto() {
        System.out.println("\n--- ELIMINAR PRODUCTO ---");

        if (gestor.getTamano() == 0) {
            System.out.println("No hay productos para eliminar.");
            return;
        }

        System.out.println("1. Eliminar por ID");
        System.out.println("2. Eliminar por nombre");

        int opcion = leerEntero("Seleccione método de búsqueda: ");
        Producto productoAEliminar = null;

        switch (opcion) {
            case 1: // Eliminar por Id
                gestor.mostrarProductos();
                int id = leerEntero("Ingrese el ID del producto a eliminar: ");
                productoAEliminar = gestor.buscarxId(id);

                if (productoAEliminar == null) {
                    System.out.println("Producto no encontrado con ID: " + id);
                    return;
                }
                break;

            case 2: // Eliminar por Nombre
                System.out.print("Ingrese el nombre del producto (o parte del nombre): ");
                String nombre = scanner.nextLine();

                ArrayList<Producto> productosEncontrados = gestor.buscarxNombre(nombre);

                if (productosEncontrados.isEmpty()) {
                    System.out.println("No se encontraron productos que contengan: " + nombre);
                    return;
                } else if (productosEncontrados.size() == 1) {
                    productoAEliminar = productosEncontrados.get(0);
                } else { // Múltiples productos encontrados
                    gestor.mostrarProductosEncontrados(productosEncontrados);
                    int seleccion = leerEntero("\nSeleccione el número del producto a eliminar (0 para cancelar): ");

                    if (seleccion > 0 && seleccion <= productosEncontrados.size()) {
                        productoAEliminar = productosEncontrados.get(seleccion - 1);
                    } else if (seleccion == 0) {
                        System.out.println("Eliminación cancelada.");
                        return;
                    } else {
                        System.out.println("Selección inválida.");
                        return;
                    }
                }
                break;

            default:
                System.out.println("Opción no válida.");
                return;
        }

        if (productoAEliminar == null) {
            System.out.println("No se seleccionó ningún producto para eliminar.");
            return;
        }

        // Muestra producto a eliminar
        System.out.println("\nProducto a eliminar:");
        System.out.println(productoAEliminar);

        System.out.print("¿Está seguro que desea eliminar este producto? (s/n): ");
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("s") || confirmacion.equalsIgnoreCase("si")) {
            gestor.eliminarProducto(productoAEliminar.getId());
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }

    // **********************************************************
    // Menu1 - Opcion 6: Crear nuevo pedido
    public static void crearPedido() {
        System.out.println("\n--- CREAR NUEVO PEDIDO ---");

        if (gestor.getTamano() == 0) {
            System.out.println("No hay productos disponibles para crear un pedido.");
            return;
        }

        Pedido nuevoPedido = gestor.crearNuevoPedido();

        while (true) {
            mostrarMenuPedidos();
            int opcionP = leerEntero("Seleccione una opción: ");

            switch (opcionP) {
                case 1: agregarProductoAPedido(nuevoPedido); break;
                case 2:GestorStock.mostrarProductos(); break;
                case 3: // PEDIDO: Ver pedido actual
                    if (nuevoPedido.pedidoVacio()) {
                        System.out.println("El pedido está vacío.");
                    } else {
                        System.out.println("\n" + nuevoPedido);
                    }
                    break;
                case 4: // PEDIDO: Confirmar pedido
                    if (nuevoPedido.pedidoVacio() ) {
                        System.out.println("No puede confirmar un pedido vacío.");
                        break;
                    }

                    System.out.println("\nResumen del pedido:");
                    System.out.println(nuevoPedido);

                    System.out.print("¿Confirmar este pedido? (s/n): ");
                    String confirmacion = scanner.nextLine();

                    if (confirmacion.equalsIgnoreCase("s") || confirmacion.equalsIgnoreCase("si")) {
                        nuevoPedido.setEstadoPedido("Confirmado");
                        gestor.agregarPedido(nuevoPedido);
                        System.out.println("¡Pedido confirmado correctamente!");
                        System.out.println("Número de pedido: " + nuevoPedido.getIdPedido());
                        return;
                    }
                    break;
                case 5: // PEDIDO: Cancelar pedido
                    nuevoPedido.devolverStock();
                    System.out.println("Pedido cancelado.");
                    return;
                case 0:
                    return;
                default:
                    System.out.println("Error. Opción no válida.");
            }

        }
    }

    // PEDIDO - Agregar producto al pedido
    private static void agregarProductoAPedido(Pedido pedido) {
        System.out.println("\n--- AGREGAR PRODUCTO AL PEDIDO ---");

        System.out.println("1. Buscar por ID");
        System.out.println("2. Buscar por nombre");

        int opcion = leerEntero("¿Cómo desea buscar el producto?: ");
        Producto producto = null;

        switch (opcion) {
            case 1:
                gestor.mostrarProductos();
                int id = leerEntero("Ingrese el ID del producto: ");
                producto = gestor.buscarxId(id);
                break;

            case 2:
                System.out.print("Ingrese el nombre del producto: ");
                String nombre = scanner.nextLine();

                ArrayList<Producto> productosEncontrados = gestor.buscarxNombre(nombre);

                if (productosEncontrados.isEmpty()) {
                    System.out.println("No se encontraron productos.");
                    return;
                } else if (productosEncontrados.size() == 1) {
                    producto = productosEncontrados.get(0);
                } else {
                    gestor.mostrarProductosEncontrados(productosEncontrados);
                    int seleccion = leerEntero("Seleccione el producto (0 para cancelar): ");

                    if (seleccion > 0 && seleccion <= productosEncontrados.size()) {
                        producto = productosEncontrados.get(seleccion - 1);
                    } else {
                        return;
                    }
                }
                break;

            default:
                System.out.println("Error. Opción no válida.");
                return;
        }

        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }
    }

}
