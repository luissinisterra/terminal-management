package servicios;

import modelos.Cliente;
import modelos.Venta;
import modelos.Viaje;
import util.Lista;
import util.interfaces.ILista;

import java.time.LocalDate;

public class ServicioVenta {
    private ILista<Venta> ventas;

    public ServicioVenta() {
        this.ventas = new Lista<Venta>();
    }

    public void agregarVenta(Viaje viaje, Cliente cliente, int cantidadTiquetes, LocalDate fecha) throws RuntimeException {
        // Verificar que la venta no exista en la lista
        if (buscarVenta(viaje, cliente, fecha)) {
            throw new RuntimeException("La venta ya está registrada.");
        }

        // Agrega la venta a la lista
        Venta venta = new Venta(viaje, cliente, cantidadTiquetes, fecha);
        ventas.add(venta);
    }

    public void eliminarVenta(int index) throws RuntimeException {
        // Verifica si el índice es válido antes de eliminar
        if (index < 0 || index >= ventas.size()) {
            throw new RuntimeException("La venta no fue encontrada.");
        }

        // Elimina la venta con el índice encontrado
        this.ventas.remove(index);
    }

    public void actualizarVenta(int index, Viaje nuevoViaje, Cliente nuevoCliente, int nuevaCantidadTiquetes, LocalDate nuevaFecha) throws RuntimeException {
        // Verifica si el índice es válido
        if (index < 0 || index >= ventas.size()) {
            throw new RuntimeException("La venta no fue encontrada.");
        }

        // Asigna los valores correspondientes
        Venta venta = this.ventas.get(index);
        venta.setViaje(nuevoViaje);
        venta.setCliente(nuevoCliente);
        venta.setCantidadTiquetes(nuevaCantidadTiquetes);
        venta.setFecha(nuevaFecha);
    }

    public ILista<Venta> obtenerVentas() throws RuntimeException {
        return this.ventas;
    }

    private boolean buscarVenta(Viaje viaje, Cliente cliente, LocalDate fecha) {
        for (int i = 0; i < this.ventas.size(); i++) {
            Venta venta = this.ventas.get(i);
            if (venta.getViaje().equals(viaje) && venta.getCliente().equals(cliente) && venta.getFecha().equals(fecha)) {
                return true;
            }
        }
        return false;
    }
}
