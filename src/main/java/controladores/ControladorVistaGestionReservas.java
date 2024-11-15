package controladores;

import modelos.Caseta;
import modelos.Reserva;
import modelos.Viaje;
import servicios.ServicioCaseta;
import servicios.ServicioReserva;
import util.interfaces.ILista;

public class ControladorVistaGestionReservas {
    private ServicioReserva servicioReserva;
    private ServicioCaseta servicioCaseta;
    public ControladorVistaGestionReservas() {
        this.servicioReserva = new ServicioReserva();
        this.servicioCaseta = new ServicioCaseta();
    }

    //Metodos de reserva

    public void eliminarReserva(String idReserva) {
        this.servicioReserva.eliminarReserva(idReserva);
    }

    public void actualizarReserva(String idReserva, Viaje nuevoViaje) {
        this.servicioReserva.actualizarReserva(idReserva, nuevoViaje);
    }

    public ILista<Reserva> obtenerReservas() {
        return this.servicioReserva.obtenerReservas();
    }

    //Metodos de casetas
    public Caseta[][] obtenerCasetas(){
        return this.servicioCaseta.obtenerCasetas();
    }

    public void asignarCaseta(int fila, int columna, Caseta caseta) {
        this.servicioCaseta.asignarCaseta(fila, columna, caseta);
    }

}
