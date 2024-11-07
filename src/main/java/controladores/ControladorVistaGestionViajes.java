package controladores;

import modelos.Bus;
import modelos.Caseta;
import servicios.ServicioCaseta;
import servicios.ServicioViaje;

import java.time.LocalDateTime;

public class ControladorVistaGestionViajes {
    private ServicioViaje servicioViaje;
    private ServicioCaseta servicioCaseta;

    public ControladorVistaGestionViajes() {
        this.servicioViaje = new ServicioViaje();
        this.servicioCaseta = new ServicioCaseta();
    }

    //Metodos de viajes
    public void agregarViaje(String idViaje, String origen, String destino, LocalDateTime horaSalida, LocalDateTime horaLlegada, Bus bus, int valorUnitario) {
        this.servicioViaje.agregarViaje(idViaje, origen ,destino, horaSalida, horaLlegada, bus, valorUnitario);
    }

    public void eliminarViaje(String idViaje) {
        this.servicioViaje.eliminarViaje(idViaje);
    }

    public void actualizarViaje(String idViaje, String nuevoDestino, LocalDateTime nuevaFechaHoraLlegada, int valorUnitario) {
        this.servicioViaje.actualizarViaje(idViaje, nuevoDestino, nuevaFechaHoraLlegada, valorUnitario);
    }

    public Bus obtenerBusPorId(String placa, Caseta caseta) {
        return this.servicioViaje.obtenerBusPorId(placa, caseta);
    }

    //Metodos de casetas
    public Caseta[][] obtenerCasetas(){
        return this.servicioCaseta.obtenerCasetas();
    }

    public void asignarCaseta(int fila, int columna, Caseta caseta) {
        this.servicioCaseta.asignarCaseta(fila, columna, caseta);
    }

}
