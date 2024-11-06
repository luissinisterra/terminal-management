package servicios;

import modelos.Caseta;
import servicios.persistencia.ServicioCasetaDatos;

public class ServicioCaseta {
    private Caseta[][] casetas;
    private ServicioCasetaDatos servicioCasetaDatos;

    public ServicioCaseta() {
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

}
