package servicios.persistencia;

import modelos.Viaje;
import util.Lista;
import util.interfaces.ILista;

import java.io.*;

public class ServicioViajeDatos {
    private String ruta;

    public ServicioViajeDatos(String ruta) {
        this.ruta = ruta;
    }

    public void agregarViajesArchivo(ILista<Viaje> viajes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(viajes);
            System.out.println("Viajes guardados en " + ruta);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ILista<Viaje> cargarViajesArchivo() {
        File file = new File(ruta);

        if (!file.exists()) {
            System.out.println("Archivo no encontrado, creando uno nuevo...");
            ILista<Viaje> viajes = new Lista<>();
            this.agregarViajesArchivo(viajes);
            return viajes;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (ILista<Viaje>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new Lista<>();
        }
    }
}
