package modelos;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Notificacion implements Serializable {
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Cliente cliente;

    public Notificacion(String mensaje, Cliente cliente) {
        this.mensaje = mensaje;
        this.fechaCreacion = LocalDateTime.now();
        this.cliente = cliente;
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

