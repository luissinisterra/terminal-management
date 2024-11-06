package servicios.persistencia;

import modelos.Tiquete;
import util.Lista;
import util.interfaces.ILista;

import java.io.*;

public class ServicioTiqueteDatos {
    private String ruta;

    public ServicioTiqueteDatos(String ruta) {
        this.ruta = ruta;
    }

    public void agregarTiquetesArchivo(ILista<Tiquete> tiquetes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(tiquetes);
            System.out.println("Tiquetes guardados en " + ruta);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ILista<Tiquete> cargarTiquetesArchivo() {
        File file = new File(ruta);

        if (!file.exists()) {
            System.out.println("Archivo no encontrado, creando...");
            ILista<Tiquete> tiquetes = new Lista<>();
            this.agregarTiquetesArchivo(tiquetes);
            return tiquetes;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (ILista<Tiquete>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new Lista<>();
        }
    }
}
