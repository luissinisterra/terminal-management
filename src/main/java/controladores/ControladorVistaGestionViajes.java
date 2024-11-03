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
    public void agregarViaje(String idViaje, String origen, String destino, LocalDateTime horaSalida, LocalDateTime horaLlegada, Bus bus) {
        this.servicioViaje.agregarViaje(idViaje, origen ,destino, horaSalida, horaLlegada, bus);
    }

    //Metodos de casetas
    public Caseta[][] obtenerCasetas(){
        return this.servicioCaseta.obtenerCasetas();
    }

    public void asignarCaseta(int fila, int columna, Caseta caseta) {
        this.servicioCaseta.asignarCaseta(fila, columna, caseta);
    }
}
