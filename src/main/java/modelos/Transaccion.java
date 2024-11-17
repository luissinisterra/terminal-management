package modelos;

import java.io.Serializable;

public class Transaccion implements Serializable {
    private Tiquete tiquete;
    private String accion;
    private int puntos;
    private static final long serialVersionUID = 1L;

    public Transaccion(Tiquete tiquete, String accion, int puntos) {
        this.tiquete = tiquete;
        this.accion = accion;
        this.puntos = puntos;
    }

    public Tiquete getTiquete() {
        return tiquete;
    }

    public void setTiquete(Tiquete tiquete) {
        this.tiquete = tiquete;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

}
