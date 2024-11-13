package modelos;

import java.io.Serializable;

public class Bus implements Serializable {
    private String placa;
    private int cantidadPuestos;
    private boolean disponibilidad;
    private String modelo;
    private int año;
    //private String marca;
    private String nombreConductor;

    public Bus(String placa, int cantidadPuestos, String modelo, int año, String nombreConductor) {
        this.placa = placa;
        this.cantidadPuestos = cantidadPuestos;
        this.disponibilidad = true;
        this.modelo = modelo;
        this.año = año;
        this.nombreConductor = nombreConductor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getCantidadPuestos() {
        return cantidadPuestos;
    }

    public void setCantidadPuestos(int cantidadPuestos) {
        this.cantidadPuestos = cantidadPuestos;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getNombreConductor() {
        return nombreConductor;
    }

    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "placa='" + placa + '\'' +
                ", cantidadPuestos=" + cantidadPuestos +
                ", disponibilidad=" + disponibilidad +
                ", modelo='" + modelo + '\'' +
                ", año=" + año +
                ", conductor='" + nombreConductor + '\'' +
                '}';
    }
}
