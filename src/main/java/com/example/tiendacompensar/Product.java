package com.example.tiendacompensar;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {

    private String nombre;
    private String categoria;
    private int unidades;
    private double precio;
    private double iva;
    private double totalConIva;


    public Product(String nombre, String categoria, int unidades, double precio, double iva, double totalConIva) {
        this.nombre =  nombre;
        this.categoria =  categoria;
        this.unidades =  unidades;
        this.precio =  precio;
        this.iva =  iva;
        this.totalConIva =  precio * unidades * (1 + iva);
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotalConIva() {
        return totalConIva;
    }

    public void setTotalConIva(double totalConIva) {
        this.totalConIva = totalConIva;
    }


}
