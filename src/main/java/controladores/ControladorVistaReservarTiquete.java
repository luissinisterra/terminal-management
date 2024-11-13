package controladores;

import modelos.*;
import servicios.*;
import util.interfaces.ILista;

public class ControladorVistaReservarTiquete {
    private ServicioReserva servicioReserva;
    private ServicioCaseta servicioCaseta;
    private ServicioViaje servicioViajes;
    private ServicioEmpresa servicioEmpresa;
    private ServicioUsuario servicioUsuario;
    public ControladorVistaReservarTiquete() {
        this.servicioReserva = new ServicioReserva();
        this.servicioCaseta = new ServicioCaseta();
        this.servicioViajes = new ServicioViaje();
        this.servicioEmpresa = new ServicioEmpresa();
        this.servicioUsuario = new ServicioUsuario();
    }

    //Metodos de reserva
    public void agregarReserva(String idReserva, Viaje viaje, Cliente cliente, int cantidadReserva) {
        this.servicioReserva.agregarReserva(idReserva, viaje, cliente, cantidadReserva);
    }

    //Metodos de casetas
    public Caseta[][] obtenerCasetas(){
        return this.servicioCaseta.obtenerCasetas();
    }

    public void asignarCaseta(int fila, int columna, Caseta caseta) {
        this.servicioCaseta.asignarCaseta(fila, columna, caseta);
    }

    //Metodo de viajes
    public ILista<Viaje> obtenerViajes() {
        return this.servicioViajes.obtenerViajes();
    }

    //Metodos de empresas
    public ILista<Empresa> obtenerEmpresas() {
        return this.servicioEmpresa.obtenerEmpresas();
    }

    //Metodos de usuarios
    public void transaccionCliente(String idCliente, Tiquete tiquete, String accion) {
        this.servicioUsuario.transaccionCliente(idCliente, tiquete, accion);
    }

}
