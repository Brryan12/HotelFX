package com.example.hotelmanager.domain.entity;

public class Personal extends  Trabajador{
    private String turno;
    private String area;
    private String bitacora;

    public Personal() {
        super();
    }

    public Personal(String nombre, String primerApellido, int id, String cedula, String puesto) {
        super(nombre, primerApellido, id, cedula, puesto);
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

    public String getBitacora() {
        return bitacora;
    }

    public void setBitacora(String bitacora) {
        this.bitacora = bitacora;
    }
}
