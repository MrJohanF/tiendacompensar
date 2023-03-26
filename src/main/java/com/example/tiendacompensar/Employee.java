package com.example.tiendacompensar;

public class Employee {

    private String nombre;
    private String identificacion;
    private String antiguedad;
    private int edad;
    private String jornadaDeTrabajo;

    private String beneficio;

    public Employee(String nombre, String identificacion, String antiguedad, int edad, String jornadaDeTrabajo, String beneficio) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.antiguedad = antiguedad;
        this.edad = edad;
        this.jornadaDeTrabajo = jornadaDeTrabajo;
        this.beneficio = beneficio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(String antiguedad) {
        this.antiguedad = antiguedad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getJornadaDeTrabajo() {
        return jornadaDeTrabajo;
    }

    public void setJornadaDeTrabajo(String jornadaDeTrabajo) {
        this.jornadaDeTrabajo = jornadaDeTrabajo;
    }

    public String getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(String beneficio) {
        this.beneficio = beneficio;
    }
}

