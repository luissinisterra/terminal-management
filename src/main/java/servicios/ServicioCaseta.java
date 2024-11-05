package servicios;

import modelos.Caseta;
import servicios.persistencia.ServicioCasetaDatos;

public class ServicioCaseta {
    private Caseta[][] casetas;
    private ServicioCasetaDatos servicioCasetaDatos;

    public ServicioCaseta() {
        this.crearMatrizIrregular();
        this.initCasetas();
        this.servicioCasetaDatos = new ServicioCasetaDatos("DatosCasetas.bin");
        this.cargarDatos();
    }

    public Caseta entregarCaseta(int fila, int columna) {
        return this.casetas[fila][columna];
    }

    public void asignarCaseta(int fila, int columna, Caseta caseta) {
        this.casetas[fila][columna] = caseta;
        this.agregarDatos();
    }

    public Caseta[][] obtenerCasetas() {
        return this.casetas;
    }

    private void agregarDatos() {
        try {
            this.servicioCasetaDatos.agregarCasetasArchivo(this.casetas);
            System.out.println("Datos de casetas guardados correctamente.");
        } catch (Exception e) {
            System.out.println("Error al guardar datos: " + e.getMessage());
        }
    }

    private void cargarDatos() {
        this.casetas = this.servicioCasetaDatos.cargarCasetasArchivo();
    }

    private void crearMatrizIrregular() {
        this.casetas = new Caseta[4][];
        this.casetas[0] = new Caseta[5];
        for (int i = 1; i < casetas.length; i++) {
            this.casetas[i] = new Caseta[2];
        }
    }

    private void initCasetas() {
        for (int i = 0; i < this.casetas.length; i++) {
            for (int j = 0; j < this.casetas[i].length; j++) {
                if (this.casetas[i][j] == null) {
                    this.casetas[i][j] = new Caseta();
                }
            }
        }
    }

}
