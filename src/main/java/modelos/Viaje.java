package modelos;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Viaje implements Serializable {
    private String idViaje;
    private String origen;
    private String destino;
    private LocalDateTime fechaHoraSalida;
    private LocalDateTime fechaHoraLlegada;
    private LocalTime fechaCreacion;
    private Bus bus;
    private int cupos;
    private int valorUnitario;

    public Viaje(String idViaje, String origen, String destino, LocalDateTime fechaHoraSalida, LocalDateTime fechaHoraLlegada, Bus bus, int valorUnitario) {
        this.idViaje = idViaje;
        this.origen = origen;
        this.destino = destino;
        this.fechaHoraSalida = fechaHoraSalida;
        this.fechaHoraLlegada = fechaHoraLlegada;
        this.fechaCreacion = LocalTime.now();
        this.bus = bus;
        this.cupos = this.bus.getCantidadPuestos();
        this.valorUnitario = valorUnitario;
    }

    public String getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(String idViaje) {
        this.idViaje = idViaje;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDateTime getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public LocalDateTime getFechaHoraLlegada() {
        return fechaHoraLlegada;
    }

    public void setFechaHoraLlegada(LocalDateTime fechaHoraLlegada) {
        this.fechaHoraLlegada = fechaHoraLlegada;
    }

    public LocalTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public int getCupos() {
        return cupos;
    }

    public void setCupos(int cupos) {
        this.cupos = cupos;
    }

    public int getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(int valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

}
