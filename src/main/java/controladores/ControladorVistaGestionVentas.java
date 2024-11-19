package controladores;

import modelos.*;
import servicios.ServicioCaseta;
import servicios.ServicioTiquete;
import servicios.ServicioUsuario;
import servicios.ServicioViaje;
import util.interfaces.ILista;

public class ControladorVistaGestionVentas {
    private ServicioTiquete servicioTiquete;
    private ServicioViaje servicioViaje;
    private ServicioUsuario servicioUsuario;
    private ServicioCaseta servicioCaseta;
    public ControladorVistaGestionVentas() {
        this.servicioTiquete = new ServicioTiquete();
        this.servicioViaje = new ServicioViaje();
        this.servicioUsuario = new ServicioUsuario();
        this.servicioCaseta = new ServicioCaseta();
    }

    //Metodos de tiquetes
    public void agregarTiquete(String idTiquete, Viaje viaje, Cliente cliente) {
        this.servicioTiquete.agregarTiquete(idTiquete, viaje, cliente);
    }

    public ILista<Tiquete> obtenerTiquetes() {
        return this.servicioTiquete.obtenerTiquetes();
    }

    //Metodos de viajes
    public ILista<Viaje> obtenerViajes() {
        return this.servicioViaje.obtenerViajes();
    }

    public Viaje obtenerViajePorId(String idViaje) {
        return this.servicioViaje.obtenerViajePorId(idViaje);
    }

    public int obtenerIndiceViaje(String idViaje) {
        return this.servicioViaje.obtenerIndiceViaje(idViaje);
    }

    public int obtenerViajeIndiceCaseta(Caseta caseta, String idViaje){
        for(int i = 0; i < caseta.getEmpresa().getViajes().size(); i++){
            if(caseta.getEmpresa().getViajes().get(i).getIdViaje().equals(idViaje)){
                return i;
            }
        }
        return -1;
    }

    //Metodos de usuarios
    public ILista<Usuario> obtenerUsuarios() {
        return this.servicioUsuario.obtenerUsuarios();
    }

    public Cliente obtenerClientePorId(String idCliente) {
        return this.servicioUsuario.obtenerClientePorId(idCliente);
    }

    public void transaccionCliente(String idCliente, Tiquete tiquete, String accion, int puntos) {
        this.servicioUsuario.transaccionCliente(idCliente, tiquete, accion, puntos);
    }

    public void eliminarReservaCliente(String idReserva, String idCliente){
        this.servicioUsuario.eliminarReservaCliente(idReserva, idCliente);
    }

    public void agregarReservaCliente(String idReserva, String idCliente, Viaje viaje){
        this.servicioUsuario.agregarReservaCliente(idReserva, idCliente, viaje);
    }

    public void enviarNotificacion(String idCliente, String mensaje){
        this.servicioUsuario.enviarNotificacion(idCliente, mensaje);
    }

    //Metodos de caseta
    public void asignarCaseta(int fila, int columna, Caseta caseta) {
        this.servicioCaseta.asignarCaseta(fila, columna, caseta);
    }

}