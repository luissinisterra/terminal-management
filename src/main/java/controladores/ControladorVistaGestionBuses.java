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

    //Metodos de buses
    public void agregarBus(String placa, int cantidadPuestos, String modelo, int año, String conductor) {
        this.servicioBus.agregarBus(placa, cantidadPuestos, modelo, año, conductor);
    }

    public void eliminarBus(String placa) {
        this.servicioBus.eliminarBus(placa);
    }

    public void actualizarBus(String placa, int nuevaCantidadPuestos, String modelo, int año, String conductor) {
        this.servicioBus.actualizarBus(placa, nuevaCantidadPuestos, modelo, año, conductor);
    }

    //Metodo de casetas
    public Caseta[][] obtenerCasetas(){
        return this.servicioCaseta.obtenerCasetas();
    }

    public void asignarCaseta(int fila, int columna, Caseta caseta) {
        this.servicioCaseta.asignarCaseta(fila, columna, caseta);
    }

}
