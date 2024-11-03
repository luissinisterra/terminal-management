package servicios.persistencia;

import modelos.AdministradorTerminal;
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
            ILista<Usuario> usuarios = new Lista<>();
            usuarios.add(new AdministradorTerminal("1111669729", "Luis", "Sinisterra", 18, "Masculino", "3184130924", "luis@", "123", 1350000));
            agregarUsuariosArchivo(usuarios);
            return usuarios;
        }

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file) )) {
            return (ILista<Usuario>) ois.readObject();
        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            return new Lista<>();
        }

    }

}
