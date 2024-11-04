package modelos;

import java.io.Serializable;
import java.time.LocalDate;

public class Reserva implements Serializable {
    private String idReserva;
    private Viaje viaje;
    private Cliente cliente;
    private int cantidadReserva;
    private LocalDate fechaReserva;

    public Reserva(String idReserva, Viaje viaje, Cliente cliente, int cantidadReserva) {
        this.idReserva = idReserva;
        this.viaje = viaje;
        this.cliente = cliente;
        this.cantidadReserva = cantidadReserva;
        this.fechaReserva = LocalDate.now();
    }

    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
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

    public int getCantidadReserva() {
        return cantidadReserva;
    }

    public void setCantidadReserva(int cantidadReserva) {
        this.cantidadReserva = cantidadReserva;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
}
