package modelos;

import util.Lista;
import util.interfaces.ILista;

import java.io.Serializable;

public class Cliente extends Usuario implements Serializable {
    private ILista<Notificacion> notificaciones;
    private ILista<Transaccion> transacciones;
    private ILista<Reserva> reservas;
    private int puntos;

    public Cliente(String documento, String nombre, String apellido, int edad, String genero, String telefono, String correo, String contrasena) {
        super(documento, nombre, apellido, edad, genero, telefono, correo, contrasena);
        this.notificaciones = new Lista<>();
        this.transacciones = new Lista<>();
        this.reservas = new Lista<>();
        this.puntos = 0;
    }

    public ILista<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(ILista<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }

    public ILista<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(ILista<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public ILista<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ILista<Reserva> reservas) {
        this.reservas = reservas;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void actualizarPuntos() {
        int totalInvertido = 0;
        for (int i = 0; i < this.transacciones.size(); i++) {
            if(this.transacciones.get(i).getAccion().equals("Compra")){
                totalInvertido += this.transacciones.get(i).getTiquete().getViaje().getValorUnitario();
            } else if(this.transacciones.get(i).getAccion().equals("Devolucion")) {
                totalInvertido -= this.transacciones.get(i).getTiquete().getViaje().getValorUnitario();
            }
        }
        int nuevosPuntos = (totalInvertido / 10000) * 3;
        this.setPuntos(nuevosPuntos);
    }
}


