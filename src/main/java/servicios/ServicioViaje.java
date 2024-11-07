package servicios;

import modelos.Bus;
import modelos.Caseta;
import modelos.Viaje;
import servicios.persistencia.ServicioViajeDatos;
import util.Lista;
import util.interfaces.ILista;

import java.time.LocalDateTime;

public class ServicioViaje {
    private ILista<Viaje> viajes;
    private ServicioViajeDatos servicioViajeDatos;

    public ServicioViaje() {
        this.viajes = new Lista<>();
        this.servicioViajeDatos = new ServicioViajeDatos("DatosViajes.bin");
        this.cargarDatos();
    }

    public void agregarViaje(String idViaje, String origen, String destino, LocalDateTime fechaHoraSalida, LocalDateTime fechaHoraLlegada, Bus bus, int valorUnitario) throws RuntimeException {
        // Verificar que el id del viaje no exista en la lista de viajes
        if (buscarIdViaje(idViaje)) {
            throw new RuntimeException("El ID del viaje ya está registrado.");
        }

        if(buscarBusEnViajes(bus) != null){
            Viaje viaje = buscarBusEnViajes(bus);
            if(this.validarBusEnTranscurso(fechaHoraSalida, fechaHoraLlegada, viaje)){
                throw new RuntimeException("El bus seleccionado se encuentra en viaje actualmente.");
            }
        }

        if(buscarBusEnViajes(bus) != null){
            Viaje vb = buscarBusEnViajes(bus);
            if(validarFechaDeViaje(fechaHoraSalida, fechaHoraLlegada, vb)){
                // Agregar el viaje a la lista
                Viaje viaje = new Viaje(idViaje, origen, destino, fechaHoraSalida, fechaHoraLlegada, bus, valorUnitario);
                this.viajes.add(viaje);
                this.agregarDatos();
            }else {
                throw new RuntimeException("El Bus esta ocupado durante esa fecha.");
            }
        } else {
            Viaje viaje = new Viaje(idViaje, origen, destino, fechaHoraSalida, fechaHoraLlegada, bus, valorUnitario);
            this.viajes.add(viaje);
            this.agregarDatos();
        }
    }

    public void eliminarViaje(String idViaje) throws RuntimeException {
        // Verifica si el ID del viaje está en la lista para luego eliminarlo
        if (!buscarIdViaje(idViaje)) {
            throw new RuntimeException("El ID del viaje no fue encontrado.");
        }

        if(obtenerViajePorIdViaje(idViaje) != null && obtenerViajePorIdViaje(idViaje).getCupos() != obtenerViajePorIdViaje(idViaje).getBus().getCantidadPuestos()){
            throw new RuntimeException("El viaje posee puestos ocupados.");
        }

        // Elimina el viaje con el índice encontrado
        int indice = obtenerIndiceViaje(idViaje);
        this.viajes.remove(indice);
        this.agregarDatos();
    }

    public void actualizarViaje(String idViaje, String nuevoDestino, LocalDateTime nuevaFechaHoraLlegada, int valorUnitario) throws RuntimeException {
        if (!buscarIdViaje(idViaje)) {
            throw new RuntimeException("El ID del viaje no fue encontrado.");
        }

        if(obtenerViajePorIdViaje(idViaje) != null && obtenerViajePorIdViaje(idViaje).getCupos() != obtenerViajePorIdViaje(idViaje).getBus().getCantidadPuestos()){
            throw new RuntimeException("El viaje posee puestos ocupados.");
        }

        // Actualizar los datos del viaje
        for (int i = 0; i < viajes.size(); i++) {
            if (this.viajes.get(i).getIdViaje().equals(idViaje)) {
                this.viajes.get(i).setDestino(nuevoDestino);
                this.viajes.get(i).setFechaHoraLlegada(nuevaFechaHoraLlegada);
                this.viajes.get(i).setValorUnitario(valorUnitario);
            }
        }
        this.agregarDatos();
    }

    public ILista<Viaje> obtenerViajes() throws RuntimeException {
        return this.viajes;
    }

    public Bus obtenerBusPorId(String placa, Caseta caseta) throws RuntimeException {
        for (int i = 0; i < caseta.getEmpresa().getBuses().size(); i++) {
            if (caseta.getEmpresa().getBuses().get(i).getPlaca().equals(placa)) {
                Bus bus = caseta.getEmpresa().getBuses().get(i);
                bus.setDisponibilidad(false);
                return bus;
            }
        }
        throw new RuntimeException("El ID del bus no fue encontrado.");
    }

    public boolean validarBusEnTranscurso(LocalDateTime fechaHoraSalida, LocalDateTime fechaHoraLlegada, Viaje viaje) throws RuntimeException {
        LocalDateTime fechaHoraSalidaViaje = viaje.getFechaHoraSalida();
        LocalDateTime fechaHoraLlegadaViaje = viaje.getFechaHoraLlegada();
        LocalDateTime fechaActual = LocalDateTime.now();

        if (fechaActual.isAfter(fechaHoraSalidaViaje) && fechaActual.isBefore(fechaHoraLlegadaViaje)) {
            return true;
        }
        return false;
    }

    private boolean buscarIdViaje(String idViaje) {
        for (int i = 0; i < this.viajes.size(); i++) {
            if (this.viajes.get(i).getIdViaje().equals(idViaje)) {
                return true;
            }
        }
        return false;
    }

    private Viaje obtenerViajePorIdViaje(String idViaje) {
        for (int i = 0; i < this.viajes.size(); i++) {
            if (this.viajes.get(i).getIdViaje().equals(idViaje)) {
                Viaje viaje = this.viajes.get(i);
                return viaje;
            }
        }
        return null;
    }

    private int obtenerIndiceViaje(String idViaje) {
        for (int i = 0; i < this.viajes.size(); i++) {
            if (this.viajes.get(i).getIdViaje().equals(idViaje)) {
                return i;
            }
        }
        return -1;
    }

    private void agregarDatos() {
        try {
            this.servicioViajeDatos.agregarViajesArchivo(this.viajes);
        } catch (Exception e) {
            System.out.println("Error al agregar datos: " + e.getMessage());
        }
    }

    private void cargarDatos() {
        this.viajes = this.servicioViajeDatos.cargarViajesArchivo();
    }

    private Viaje buscarBusEnViajes(Bus bus) {
        for (int i = 0; i < this.viajes.size(); i++) {
            if (this.viajes.get(i).getBus().getPlaca().equals(bus.getPlaca())) {
                return this.viajes.get(i);
            }
        }
        return null;
    }

    private boolean validarFechaDeViaje(LocalDateTime fechaHoraSalida, LocalDateTime fechaHoraLlegada, Viaje viaje) {
        LocalDateTime fechaHoraSalidaViaje = viaje.getFechaHoraSalida();
        LocalDateTime fechaHoraLlegadaViaje = viaje.getFechaHoraLlegada();

        if (fechaHoraSalida.isAfter(fechaHoraLlegadaViaje.plusDays(1))) {
            return true;
        }

        if (fechaHoraLlegada.isBefore(fechaHoraSalidaViaje)) {
            return true;
        }

        return false;
    }


}
