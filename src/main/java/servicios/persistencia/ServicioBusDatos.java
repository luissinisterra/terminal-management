package servicios.persistencia;

import modelos.Bus;
import util.Lista;
import util.interfaces.ILista;

import java.io.*;

public class ServicioBusDatos {
    private String ruta;
    public ServicioBusDatos(String ruta) {
        this.ruta = ruta;
    }

    public void agregarBusesArchivo(ILista<Bus> buses){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(buses);
            System.out.println("Buses guardados en " + ruta);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public ILista<Bus> cargarBusesArchivo(){
        File file = new File(ruta);

        if(!file.exists()){
            System.out.println("Archivo no encontrotado, creando...");
            ILista<Bus> buses = new Lista<>();
            this.agregarBusesArchivo(buses);
            return buses;
        }

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file) )) {
            return (ILista<Bus>) ois.readObject();
        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            return new Lista<>();
        }
    }
}
