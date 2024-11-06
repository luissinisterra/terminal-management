package controladores;

import modelos.Cliente;
import modelos.Usuario;
import modelos.Viaje;
import servicios.ServicioTiquete;
import servicios.ServicioUsuario;
import servicios.ServicioViaje;
import util.interfaces.ILista;

public class ControladorVistaGestionVentas {
    private ServicioTiquete servicioTiquete;
    private ServicioViaje servicioViaje;
    private ServicioUsuario servicioUsuario;
    public ControladorVistaGestionVentas() {
        this.servicioTiquete = new ServicioTiquete();
        this.servicioViaje = new ServicioViaje();
        this.servicioUsuario = new ServicioUsuario();
    }

    //Metodos de tiquetes
    public void agregarTiquete(String idTiquete, Viaje viaje, Cliente cliente) {
        this.servicioTiquete.agregarTiquete(idTiquete, viaje, cliente);
    }

    //Metodos de viajes
    public ILista<Viaje> obtenerViajes() {
        return this.servicioViaje.obtenerViajes();
    }

    //Metodos de usuarios
    public ILista<Usuario> obtenerUsuarios() {
        return this.servicioUsuario.obtenerUsuarios();
    }


}
