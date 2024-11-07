package modelos;

import java.time.LocalDateTime;

public class Notificacion {
    private int idNotificacion;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Cliente cliente;

    public Notificacion(String mensaje, Cliente cliente) {
        this.idNotificacion = idNotificacion;
        this.mensaje = mensaje;
        this.fechaCreacion = LocalDateTime.now();
        this.cliente = cliente;
    }

    public int getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}

