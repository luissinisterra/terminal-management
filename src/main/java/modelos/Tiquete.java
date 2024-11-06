package modelos;

public class Tiquete {
    private String idTiquete;
    private Viaje viaje;
    private Cliente cliente;

    public Tiquete(String idTiquete, Viaje viaje, Cliente cliente) {
        this.idTiquete = idTiquete;
        this.viaje = viaje;
        this.cliente = cliente;
    }

    public String getIdTiquete() {
        return idTiquete;
    }

    public void setIdTiquete(String idTiquete) {
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

