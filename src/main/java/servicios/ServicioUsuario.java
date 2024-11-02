package servicios;

import modelos.AdministradorFlota;
import modelos.AdministradorTerminal;
import modelos.Cliente;
import modelos.Usuario;
import servicios.persistencia.ServicioUsuarioDatos;
import util.Lista;
import util.interfaces.ILista;

public class ServicioUsuario {
    private ILista<Usuario> usuarios;
    private ServicioUsuarioDatos servicioUsuarioDatos;
    public ServicioUsuario() {
        this.usuarios = new Lista<>();
        this.servicioUsuarioDatos = new ServicioUsuarioDatos("DatosUsuarios.bin");
        this.usuarios.add(new AdministradorTerminal("1111669729", "Luis", "Sinisterra", 18, "Masculino", "3184130924", "luis@", "123", 1350000));
        //this.agregarDatos();
        //this.cargarDatos();
    }

    public void agregarAdmininistradorFlota(String documento, String nombre, String apellido, int edad, String genero, String telefono, String correo, String contrasena, double sueldo) throws RuntimeException {
        //Verifica que el documento no sea repetido
        if(buscarDocumento(documento)){
            throw new RuntimeException("El documento ingresado ya existe en la terminal");
        }

        AdministradorFlota adminFlota = new AdministradorFlota(documento, nombre, apellido, edad, genero, telefono, correo, contrasena, sueldo);
        this.usuarios.add(adminFlota);

        //this.agregarDatos();
        //this.cargarDatos();
    }

    public void agregarCliente(String documento, String nombre, String apellido, int edad, String genero, String telefono, String correo, String contrasena, double sueldo) throws RuntimeException {
        //Verifica que el documento no sea repetido
        if(buscarDocumento(documento)){
            throw new RuntimeException("El documento ingresado ya existe en la terminal");
        }

        Cliente cliente = new Cliente(documento, nombre, apellido, edad, genero, telefono, correo, contrasena, sueldo);
        this.usuarios.add(cliente);
        this.agregarDatos();
        //this.cargarDatos();
    }

    public void eliminarUsuario(String documento) throws RuntimeException {
        //Verifica que el documento este en la terminal
        if(!buscarDocumento(documento)){
            throw new RuntimeException("El documento ingresado no fue encontrado en la terminal");
        }

        int indice = obtenerIndiceUsuario(documento);
        this.usuarios.remove(indice);
        this.agregarDatos();
        //this.cargarDatos();
    }

    public void actualizarUsuario(String documento, String nombre, String apellido, int edad, String genero, String telefono, String correo, String contrasena, double sueldo) throws RuntimeException {
        //Verifica que el documento exista en la lista
        if(!buscarDocumento(documento)){
            throw new RuntimeException("El documento ingresado no fue encontrado en la terminal");
        }
        //Asigna los valores correspondientes
        for(int i = 0; i < this.usuarios.size(); i++){
            if(this.usuarios.get(i).getDocumento().equals(documento)){
                this.usuarios.get(i).setNombre(nombre);
                this.usuarios.get(i).setApellido(apellido);
                this.usuarios.get(i).setEdad(edad);
                this.usuarios.get(i).setGenero(genero);
                this.usuarios.get(i).setTelefono(telefono);
                this.usuarios.get(i).setCorreo(correo);
                this.usuarios.get(i).setContrasena(contrasena);
                this.usuarios.get(i).setSueldo(sueldo);
            }
        }
        this.agregarDatos();
    }

    public Usuario buscarUsuario(String documento, String contrasena) throws RuntimeException {
        //Verifica que el documento exista en la lista
        if(!buscarDocumento(documento)){
            throw new RuntimeException("El documento ingresado no existe en la terminal");
        }
        //Verifica que la contrasena sea correspondiente al documento ingresado
        if(!validarContrasena(documento, contrasena)){
            throw new RuntimeException("La contraseña ingresada no es correcta.");
        }

        //Busca el usuario con el documento y contasena ingresado para retonarlo
        for(int i = 0; i < this.usuarios.size(); i++){
            if(this.usuarios.get(i).getDocumento().equals(documento) && this.usuarios.get(i).getContrasena().equals(contrasena)){
                return this.usuarios.get(i);
            }
        }
        return null;
    }

    private boolean validarContrasena(String documento, String contrasena){
        for(int i = 0; i < this.usuarios.size(); i++){
            if(this.usuarios.get(i).getDocumento().equals(documento) && this.usuarios.get(i).getContrasena().equals(contrasena)){
                return true;
            }
        }
        return false;
    }

    private boolean buscarDocumento(String documento){
        for(int i = 0; i < this.usuarios.size(); i++){
            if(this.usuarios.get(i).getDocumento().equals(documento)){
                return true;
            }
        }
        return false;
    }

    private int obtenerIndiceUsuario(String documento){
        for(int i = 0; i < this.usuarios.size(); i++){
            if(this.usuarios.get(i).getDocumento().equals(documento)){
                return i;
            }
        }
        return -1;
    }

    private void agregarDatos() {
        try {
            this.servicioUsuarioDatos.agregarUsuariosArchivo(this.usuarios);
        } catch (Exception e) {
            System.out.println("Error al agregar datos: " + e.getMessage());
        }
    }

    private void cargarDatos(){
        this.usuarios = this.servicioUsuarioDatos.cargarUsuariosArchivo();
    }

    public ILista<Usuario> getAdmins() {
        return this.usuarios;
    }


}
