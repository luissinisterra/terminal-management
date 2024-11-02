package modelos;

import java.io.Serializable;

public class AdministradorTerminal extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    public AdministradorTerminal(String documento, String nombre, String apellido, int edad, String genero, String telefono, String correo, String contrasena, double sueldo) {
        super(documento, nombre, apellido, edad, genero, telefono, correo, contrasena, sueldo);
    }
}