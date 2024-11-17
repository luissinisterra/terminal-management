package modelos;
import util.Cola;
import util.Lista;
import util.interfaces.ICola;
import util.interfaces.ILista;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Viaje implements Serializable {
    private String idViaje;
    private String origen;
    private String destino;
    private LocalDateTime fechaHoraSalida;
    private LocalDateTime fechaHoraLlegada;
    private LocalDateTime fechaCreacion;
    private Bus bus;
    private int valorUnitario;
    private ILista<Tiquete> tiquetes;
    private ILista<Reserva> reservas;
    private ICola<Cliente> colaEspera;

    public Viaje(String idViaje, String origen, String destino, LocalDateTime fechaHoraSalida, LocalDateTime fechaHoraLlegada, Bus bus, int valorUnitario) {
        this.idViaje = idViaje;
        this.origen = origen;
        this.destino = destino;
        this.fechaHoraSalida = fechaHoraSalida;
        this.fechaHoraLlegada = fechaHoraLlegada;
        this.fechaCreacion = LocalDateTime.now();
        this.bus = bus;
        this.valorUnitario = valorUnitario;
        this.tiquetes = new Lista<>();
        this.reservas = new Lista<>();
        this.colaEspera = new Cola<>();
    }

    public String getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(String idViaje) {
        this.idViaje = idViaje;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDateTime getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public LocalDateTime getFechaHoraLlegada() {
        return fechaHoraLlegada;
    }

    public void setFechaHoraLlegada(LocalDateTime fechaHoraLlegada) {
        this.fechaHoraLlegada = fechaHoraLlegada;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public int getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(int valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public ILista<Tiquete> getTiquetes() {
        return tiquetes;
    }

    public void setTiquetes(ILista<Tiquete> tiquetes) {
        this.tiquetes = tiquetes;
    }

    public ILista<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ILista<Reserva> reservas) {
        this.reservas = reservas;
    }

    public ICola<Cliente> getColaEspera() {
        return colaEspera;
    }

    public void setColaEspera(ICola<Cliente> colaEspera) {
        this.colaEspera = colaEspera;
    }

}
