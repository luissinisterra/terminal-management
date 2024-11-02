package modelos;

import java.time.LocalDateTime;

public class Notificacion {
    private int id;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private String estado;
    private Cliente cliente;

    public Notificacion(String mensaje, Cliente cliente) {
        this.id = id;
        this.mensaje = mensaje;
        this.fechaCreacion = LocalDateTime.now();
        this.estado = "pendiente";
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}

