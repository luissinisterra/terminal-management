package servicios.persistencia;

import modelos.Caseta;
import java.io.*;

public class ServicioCasetaDatos {
    private String ruta;

    public ServicioCasetaDatos(String ruta) {
        this.ruta = ruta;
    }

    public void agregarCasetasArchivo(Caseta[][] casetas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(casetas);
            System.out.println("Casetas guardadas en " + ruta);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Caseta[][] cargarCasetasArchivo() {
        File file = new File(ruta);

        if (!file.exists()) {
            System.out.println("Archivo no encontrado, creando...");
            Caseta[][] casetasInicial = crearMatrizIrregular();
            this.agregarCasetasArchivo(casetasInicial);
            return casetasInicial; 
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Caseta[][]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return crearMatrizIrregular();
        }
    }

    private Caseta[][] crearMatrizIrregular() {
        Caseta[][] matrizIrregular = new Caseta[4][];

        matrizIrregular[0] = new Caseta[5];
        for (int i = 1; i < 4; i++) {
            matrizIrregular[i] = new Caseta[2];
        }

        /*for (int i = 0; i < matrizIrregular.length; i++) {
            for (int j = 0; j < matrizIrregular[i].length; j++) {
                matrizIrregular[i][j] = new Caseta();
            }
        }*/

        return matrizIrregular;
    }

}
