package modelos;

import java.io.Serializable;

public class AdministradorFlota extends Usuario implements Serializable {
    private double sueldo;
    private static final long serialVersionUID = 1L;
    public AdministradorFlota(String documento, String nombre, String apellido, int edad, String genero, String telefono, String correo, String contrasena, double sueldo) {
        super(documento, nombre, apellido, edad, genero, telefono, correo, contrasena);
        this.sueldo = sueldo;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
}

