package modelos;

import java.io.Serializable;

public abstract class Usuario extends Persona implements Serializable {
    private String contrasena;
    private String correo;
    private double sueldo;
    private static final long serialVersionUID = 1L;

    public Usuario(String documento, String nombre, String apellido, int edad, String genero, String telefono, String correo, String contrasena, double sueldo) {
        super(documento, nombre, apellido, edad, genero, telefono);
        this.correo = correo;
        this.contrasena = contrasena;
        this.sueldo = sueldo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
}
