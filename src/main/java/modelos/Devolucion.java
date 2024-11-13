package modelos;

import java.time.LocalDate;

public class Devolucion {
    private String idDevolucion;
    private Tiquete tiquete;
    private LocalDate fechaDevolucion;

    public Devolucion(String idDevolucion, Tiquete tiquete, LocalDate fechaDevolucion) {
        this.idDevolucion = idDevolucion;
        this.tiquete = tiquete;
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getIdDevolucion() {
        return idDevolucion;
    }

    public void setIdDevolucion(String idDevolucion) {
        this.idDevolucion = idDevolucion;
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
