package servicios.persistencia;

import modelos.Reserva;
import util.Lista;
import util.interfaces.ILista;

import java.io.*;

public class ServicioReservaDatos {
    private String ruta;

    public ServicioReservaDatos(String ruta) {
        this.ruta = ruta;
    }

    public void agregarReservasArchivo(ILista<Reserva> reservas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(reservas);
            System.out.println("Reservas guardadas en " + ruta);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ILista<Reserva> cargarReservasArchivo() {
        File file = new File(ruta);

        if (!file.exists()) {
            System.out.println("Archivo no encontrado, creando uno nuevo...");
            ILista<Reserva> reservas = new Lista<>();
            this.agregarReservasArchivo(reservas);
            return reservas;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (ILista<Reserva>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new Lista<>();
        }
    }
}
