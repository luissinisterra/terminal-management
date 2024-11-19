package controladores;

import modelos.Caseta;
import modelos.Reserva;
import modelos.Viaje;
import servicios.ServicioCaseta;
import servicios.ServicioReserva;
import servicios.ServicioUsuario;
import servicios.ServicioViaje;
import util.interfaces.ILista;

public class ControladorVistaGestionReservas {
    private ServicioReserva servicioReserva;
    private ServicioCaseta servicioCaseta;
    private ServicioViaje servicioViaje;
    private ServicioUsuario servicioUsuario;
    public ControladorVistaGestionReservas() {
        this.servicioReserva = new ServicioReserva();
        this.servicioCaseta = new ServicioCaseta();
        this.servicioViaje = new ServicioViaje();
        this.servicioUsuario = new ServicioUsuario();
    }

    //Metodos de reserva
    public void eliminarReserva(String idReserva) {
        this.servicioReserva.eliminarReserva(idReserva);
    }

    public boolean buscarReservaPorId(String idReserva) {
        return this.servicioReserva.buscarReservaPorId(idReserva);
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

    public int obtenerViajeIndiceCaseta(Caseta caseta, String idViaje){
        for(int i = 0; i < caseta.getEmpresa().getViajes().size(); i++){
            if(caseta.getEmpresa().getViajes().get(i).getIdViaje().equals(idViaje)){
                return i;
            }
        }
        return -1;
    }

    //Metodos de viajes
    public Viaje obtenerViajePorId(String idViaje) {
        return this.servicioViaje.obtenerViajePorId(idViaje);
    }

    public void agregarReservaCliente(String idReserva, String idCliente, Viaje viaje){
        this.servicioUsuario.agregarReservaCliente(idReserva, idCliente, viaje);
    }

    public void enviarNotificacion(String idCliente, String mensaje){
        this.servicioUsuario.enviarNotificacion(idCliente, mensaje);
    }

}
