package controladores;

import modelos.Caseta;
import servicios.ServicioCaseta;
import servicios.ServicioUsuario;

public class ControladorVistaCaseta {
    private ServicioCaseta servicioCaseta;
    private ServicioUsuario servicioUsuario;

    public ControladorVistaCaseta() {
        this.servicioCaseta = new ServicioCaseta();
        this.servicioUsuario = new ServicioUsuario();
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

}
