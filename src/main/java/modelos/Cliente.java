package modelos;

import util.Lista;
import util.interfaces.ILista;

public class Cliente extends Usuario {
    private ILista<Notificacion> notificaciones;
    private ILista<Tiquete> tiquetes;
    private int puntos;

    public Cliente(String documento, String nombre, String apellido, int edad, String genero, String telefono, String correo, String contrasena, double sueldo) {
        super(documento, nombre, apellido, edad, genero, telefono, correo, contrasena, sueldo);
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
}


