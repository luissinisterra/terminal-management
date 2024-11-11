package controladores;

import modelos.Usuario;
import servicios.ServicioUsuario;

public class ControladorVistaRegistro {
    private ServicioUsuario servicioUsuario;
    public ControladorVistaRegistro() {
        this.servicioUsuario = new ServicioUsuario();
    }

    //Metodos para acceder a los servicios de los usuarios
    public void agregarCliente(String documento, String nombre, String apellido, int edad, String genero, String telefono, String correo, String contrasena) {
        this.servicioUsuario.agregarCliente(documento, nombre, apellido, edad, genero, telefono, correo, contrasena);
    }

    public Usuario buscarUsuario(String documento, String contrasena){
        return this.servicioUsuario.buscarUsuario(documento, contrasena);
    }

}
