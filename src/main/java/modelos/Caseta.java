package modelos;

import util.interfaces.ILista;

public class Caseta {
    private Empresa empresa;
    private double canonArrendamiento;
    private int plazasEstacionamiento;
    private boolean disponibilidad;

    public Caseta() {}

    public Caseta(Empresa empresa, double canonArrendamiento, int plazasEstacionamiento) {
        this.empresa = empresa;
        this.canonArrendamiento = canonArrendamiento;
        this.plazasEstacionamiento = plazasEstacionamiento;
        this.disponibilidad = true;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public double getCanonArrendamiento() {
        return canonArrendamiento;
    }

    public void setCanonArrendamiento(double canonArrendamiento) {
        this.canonArrendamiento = canonArrendamiento;
    }

    public int getPlazasEstacionamiento() {
        return plazasEstacionamiento;
    }

    public void setPlazasEstacionamiento(int plazasEstacionamiento) {
        this.plazasEstacionamiento = plazasEstacionamiento;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    private void validarDisponibilidad() {
        if(this.empresa != null) {
            this.disponibilidad = false;
        }
    }

}

