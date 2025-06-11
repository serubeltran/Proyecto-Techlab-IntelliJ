package com.techlab.inicio.modelo;

public class Bebida extends Producto {
    private double volumenLitros;
    private boolean alcoholica;

    // Constructor
    public Bebida(String nombre, double precio, int cantidadStock, double volumenLitros, boolean alcoholica) {
        super(nombre, precio, cantidadStock);
        this.volumenLitros = volumenLitros;
        this.alcoholica = alcoholica;
    }

    @Override
    public String getTipoProducto() {
        return "Bebida"; }

    @Override
    public String toString() {
        return String.format("%s | Tipo: Bebida | Vol: %.2fL | Alcohol: %s",
                super.toString(), volumenLitros, alcoholica ? "Sí" : "No"); }

}