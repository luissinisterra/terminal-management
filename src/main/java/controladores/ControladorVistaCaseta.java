package controladores;

import modelos.AdministradorFlota;
import modelos.Caseta;
import servicios.ServicioCaseta;
import servicios.ServicioEmpresa;
import servicios.ServicioUsuario;

public class ControladorVistaCaseta {
    private ServicioCaseta servicioCaseta;
    private ServicioUsuario servicioUsuario;
    private ServicioEmpresa servicioEmpresa;

    public ControladorVistaCaseta() {
        this.servicioCaseta = new ServicioCaseta();
        this.servicioUsuario = new ServicioUsuario();
        this.servicioEmpresa = new ServicioEmpresa();
    }

    //Metodos de casetas
    public Caseta obtenerCaseta(int fila, int columna) {
        return servicioCaseta.entregarCaseta(fila, columna);
    }

    public void asignarCaseta(int fila, int columna, Caseta caseta) {
        servicioCaseta.asignarCaseta(fila, columna, caseta);
    }

    //Metodos de usuarios
    public void agregarAdmininistradorFlota(String documento, String nombre, String apellido, int edad, String genero, String telefono, String correo, String contrasena, double sueldo) {
        this.servicioUsuario.agregarAdmininistradorFlota(documento, nombre, apellido, edad, genero, telefono, correo, contrasena, sueldo);
    }

    //Metodos de empresas
    public void agregarEmpresa(String nit, String nombreEmpresa, AdministradorFlota administradorFlota) {
        this.servicioEmpresa.agregarEmpresa(nit, nombreEmpresa, administradorFlota);
    }

}
