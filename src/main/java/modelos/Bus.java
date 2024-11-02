package modelos;

public class Bus {
    private String placa;
    private int cantidadPuestos;
    private boolean disponibilidad;

    public Bus(String placa, int cantidadPuestos) {
        this.placa = placa;
        this.cantidadPuestos = cantidadPuestos;
        this.disponibilidad = true;
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

}
