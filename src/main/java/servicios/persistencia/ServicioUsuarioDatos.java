package servicios.persistencia;

import modelos.Usuario;
import util.Lista;
import util.interfaces.ILista;

import java.io.*;

public class ServicioUsuarioDatos {
    private String ruta;
    public ServicioUsuarioDatos(String ruta) {
        this.ruta = ruta;
    }

    public void agregarUsuariosArchivo(ILista<Usuario> usuarios){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(usuarios);
            System.out.println("usuarios guardados en " + ruta);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public ILista<Usuario> cargarUsuariosArchivo(){
        File file = new File(ruta);
        if(!file.exists()){
            System.out.println("Archivo no encontrotado, creando...");
            agregarUsuariosArchivo(new Lista<>());
            return new Lista<>();
        }

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file) )) {
            return (ILista<Usuario>) ois.readObject();
        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            return new Lista<>();
        }

    }

}
