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
        return this.servicioViajes.obtenerViajes();
    }

    public Viaje obtenerViajePorId(String idViaje) {
        return this.servicioViajes.obtenerViajePorId(idViaje);
    }

    public void asignarViajeBinario(String idViaje, Viaje viaje) {
        this.servicioViajes.asignarViajeBinario(idViaje, viaje);
    }


    //Metodos de empresas
    public ILista<Empresa> obtenerEmpresas() {
        return this.servicioEmpresa.obtenerEmpresas();
    }

    //Metodos de usuarios
    public void transaccionCliente(String idCliente, Tiquete tiquete, String accion, int puntos) {
        this.servicioUsuario.transaccionCliente(idCliente, tiquete, accion, puntos);
    }

}
