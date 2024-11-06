package servicios;

import modelos.Cliente;
import modelos.Tiquete;
import modelos.Viaje;
import servicios.persistencia.ServicioTiqueteDatos;
import util.Lista;
import util.interfaces.ILista;

public class ServicioTiquete {
    private ILista<Tiquete> tiquetes;
    private ServicioTiqueteDatos servicioTiqueteDatos;

    public ServicioTiquete() {
        this.tiquetes = new Lista<Tiquete>();
        this.servicioTiqueteDatos = new ServicioTiqueteDatos("DatosTiquetes.bin");
        this.cargarDatos();
    }

    public void agregarTiquete(String idTiquete, Viaje viaje, Cliente cliente) throws RuntimeException {
        // Verificar que el tiquete no exista en la lista
        if (buscarTiquete(viaje, cliente)) {
            throw new RuntimeException("El tiquete ya está registrado.");
        }

        // Agrega el tiquete a la lista
        Tiquete tiquete = new Tiquete(idTiquete, viaje, cliente);
        tiquetes.add(tiquete);
        this.agregarDatos();
    }

    public void eliminarTiquete(String idTiquete) throws RuntimeException {
        // Verifica si el tiquete está en la lista antes de eliminarlo
        int indice = obtenerIndiceTiquete(idTiquete);
        if (indice == -1) {
            throw new RuntimeException("El tiquete no fue encontrado.");
        }

        // Elimina el tiquete con el índice encontrado
        this.tiquetes.remove(indice);
        this.agregarDatos();
    }

    public void actualizarTiquete(String idTiquete, Viaje nuevoViaje, Cliente nuevoCliente) throws RuntimeException {
        int indice = obtenerIndiceTiquete(idTiquete);
        if (indice == -1) {
            throw new RuntimeException("El tiquete no fue encontrado.");
        }

        // Asigna los valores correspondientes
        Tiquete tiquete = this.tiquetes.get(indice);
        tiquete.setViaje(nuevoViaje);
        tiquete.setCliente(nuevoCliente);
        this.agregarDatos();
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

    private int obtenerIndiceTiquete(String idTiquete) {
        for (int i = 0; i < this.tiquetes.size(); i++) {
            if (this.tiquetes.get(i).getIdTiquete().equals(idTiquete)) {
                return i;
            }
        }
        return -1;
    }

    private void agregarDatos() {
        try {
            this.servicioTiqueteDatos.agregarTiquetesArchivo(this.tiquetes);
        } catch (Exception e) {
            System.out.println("Error al agregar datos: " + e.getMessage());
        }
    }

    private void cargarDatos(){
        this.tiquetes = this.servicioTiqueteDatos.cargarTiquetesArchivo();
    }

}
