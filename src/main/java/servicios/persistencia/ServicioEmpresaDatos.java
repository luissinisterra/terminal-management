package servicios.persistencia;

import modelos.Empresa;
import util.Lista;
import util.interfaces.ILista;

import java.io.*;

public class ServicioEmpresaDatos {
    private String ruta;

    public ServicioEmpresaDatos(String ruta) {
        this.ruta = ruta;
    }

    // Método para agregar empresas al archivo
    public void agregarEmpresaArchivo(ILista<Empresa> empresas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(empresas);
            System.out.println("Empresas guardadas en " + ruta);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar empresas desde el archivo
    public ILista<Empresa> cargarEmpresaArchivo() {
        File file = new File(ruta);

        if (!file.exists()) {
            System.out.println("Archivo no encontrado, creando...");
            ILista<Empresa> empresas = new Lista<>();
            this.agregarEmpresaArchivo(empresas);
            return empresas;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (ILista<Empresa>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new Lista<>();
        }
    }
}
