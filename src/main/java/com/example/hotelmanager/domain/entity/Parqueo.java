package com.example.hotelmanager.domain.entity;

public class Parqueo {
    private int idParqueo;
    private boolean estado;
    private boolean discapacidad;

    public Parqueo() {
    }

    public Parqueo(int idParqueo, boolean discapacidad) {
        this.idParqueo = idParqueo;
        this.estado = true; // Por defecto, el parqueo está disponible
        this.discapacidad = discapacidad;
    }

    public int getIdParqueo() {
        return idParqueo;
    }

    public void setIdParqueo(int idParqueo) {
        this.idParqueo = idParqueo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean isDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(boolean discapacidad) {
        this.discapacidad = discapacidad;
    }
}
