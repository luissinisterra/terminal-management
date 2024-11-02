package modelos;

public class Tiquete {
    private int idTiquete;
    private Viaje viaje;
    private Cliente cliente;

    public Tiquete(Viaje viaje, Cliente cliente) {
        this.viaje = viaje;
        this.cliente = cliente;
    }

    public int getIdTiquete() {
        return idTiquete;
    }

    public void setIdTiquete(int idTiquete) {
        this.idTiquete = idTiquete;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}

