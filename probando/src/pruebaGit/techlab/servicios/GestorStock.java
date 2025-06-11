package com.techlab.inicio.servicios;

import com.techlab.inicio.modelo.Producto;
import java.util.ArrayList;

public class GestorStock {
    private static ArrayList <Producto> productos;
    private ArrayList <Pedido> pedidos;

    // Constructor
    public GestorStock() {
        this.productos = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }


    // Agregar producto
    public void agregarProducto(Producto producto) {
        if (producto.getPrecio() < 0 | producto.getCantidadStock() < 0 ) {
            System.out.println("Error: El valor no puede ser negativo.");
            return;
        }
        productos.add(producto);
        System.out.println("Producto agregado exitosamente con ID: " + producto.getId());
        System.out.println("Tipo: " + producto.getTipoProducto());
    }

    // Mostrar productos
    public static void mostrarProductos() {
        if (productos == null) {
            System.out.println("No hay productos ingresados.");
            return;
        }
        System.out.println("\n=== LISTA DE PRODUCTOS ===");
        for (Producto producto : productos) {
            System.out.println(producto);
        }
        System.out.println("==========================\n");
    }

    // Buscar productos por Id
    public Producto buscarxId(int id) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }

    // Buscar productos por Nombre
    public ArrayList<Producto> buscarxNombre(String nombre) {
        ArrayList<Producto> productosEncontrados = new ArrayList<>();
        String nombreBusqueda = nombre.toLowerCase();

        for (Producto producto : productos) {
            if (producto.getNombre().toLowerCase().contains(nombreBusqueda)) {
                productosEncontrados.add(producto);
            }
        }
        return productosEncontrados;
    }

    // Mostrar array de productos encontrados
    public void mostrarProductosEncontrados(ArrayList<Producto> listaProductos) {
        System.out.println("\nProductos encontrados:");
        for (int i = 0; i < listaProductos.size(); i++) {
            System.out.println((i + 1) + ". " + listaProductos.get(i));
        }
    }

    // Actualizar productos
    public void actualizarProductos(int id, Double nuevoPrecio, Integer nuevoStock) {
        Producto producto = buscarxId(id);
        if (producto == null) {
            System.out.println("Producto no encontrado con ID: " + id);
            return;
        }

        if (nuevoPrecio != null) {
            if (nuevoPrecio < 0) {
                System.out.println("Error: El precio no puede ser negativo.");
                return;
            }
            producto.setPrecio(nuevoPrecio);
        }

        if (nuevoStock != null) {
            if (nuevoStock < 0) {
                System.out.println("Error: El stock no puede ser negativo.");
                return;
            }
            producto.setCantidadStock(nuevoStock);
        }

        System.out.println("Producto actualizado correctamente:");
        System.out.println(producto);
    }

    // Eliminar productos
    public boolean eliminarProducto(int id) {
        Producto producto = buscarxId(id);
        if (producto == null) {
            System.out.println("Producto no encontrado con ID: " + id);
            return false;
        }

        productos.remove(producto);
        System.out.println("Producto eliminado exitosamente: " + producto.getNombre());
        return true;
    }

    // Metodo para obtener el tamaño del array
    public int getTamano() {
        return productos.size();
    }

    // Metodo crear nuevo pedido
    public Pedido crearNuevoPedido() {
        return new Pedido();
    }

    // Metodo para agregar un pedido confirmado
    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    // Metodo para mostrar todos los pedidos
    public void mostrarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }
        System.out.println("\n=== LISTA DE PEDIDOS ===");
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
            System.out.println();
        }
    }


}
