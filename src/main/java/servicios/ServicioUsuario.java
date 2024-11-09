package servicios;

import modelos.*;
import servicios.persistencia.ServicioUsuarioDatos;
import util.Lista;
import util.interfaces.ILista;

public class ServicioUsuario {
    private ILista<Usuario> usuarios;
    private ServicioUsuarioDatos servicioUsuarioDatos;
    public ServicioUsuario() {
        this.servicioUsuarioDatos = new ServicioUsuarioDatos("DatosUsuarios.bin");
        this.usuarios = new Lista<>();
        this.cargarDatos();
    }

    public void agregarAdmininistradorTerminal(String documento, String nombre, String apellido, int edad, String genero, String telefono, String correo, String contrasena, double sueldo) throws RuntimeException {
        //Verifica que el documento no sea repetido
        if(buscarDocumento(documento)){
            throw new RuntimeException("El documento ingresado ya existe en la terminal");
        }

        AdministradorTerminal adminTerminal = new AdministradorTerminal(documento, nombre, apellido, edad, genero, telefono, correo, contrasena, sueldo);
        this.usuarios.add(adminTerminal);

        this.agregarDatos();
    }

    public void agregarAdmininistradorFlota(String documento, String nombre, String apellido, int edad, String genero, String telefono, String correo, String contrasena, double sueldo) throws RuntimeException {
        //Verifica que el documento no sea repetido
        if(buscarDocumento(documento)){
            throw new RuntimeException("El documento ingresado ya existe en la terminal");
        }

        AdministradorFlota adminFlota = new AdministradorFlota(documento, nombre, apellido, edad, genero, telefono, correo, contrasena, sueldo);
        this.usuarios.add(adminFlota);

        this.agregarDatos();
    }

    public void agregarCliente(String documento, String nombre, String apellido, int edad, String genero, String telefono, String correo, String contrasena, double sueldo) throws RuntimeException {
        //Verifica que el documento no sea repetido
        if(buscarDocumento(documento)){
            throw new RuntimeException("El documento ingresado ya existe en la terminal");
        }

        Cliente cliente = new Cliente(documento, nombre, apellido, edad, genero, telefono, correo, contrasena, sueldo);
        this.usuarios.add(cliente);
        this.agregarDatos();
    }

    public void eliminarUsuario(String documento) throws RuntimeException {
        //Verifica que el documento este en la terminal
        if(!buscarDocumento(documento)){
            throw new RuntimeException("El documento ingresado no fue encontrado en la terminal");
        }

        int indice = obtenerIndiceUsuario(documento);
        this.usuarios.remove(indice);
        this.agregarDatos();
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

    public ILista<Usuario> obtenerUsuarios() throws RuntimeException {
        return this.usuarios;
    }

    public Cliente obtenerClientePorId(String idCliente) throws RuntimeException {
        for (int i = 0; i < this.usuarios.size(); i++) {
            if (this.usuarios.get(i) instanceof Cliente) {
                Cliente cliente = (Cliente) this.usuarios.get(i);
                return cliente;
            }
        }
        throw new RuntimeException("El documento ingresado no fue encontrado en la terminal");
    }

    public void agregarTiqueteCliente(String idCliente, Tiquete tiquete) throws RuntimeException {
        int indice = this.obtenerIndiceUsuario(idCliente);

        if (indice == -1) {
            throw new RuntimeException("El documento ingresado no existe en la terminal");
        }

        if (this.usuarios.get(indice) != null) {
            ((Cliente) this.usuarios.get(indice)).getTiquetes().add(tiquete);
            ((Cliente) this.usuarios.get(indice)).actualizarPuntos();
            return;
        } else {
            throw new RuntimeException("El cliente con ese Id no se ha encontrado.");
        }
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

    private int obtenerIndiceUsuario(String documento) {
        for (int i = 0; i < this.usuarios.size(); i++) {
            if (this.usuarios.get(i).getDocumento().equals(documento)) {
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
}
