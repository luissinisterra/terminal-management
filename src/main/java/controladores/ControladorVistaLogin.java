package controladores;

import modelos.Usuario;
import servicios.ServicioUsuario;
import servicios.persistencia.ServicioUsuarioDatos;
import util.interfaces.ILista;

public class ControladorVistaLogin {
    private ServicioUsuario servicioUsuario;
    public ControladorVistaLogin() {
        this.servicioUsuario = new ServicioUsuario();
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

    public ILista<Usuario> getAdmins(){
        return this.servicioUsuario.getAdmins();
    }

}
