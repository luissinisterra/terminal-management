package controladores;

import modelos.*;
import util.interfaces.ILista;

import javax.swing.*;

public class ControladorPrincipal {
    private Caseta[][] casetas;

    public ControladorPrincipal() {
        this.casetas = new Caseta[4][0];
        this.initCasetas();
    }

    private void initCasetas() {
        this.casetas[0] = new Caseta[5];
        for (int i = 1; i < this.casetas.length; i++) {
            this.casetas[i] = new Caseta[2];
        }
    }

    public Caseta entregarCaseta(int fila, int columna){
        return this.casetas[fila][columna];
    }

    public void asignarCaseta(int fila, int columna, Caseta caseta){
        this.casetas[fila][columna] = caseta;
    }

    /*public Usuario iniciarSesion(String documento, String contrasena) {
        for(int i = 0; i < this.usuarios.size(); i++) {
            boolean existeDocumento = false;
            boolean existeContrasena = false;

            if(this.usuarios.get(i).getDocumento().equals(documento)) {
                existeDocumento = true;
            }

            if(this.usuarios.get(i).getContrasena().equals(contrasena)) {
                existeContrasena = true;
            }

            if(existeDocumento && existeContrasena) {
                return this.usuarios.get(i);
            }
        }
        return null;
    }

    public Cliente registro(String documento, String nombre, String apellido, int edad, String genero, String telefono, String correo, String contrasena, double sueldo) {
        Cliente usuario = new Cliente(documento, nombre, apellido, edad, genero, telefono, correo, contrasena, sueldo);
        this.usuarios.add(usuario);
        return usuario;
    }*/

}
