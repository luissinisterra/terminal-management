package modelos;

import java.io.Serializable;

public abstract class Usuario extends Persona implements Serializable {
    private String correo;
    private String contrasena;
    private static final long serialVersionUID = 1L;

    public Usuario(String documento, String nombre, String apellido, int edad, String genero, String telefono, String correo, String contrasena) {
        super(documento, nombre, apellido, edad, genero, telefono);
        this.correo = correo;
        this.contrasena = contrasena;
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

}
