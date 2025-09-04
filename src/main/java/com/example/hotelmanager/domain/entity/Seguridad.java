package com.example.hotelmanager.domain.entity;

public class Seguridad extends Trabajador {
    private String turno;
    private String area;

    public Seguridad() {
        super();
    }

    public Seguridad(String nombre, String primerApellido, int id, String cedula, String puesto, String turno, String area) {
        super(nombre, primerApellido, id, cedula, puesto);
        this.turno = turno;
        this.area = area;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
