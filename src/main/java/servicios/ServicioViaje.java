package servicios;

import modelos.Bus;
import modelos.Viaje;
import util.Lista;
import util.interfaces.ILista;

import java.time.LocalDateTime;

public class ServicioViaje {
    private ILista<Viaje> viajes;

    public ServicioViaje() {
        this.viajes = new Lista<Viaje>();
    }

    public void agregarViaje(int idViaje, String origen, String destino, LocalDateTime horaSalida, LocalDateTime horaLlegada, Bus bus) throws RuntimeException {
        // Verificar que el viaje no exista en la lista
        if (buscarViaje(idViaje)) {
            throw new RuntimeException("El viaje ya está registrado.");
        }

        // Agrega el viaje a la lista
        Viaje viaje = new Viaje(idViaje, origen, destino, horaSalida, horaLlegada, bus);
        viajes.add(viaje);
    }

    public void eliminarViaje(int idViaje) throws RuntimeException {
        // Verifica si el viaje está en la lista antes de eliminarlo
        int indice = obtenerIndiceViaje(idViaje);
        if (indice == -1) {
            throw new RuntimeException("El viaje no fue encontrado.");
        }

        // Elimina el viaje con el índice encontrado
        this.viajes.remove(indice);
    }

    public void actualizarViaje(int idViaje, String nuevoOrigen, String nuevoDestino, LocalDateTime nuevaHoraSalida, LocalDateTime nuevaHoraLlegada, Bus nuevoBus) throws RuntimeException {
        int indice = obtenerIndiceViaje(idViaje);
        if (indice == -1) {
            throw new RuntimeException("El viaje no fue encontrado.");
        }

        // Asigna los valores correspondientes
        Viaje viaje = this.viajes.get(indice);
        viaje.setOrigen(nuevoOrigen);
        viaje.setDestino(nuevoDestino);
        viaje.setHoraSalida(nuevaHoraSalida);
        viaje.setHoraLlegada(nuevaHoraLlegada);
        viaje.setBus(nuevoBus);
    }

    public ILista<Viaje> obtenerViajes() throws RuntimeException {
        return this.viajes;
    }

    private boolean buscarViaje(int idViaje) {
        for (int i = 0; i < this.viajes.size(); i++) {
            if (this.viajes.get(i).getIdViaje() == idViaje) {
                return true;
            }
        }
        return false;
    }

    private int obtenerIndiceViaje(int idViaje) {
        for (int i = 0; i < this.viajes.size(); i++) {
            if (this.viajes.get(i).getIdViaje() == idViaje) {
                return i;
            }
        }
        return -1;
    }
}
