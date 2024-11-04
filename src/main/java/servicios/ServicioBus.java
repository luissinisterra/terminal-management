package servicios;

import modelos.Bus;
import servicios.persistencia.ServicioBusDatos;
import util.Lista;
import util.interfaces.ILista;

public class ServicioBus {
    private ILista <Bus> buses;
    private ServicioBusDatos servicioBusDatos;
    public ServicioBus() {
        this.buses = new Lista<Bus>();
        this.servicioBusDatos = new ServicioBusDatos("DatosBuses.bin");
        this.cargarDatos();
    }

    public void agregarBus(String placa, int cantidadPuestos) throws RuntimeException{
        //Verificar que la placa no exista en lista de buses
        if(buscarPlaca(placa)){
            throw new RuntimeException("La placa de este vehiculo ya esta registrada en la terminal.");
        }

        //Agrega el bus a la lista
        Bus bus = new Bus(placa, cantidadPuestos);
        this.buses.add(bus);
        this.agregarDatos();
    }

    public void eliminarBus(String placa) throws RuntimeException{
        //Verifica si la placa esta en la lista de buses pera luego eliminar el bus
        if(!buscarPlaca(placa)){
            throw new RuntimeException("La placa ingresada no fue encontrada en la terminal.");
        }

        //Elimina el bus con el indice encontrado
        int indice = obtenerIndiceBus(placa);
        this.buses.remove(indice);
        this.agregarDatos();
    }

    public void actualizarBus(String placa, int nuevaCantidadPuestos) throws RuntimeException{
        if(!buscarPlaca(placa)){
            throw new RuntimeException("La placa ingresada no fue encontrada en la terminal.");
        }

        //Asigna los valores correspondientes
        for(int i = 0; i < buses.size(); i++){
            if(this.buses.get(i).getPlaca().equals(placa)){
                this.buses.get(i).setCantidadPuestos(nuevaCantidadPuestos);
            }
        }
        this.agregarDatos();
    }

    public ILista<Bus> obtenerBuses() throws RuntimeException{
        return this.buses;
    }

    private boolean buscarPlaca(String placa){
        for(int i = 0; i < this.buses.size(); i++){
            if(this.buses.get(i).getPlaca().equals(placa)){
                return true;
            }
        }
        return false;
    }

    private int obtenerIndiceBus(String placa){
        for(int i = 0; i < this.buses.size(); i++){
            if(this.buses.get(i).getPlaca().equals(placa)){
                return i;
            }
        }
        return -1;
    }

    private void agregarDatos() {
        try {
            this.servicioBusDatos.agregarBusesArchivo(this.buses);
        } catch (Exception e) {
            System.out.println("Error al agregar datos: " + e.getMessage());
        }
    }

    private void cargarDatos(){
        this.buses = this.servicioBusDatos.cargarBusesArchivo();
    }

}
