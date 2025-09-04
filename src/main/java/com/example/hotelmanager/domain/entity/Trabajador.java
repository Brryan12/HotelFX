package com.example.hotelmanager.domain.entity;

public abstract class Trabajador {
    protected String nombre;
    protected String primerApellido;
    protected String segundoApellido;
    protected int id;
    protected String cedula;
    protected String puesto;

    public Trabajador() {
    }

    public Trabajador(String nombre, String primerApellido, int id, String cedula, String puesto) {
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.id = id;
        this.cedula = cedula;
        this.puesto = puesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
}
