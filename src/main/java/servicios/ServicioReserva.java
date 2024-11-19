package servicios;

import modelos.Cliente;
import modelos.Reserva;
import modelos.Viaje;
import servicios.persistencia.ServicioReservaDatos;
import servicios.persistencia.ServicioTiqueteDatos;
import util.Lista;
import util.interfaces.ILista;

public class ServicioReserva {
    private ILista<Reserva> reservas;
    private ServicioReservaDatos servicioReservaDatos;
    private ServicioTiquete servicioTiquete;

    public ServicioReserva() {
        this.reservas = new Lista<>();
        this.servicioReservaDatos = new ServicioReservaDatos("DatosReservas.bin");
        this.servicioTiquete = new ServicioTiquete();
        this.cargarDatos();
    }

    public void agregarReserva(String idReserva, Viaje viaje, Cliente cliente) throws RuntimeException {
        // Verificar que el id del viaje no exista en la lista de reservas
        if (buscarIdReservaParaTiquete(idReserva)) {
            throw new RuntimeException("El ID de la reserva para tiquete ya está registrado.");
        }

        // Agregar la reserva a la lista
        Reserva reserva = new Reserva(idReserva, viaje, cliente);
        this.reservas.add(reserva);
        this.agregarDatos();
    }

    public void eliminarReserva(String idReserva) throws RuntimeException {
        // Verifica si el ID de la reserva está en la lista para luego eliminarlo
        if (!buscarIdReserva(idReserva)) {
            throw new RuntimeException("El ID de la reserva no fue encontrado.");
        }

        // Elimina la reserva con el índice encontrado
        int indice = obtenerIndiceReserva(idReserva);
        this.reservas.remove(indice);
        this.agregarDatos();
    }

    public void actualizarReserva(String idReserva, Viaje nuevoViaje) throws RuntimeException {
        if (!buscarIdReserva(idReserva)) {
            throw new RuntimeException("El ID de la reserva no fue encontrado.");
        }

        // Actualizar los datos del viaje
        for (int i = 0; i < reservas.size(); i++) {
            if (this.reservas.get(i).getIdReserva().equals(idReserva)) {
                this.reservas.get(i).setViaje(nuevoViaje);
            }
        }
        this.agregarDatos();
    }

    public boolean buscarReservaPorId(String idReserva) {
        return this.buscarIdReserva(idReserva);
    }

    public ILista<Reserva> obtenerReservas() {
        return this.reservas;
    }

    private boolean buscarIdReserva(String idReserva) {
        for (int i = 0; i < this.reservas.size(); i++) {
            if (this.reservas.get(i).getIdReserva().equals(idReserva)) {
                return true;
            }
        }
        return false;
    }

    private boolean buscarIdReservaParaTiquete(String idReserva) {
        for (int i = 0; i < this.servicioTiquete.obtenerTiquetes().size(); i++) {
            if (this.servicioTiquete.obtenerTiquetes().get(i).getIdTiquete().equals(idReserva)) {
                return true;
            }
        }
        return false;
    }

    private int obtenerIndiceReserva(String idReserva) {
        for (int i = 0; i < this.reservas.size(); i++) {
            if (this.reservas.get(i).getIdReserva().equals(idReserva)) {
                return i;
            }
        }
        return -1;
    }

    private void agregarDatos() {
        try {
            this.servicioReservaDatos.agregarReservasArchivo(this.reservas);
        } catch (Exception e) {
            System.out.println("Error al agregar datos: " + e.getMessage());
        }
    }

    private void cargarDatos() {
        this.reservas = this.servicioReservaDatos.cargarReservasArchivo();
    }
}
