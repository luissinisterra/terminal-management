package controladores;

import modelos.Caseta;
import servicios.ServicioBus;
import servicios.ServicioCaseta;

public class ControladorVistaGestionBuses {
    private ServicioBus servicioBus;
    private ServicioCaseta servicioCaseta;

    public ControladorVistaGestionBuses() {
        this.servicioBus = new ServicioBus();
        this.servicioCaseta = new ServicioCaseta();
    }

    public void agregarBus(String placa, int cantidadPuestos) {
        this.servicioBus.agregarBus(placa, cantidadPuestos);
    }

    public void eliminarBus(String placa) {
        this.servicioBus.eliminarBus(placa);
    }

    public void actualizarBus(String placa, int nuevaCantidadPuestos) {
        this.servicioBus.actualizarBus(placa, nuevaCantidadPuestos);
    }

    //Metodo de casetas
    public Caseta[][] obtenerCasetas(){
        return this.servicioCaseta.obtenerCasetas();
    }

    public void asignarCaseta(int fila, int columna, Caseta caseta) {
        this.servicioCaseta.asignarCaseta(fila, columna, caseta);
    }

}
