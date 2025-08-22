package Backend;

import com.example.hotelmanager.Cliente;

import java.time.LocalDate;

public class Reservacion {
    private int idReservacion;
    private Cliente cliente;
    private Habitacion habitacion;
    private LocalDate fechaReservacion;
    private LocalDate fechaLlegada;
    private int cantNoches;
    private LocalDate fechaSalida;
    private double precioTotal;
    private double descuento;

    public Reservacion() {
    }

    public Reservacion(int idReservacion, Cliente cliente, Habitacion habitacion, LocalDate fechaReservacion, LocalDate fechaLlegada, int cantNoches) {
        this.idReservacion = idReservacion;
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fechaReservacion = fechaReservacion;
        this.fechaLlegada = fechaLlegada;
        this.cantNoches = cantNoches;
        this.setPrecioTotal(); //this.precioTotal = this.habitacion.getPrecio() * this.cantNoches;
    }

    public int getIdReservacion() {
        return idReservacion;
    }

    public void setIdReservacion(int idReservacion) {
        this.idReservacion = idReservacion;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal() {
        this.precioTotal = this.habitacion.getPrecio() * cantNoches;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida() {
        this.fechaSalida = this.fechaLlegada.plusDays(this.cantNoches);
    }

    public int getCantNoches() {
        return cantNoches;
    }

    public void setCantNoches(int cantNoches) {
        this.cantNoches = cantNoches;
    }

    public LocalDate getFechaReservacion() {
        return fechaReservacion;
    }

    public void setFechaReservacion(LocalDate fechaReservacion) {
        this.fechaReservacion = fechaReservacion;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDate fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }
}
