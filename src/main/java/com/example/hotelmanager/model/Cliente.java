package com.example.hotelmanager.model;
//3 clases corazon
//cosas que no sean utiles del core de la aplicacion pueden ir en clases miselaneas


import java.time.LocalDate;
import java.time.Period;


public class Cliente {
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private int id;
    private String identificacion;
    // private String telefono;
    //private String correo; //clase miselanea
    private LocalDate fechaNacimiento;
    private int edad;
    private String nombreCompleto;

    public Cliente() {
    }

    public Cliente(String nombre, int id, LocalDate fechaNacimiento, String primerApellido, String identificacion) {
        this.nombre = nombre;
        this.id = id;
        this.fechaNacimiento = fechaNacimiento;
        this.primerApellido = primerApellido;
        this.identificacion = identificacion;
        this.nombreCompleto = nombre + " " + primerApellido;
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

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        LocalDate fechaActual = LocalDate.now();
        if(Period.between(this.fechaNacimiento, fechaActual).getYears() >= 18 ) {
            this.fechaNacimiento = fechaNacimiento;
        }
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad() {
        LocalDate fechaActual = LocalDate.now();
        this.edad = Period.between(this.fechaNacimiento, fechaActual).getYears();
    }
}
