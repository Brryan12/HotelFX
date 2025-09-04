package com.example.hotelmanager.domain.entity;

public class Habitacion {
    private int id; //tag interno
    private int numero; //tag para identificar
    private int tipo;
    private String descTipo;
    private int estado;
    private String descEstado;
    private double precio;
    private int capacidad;

    public Habitacion() {
    }

    public Habitacion(int id, int numero, double precio, int estado, int capacidad) {
        this.id = id;
        this.numero = numero;
        this.precio = precio;
        this.estado = estado;
        this.capacidad = capacidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDescTipo() {
        return descTipo;
    }

    public void setDescTipo(String descTipo) {
        this.descTipo = descTipo;
    }

    public String getDescEstado() {
        return descEstado;
    }

    public void setDescEstado(String descEstado) {
        this.descEstado = descEstado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) { this.precio = precio; }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getCapacidad(){ return capacidad; }

    public void setCapacidad(int capacidad){ this.capacidad = capacidad; }
}
