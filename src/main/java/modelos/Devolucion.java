package modelos;

import java.time.LocalDate;

public class Devolucion {
    private Tiquete tiquete;
    private LocalDate fecha;

    public Devolucion(Tiquete tiquete, LocalDate fecha) {
        this.tiquete = tiquete;
        this.fecha = fecha;
    }

    public Tiquete getTiquete() {
        return tiquete;
    }

    public void setTiquete(Tiquete tiquete) {
        this.tiquete = tiquete;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
