package controladores;

import modelos.Caseta;
import servicios.ServicioCaseta;

public class ControladorVistaPrincipal {
    private ServicioCaseta servicioCaseta;

    public ControladorVistaPrincipal() {
        this.servicioCaseta = new ServicioCaseta();
    }

    public Caseta obtenerCaseta(int fila, int columna) {
        return servicioCaseta.entregarCaseta(fila, columna);
    }

    public void asignarCaseta(int fila, int columna, Caseta caseta) {
        servicioCaseta.asignarCaseta(fila, columna, caseta);
    }
}
