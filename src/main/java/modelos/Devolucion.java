package modelos;

import java.io.Serializable;
import java.time.LocalDate;

public class Devolucion implements Serializable {
    private Tiquete tiquete;
    private LocalDate fechaDevolucion;

    public Devolucion(Tiquete tiquete, LocalDate fechaDevolucion) {
        this.tiquete = tiquete;
        this.fechaDevolucion = fechaDevolucion;
    }

    public Tiquete getTiquete() {
        return tiquete;
    }

    public void setTiquete(Tiquete tiquete) {
        this.tiquete = tiquete;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}
