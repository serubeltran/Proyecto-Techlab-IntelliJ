package com.techlab.inicio.servicios;

import com.techlab.inicio.modelo.Producto;

public class LineaPedido {
    private Producto producto;
    private int cantidad;
    private double subtotal;

    // Constructor
    public LineaPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = producto.getPrecio() * cantidad;
    }

    // Getters y setters
    public Producto getProducto() {return producto;}
    public void setProducto(Producto producto) {this.producto = producto;}
    public int getCantidad() {return cantidad;}
    public void setCantidad(int cantidad) {this.cantidad = cantidad;}
    public double getSubtotal() {return subtotal;}
    //public void setSubtotal(double subtotal) {this.subtotal = subtotal;}

    // Mostrar líneas pedido
    public String toString() {
        return String.format("  - %s x%d = $%.2f",
                producto.getNombre(), cantidad, subtotal);
    }
}
