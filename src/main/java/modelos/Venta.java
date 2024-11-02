package modelos;

import java.time.LocalDate;

public class Venta {
    private Viaje viaje;
    private Cliente cliente;
    private int cantidadTiquetes;
    private LocalDate fecha;

    public Venta(Viaje viaje, Cliente cliente, int cantidadTiquetes, LocalDate fecha) {
        this.viaje = viaje;
        this.cliente = cliente;
        this.cantidadTiquetes = cantidadTiquetes;
        this.fecha = fecha;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getCantidadTiquetes() {
        return cantidadTiquetes;
    }

    public void setCantidadTiquetes(int cantidadTiquetes) {
        this.cantidadTiquetes = cantidadTiquetes;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
