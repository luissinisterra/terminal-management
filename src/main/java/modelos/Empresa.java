package modelos;

import util.Lista;
import util.interfaces.ILista;

import java.io.Serializable;

public class Empresa implements Serializable {
    private String nit;
    private String nombreEmpresa;
    private AdministradorFlota administradorFlota;
    private ILista<Bus> buses;
    private ILista<Viaje> viajes;
    private static final long serialVersionUID = 1L;

    public Empresa(String nit, String nombreEmpresa, AdministradorFlota administradorFlota) {
        this.nit = nit;
        this.nombreEmpresa = nombreEmpresa;
        this.administradorFlota = administradorFlota;
        this.buses = new Lista<>();
        this.viajes = new Lista<>();
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public AdministradorFlota getAdministradorFlota() {
        return administradorFlota;
    }

    public void setAdministradorFlota(AdministradorFlota administradorFlota) {
        this.administradorFlota = administradorFlota;
    }

    public ILista<Bus> getBuses() {
        return buses;
    }

    public void setBuses(ILista<Bus> buses) {
        this.buses = buses;
    }

    public ILista<Viaje> getViajes() {
        return viajes;
    }

    public void setViajes(ILista<Viaje> viajes) {
        this.viajes = viajes;
    }

}
