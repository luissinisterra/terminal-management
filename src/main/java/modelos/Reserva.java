package modelos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reserva implements Serializable {
    private String idReserva;
    private Viaje viaje;
    private Cliente cliente;
    private LocalDateTime fechaReserva;

    public Reserva(String idReserva, Viaje viaje, Cliente cliente) {
        this.idReserva = idReserva;
        this.viaje = viaje;
        this.cliente = cliente;
        this.fechaReserva = LocalDateTime.now();
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

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
}
