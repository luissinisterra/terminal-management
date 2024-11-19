package controladores;

import modelos.*;
import servicios.*;
import util.interfaces.ILista;

public class ControladorVistaReservarTiquete {
    private ServicioReserva servicioReserva;
    private ServicioCaseta servicioCaseta;
    private ServicioViaje servicioViaje;
    private ServicioEmpresa servicioEmpresa;
    private ServicioUsuario servicioUsuario;
    public ControladorVistaReservarTiquete() {
        this.servicioReserva = new ServicioReserva();
        this.servicioCaseta = new ServicioCaseta();
        this.servicioViaje = new ServicioViaje();
        this.servicioEmpresa = new ServicioEmpresa();
        this.servicioUsuario = new ServicioUsuario();
    }

    //Metodos de reserva
    public void agregarReserva(String idReserva, Viaje viaje, Cliente cliente) {
        this.servicioReserva.agregarReserva(idReserva, viaje, cliente);
    }

    //Metodos de casetas
    public Caseta[][] obtenerCasetas(){
        return this.servicioCaseta.obtenerCasetas();
    }

    public int obtenerViajeIndiceCaseta(Caseta caseta, String idViaje){
        for(int i = 0; i < caseta.getEmpresa().getViajes().size(); i++){
            if(caseta.getEmpresa().getViajes().get(i).getIdViaje().equals(idViaje)){
                return i;
            }
        }
        return -1;
    }

    public void asignarCaseta(int fila, int columna, Caseta caseta) {
        this.servicioCaseta.asignarCaseta(fila, columna, caseta);
    }

    //Metodo de viajes
    public ILista<Viaje> obtenerViajes() {
        return this.servicioViaje.obtenerViajes();
    }

    public Viaje obtenerViajePorId(String idViaje) {
        return this.servicioViaje.obtenerViajePorId(idViaje);
    }

    public void asignarViajeBinario(String idViaje, Viaje viaje) {
        this.servicioViaje.asignarViajeBinario(idViaje, viaje);
    }

    public ILista<Viaje> filtrarViajesPorFecha(String filtro) {
        return this.servicioViaje.filtrarViajesPorFecha(filtro);
    }

    public ILista<Viaje> filtrarViajePorDestino(String destinoBusqueda) {
        return this.servicioViaje.filtrarViajePorDestino(destinoBusqueda);
    }

    //Metodos de empresas
    public ILista<Empresa> obtenerEmpresas() {
        return this.servicioEmpresa.obtenerEmpresas();
    }

    //Metodos de usuarios
    public void agregarReservaCliente(String idReserva, String idCliente, Viaje viaje){
        this.servicioUsuario.agregarReservaCliente(idReserva, idCliente, viaje);
    }

    public void enviarNotificacion(String idCliente, String mensaje){
        this.servicioUsuario.enviarNotificacion(idCliente, mensaje);
    }


}
