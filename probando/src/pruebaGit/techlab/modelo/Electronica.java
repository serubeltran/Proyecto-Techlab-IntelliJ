package com.techlab.inicio.modelo;

public class Electronica extends Producto {
    private String marca;
    private int garantiaMeses;

    public Electronica(String nombre, double precio, int cantidadStock, String marca, int garantiaMeses) {
        super(nombre, precio, cantidadStock);
        this.marca = marca;
        this.garantiaMeses = garantiaMeses;
    }

    public String getTipoProducto() {
        return "Electrónica";
    }

    public String toString() {
        return String.format("%s | Tipo: Electrónica | Marca: %s | Garantía: %d meses",
                super.toString(), marca, garantiaMeses);
    }
}
