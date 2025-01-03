package modelos;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Tiquete implements Serializable {
    private String idTiquete;
    private Viaje viaje;
    private Cliente cliente;
    private LocalDateTime fechaVenta;
    private int precio;
    private static final long serialVersionUID = 1L;

    public Tiquete(String idTiquete, Viaje viaje, Cliente cliente) {
        this.idTiquete = idTiquete;
        this.viaje = viaje;
        this.cliente = cliente;
        this.precio = viaje.getValorUnitario();
        this.fechaVenta = LocalDateTime.now();
    }

    public String getIdTiquete() {
        return idTiquete;
    }

    public void setIdTiquete(String idTiquete) {
        this.idTiquete = idTiquete;
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

    public LocalDateTime getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDateTime fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}

