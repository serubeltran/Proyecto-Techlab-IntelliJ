package com.techlab.inicio.modelo;

public abstract class Producto {
    private static int contadorId = 1;
    private int id;
    private String nombre;
    private double precio;
    private int cantidadStock;

    // Constructor
    public Producto(String nombre, double precio, int cantidadStock) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadStock = cantidadStock;
    }

    //GETTERS
    public int getId() {return id;}
    public String getNombre() {return nombre;}
    public double getPrecio() {return precio;}
    public int getCantidadStock() {return cantidadStock;}
    public void setCantidadStock(int cantidadStock) {this.cantidadStock = cantidadStock; }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Metodo abstracto para ser usado por las subclases
    public abstract String getTipoProducto();

    // Metodo para mostrar la info de cada producto
    public String tostring() {
        return String.format("ID: %d | Nombre: %s | Precio: $%.2f | Stock: %d", id, nombre, precio, cantidadStock);
    }


}
