package controladores;

import modelos.Caseta;
import modelos.Usuario;
import servicios.ServicioCaseta;
import servicios.ServicioUsuario;

public class ControladorVistaLogin {
    private ServicioUsuario servicioUsuario;
    private ServicioCaseta servicioCaseta;

    public ControladorVistaLogin() {
        this.servicioUsuario = new ServicioUsuario();
        this.servicioCaseta = new ServicioCaseta();
    }

    //Metodos de usuarios
    public void agregarAdmininistradorTerminal(String documento, String nombre, String apellido, int edad, String genero, String telefono, String correo, String contrasena, double sueldo) {
        this.servicioUsuario.agregarAdmininistradorTerminal(documento, nombre, apellido, edad, genero, telefono, correo, contrasena, sueldo);
    }

    public void agregarAdmininistradorFlota(String documento, String nombre, String apellido, int edad, String genero, String telefono, String correo, String contrasena, double sueldo) {
        this.servicioUsuario.agregarAdmininistradorFlota(documento, nombre, apellido, edad, genero, telefono, correo, contrasena, sueldo);
    }

    public void agregarCliente(String documento, String nombre, String apellido, int edad, String genero, String telefono, String correo, String contrasena, double sueldo) {
        this.servicioUsuario.agregarCliente(documento, nombre, apellido, edad, genero, telefono, correo, contrasena, sueldo);
    }

    public void eliminarUsuario(String documento) {
        this.servicioUsuario.eliminarUsuario(documento);
    }

    public void actualizarUsuario(String documento, String nombre, String apellido, int edad, String genero, String telefono, String correo, String contrasena, double sueldo) {
        this.servicioUsuario.actualizarUsuario(documento, nombre, apellido, edad, genero, telefono, correo, contrasena, sueldo);
    }

    public Usuario buscarUsuario(String documento, String contrasena){
        return this.servicioUsuario.buscarUsuario(documento, contrasena);
    }

    //Metodos de casetas
    public Caseta obtenerCaseta(int fila, int columna) {
        return servicioCaseta.entregarCaseta(fila, columna);
    }

    public void asignarCaseta(int fila, int columna, Caseta caseta) {
        servicioCaseta.asignarCaseta(fila, columna, caseta);
    }

    public Caseta[][] obtenerCasetas(){
        return this.servicioCaseta.obtenerCasetas();
    }

    /*public ILista<Usuario> getAdmins(){
        return this.servicioUsuario.getAdmins();
    }*/

}
