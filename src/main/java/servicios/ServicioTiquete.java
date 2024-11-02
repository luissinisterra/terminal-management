package servicios;

import modelos.Cliente;
import modelos.Tiquete;
import modelos.Viaje;
import util.Lista;
import util.interfaces.ILista;

public class ServicioTiquete {
    private ILista<Tiquete> tiquetes;

    public ServicioTiquete() {
        this.tiquetes = new Lista<Tiquete>();
    }

    public void agregarTiquete(Viaje viaje, Cliente cliente) throws RuntimeException {
        // Verificar que el tiquete no exista en la lista
        if (buscarTiquete(viaje, cliente)) {
            throw new RuntimeException("El tiquete ya está registrado.");
        }

        // Agrega el tiquete a la lista
        Tiquete tiquete = new Tiquete(viaje, cliente);
        tiquetes.add(tiquete);
    }

    public void eliminarTiquete(int idTiquete) throws RuntimeException {
        // Verifica si el tiquete está en la lista antes de eliminarlo
        int indice = obtenerIndiceTiquete(idTiquete);
        if (indice == -1) {
            throw new RuntimeException("El tiquete no fue encontrado.");
        }

        // Elimina el tiquete con el índice encontrado
        this.tiquetes.remove(indice);
    }

    public void actualizarTiquete(int idTiquete, Viaje nuevoViaje, Cliente nuevoCliente) throws RuntimeException {
        int indice = obtenerIndiceTiquete(idTiquete);
        if (indice == -1) {
            throw new RuntimeException("El tiquete no fue encontrado.");
        }

        // Asigna los valores correspondientes
        Tiquete tiquete = this.tiquetes.get(indice);
        tiquete.setViaje(nuevoViaje);
        tiquete.setCliente(nuevoCliente);
    }

    public ILista<Tiquete> obtenerTiquetes() throws RuntimeException {
        return this.tiquetes;
    }

    private boolean buscarTiquete(Viaje viaje, Cliente cliente) {
        for (int i = 0; i < this.tiquetes.size(); i++) {
            Tiquete tiquete = this.tiquetes.get(i);
            if (tiquete.getViaje().equals(viaje) && tiquete.getCliente().equals(cliente)) {
                return true;
            }
        }
        return false;
    }

    private int obtenerIndiceTiquete(int idTiquete) {
        for (int i = 0; i < this.tiquetes.size(); i++) {
            if (this.tiquetes.get(i).getIdTiquete() == idTiquete) {
                return i;
            }
        }
        return -1;
    }
}
