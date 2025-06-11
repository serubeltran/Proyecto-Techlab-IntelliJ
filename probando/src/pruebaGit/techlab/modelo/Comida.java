package com.techlab.inicio.modelo;

public class Comida extends Producto {
    private double pesoGramos;
    private String categoria;

    public Comida(String nombre, double precio, int cantidadStock, double pesoGramos, String categoria) {
        super(nombre, precio, cantidadStock);
        this.pesoGramos = pesoGramos;
        this.categoria = categoria;
    }

    @Override
    public String getTipoProducto() {
        return "Comida";
    }

    @Override
    public String toString() {
        return String.format("%s | Tipo: Comida | Peso: %.2fg | Categoría: %s",
                super.toString(), pesoGramos, categoria);
    }
}
