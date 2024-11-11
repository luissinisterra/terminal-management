package modelos;

import util.Lista;
import util.interfaces.ILista;

import java.io.Serializable;

public class Cliente extends Usuario implements Serializable {
    private ILista<Notificacion> notificaciones;
    private ILista<Tiquete> tiquetes;
    private int puntos;

    public Cliente(String documento, String nombre, String apellido, int edad, String genero, String telefono, String correo, String contrasena) {
        super(documento, nombre, apellido, edad, genero, telefono, correo, contrasena);
        this.notificaciones = new Lista<>();
        this.tiquetes = new Lista<>();
        this.puntos = 0;
    }

    public ILista<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(ILista<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }

    public ILista<Tiquete> getTiquetes() {
        return tiquetes;
    }

    public void setTiquetes(ILista<Tiquete> tiquetes) {
        this.tiquetes = tiquetes;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void actualizarPuntos() {
        int totalInvertido = 0;
        for (int i = 0; i < this.tiquetes.size(); i++) {
            totalInvertido += this.tiquetes.get(i).getPrecio();
        }
        int nuevosPuntos = (totalInvertido / 10000) * 3;
        this.setPuntos(nuevosPuntos);
    }
}


