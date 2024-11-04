package controladores;

import modelos.*;
import servicios.ServicioCaseta;
import servicios.ServicioReserva;
import servicios.ServicioViaje;
import util.interfaces.ILista;

public class ControladorVistaReservarTiquete {
    private ServicioReserva servicioReserva;
    private ServicioCaseta servicioCaseta;
    private ServicioViaje servicioViajes;
    public ControladorVistaReservarTiquete() {
        this.servicioReserva = new ServicioReserva();
        this.servicioCaseta = new ServicioCaseta();
        this.servicioViajes = new ServicioViaje();
    }

    //Metodos de reserva
    public void agregarReserva(String idReserva, Viaje viaje, Cliente cliente, int cantidadReserva) {
        this.servicioReserva.agregarReserva(idReserva, viaje, cliente, cantidadReserva);
    }

    public void eliminarReserva(String idReserva) {
        this.servicioReserva.eliminarReserva(idReserva);
    }

    public void actualizarReserva(String idReserva, Viaje nuevoViaje, int nuevaCantidadReserva) {
        this.servicioReserva.actualizarReserva(idReserva, nuevoViaje, nuevaCantidadReserva);
    }

    public ILista<Reserva> obtenerReservas() {
        return this.servicioReserva.obtenerReservas();
    }

    //Metodos de casetas
    public Caseta[][] obtenerCasetas(){
        return this.servicioCaseta.obtenerCasetas();
    }

    //Metodo de viajes
    public ILista<Viaje> obtenerViajes() {
        return this.servicioViajes.obtenerViajes();
    }

}
