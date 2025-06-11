package com.techlab.inicio.servicios;

import com.techlab.inicio.modelo.Producto;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pedido {
    private static int contPedidos;
    private int idPedido;
    private ArrayList<LineaPedido> lineasPedido;
    private double costoTotal;
    private LocalDateTime fechaPedido;
    private String estadoPedido;

    // Constructor
    public Pedido() {
        this.idPedido = contPedidos++;
        this.lineasPedido = lineasPedido;
        this.costoTotal = costoTotal;
        this.fechaPedido = LocalDateTime.now();
        this.estadoPedido = "Pendiente";
    }

    // Getters
    public int getIdPedido() {return idPedido; }
    public String getEstadoPedido() {return estadoPedido; }
    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    // Métodos Pedidos
    public boolean pedidoVacio() { return lineasPedido.isEmpty();}

    // Agregar una línea al pedido
    public void agregarLinea(Producto producto, int cantidad) {
        LineaPedido linea = new LineaPedido(producto, cantidad);
        lineasPedido.add(linea);
        // ----- Descuento del stock la cantidad pedida en la línea
        producto.setCantidadStock(producto.getCantidadStock()-cantidad);
        costoTotal += linea.getSubtotal();
    }

    // DEVOLVER STOCK ORIGINAL PARA PEDIDO CANCELADO
    void devolverStock() {
        for (LineaPedido linea : lineasPedido) {
            Producto producto = linea.getProducto();
            int viejoStock = producto.getCantidadStock() + linea.getCantidad();
            producto.setCantidadStock(viejoStock);
        }
    }

    // Mostrar datos pedido
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("=== PEDIDO #%d ===\n", idPedido));
        sb.append(String.format("Fecha: %s\n", fechaPedido.format(formatter)));
        sb.append(String.format("Estado: %s\n", estadoPedido));
        sb.append("Productos:\n");

        for (LineaPedido linea : lineasPedido) {
            sb.append(linea.toString()).append("\n");
        }

        sb.append(String.format("TOTAL: $%.2f\n", costoTotal));
        sb.append("==================");

        return sb.toString();
    }
}