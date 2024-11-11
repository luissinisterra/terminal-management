package servicios;

import modelos.AdministradorFlota;
import modelos.Empresa;
import servicios.persistencia.ServicioEmpresaDatos;
import util.Lista;
import util.interfaces.ILista;

public class ServicioEmpresa {
    private ILista<Empresa> empresas;
    private ServicioEmpresaDatos servicioEmpresaDatos;

    public ServicioEmpresa() {
        this.empresas = new Lista<>();
        this.servicioEmpresaDatos = new ServicioEmpresaDatos("DatosEmpresas.bin");
        this.cargarDatos();
    }

    // Agregar una nueva empresa
    public void agregarEmpresa(String nit, String nombreEmpresa, AdministradorFlota administradorFlota) throws RuntimeException {
        // Verificar que el NIT no exista en la lista de empresas
        if (buscarNit(nit)) {
            throw new RuntimeException("La empresa con este NIT ya está registrada.");
        }

        // Crear y agregar la empresa a la lista
        Empresa empresa = new Empresa(nit, nombreEmpresa, administradorFlota);
        this.empresas.add(empresa);
        this.agregarDatos();
    }

    // Eliminar una empresa por NIT
    public void eliminarEmpresa(String nit) throws RuntimeException {
        // Verificar si el NIT está en la lista de empresas
        if (!buscarNit(nit)) {
            throw new RuntimeException("La empresa con el NIT ingresado no fue encontrada.");
        }

        // Eliminar la empresa
        int indice = obtenerIndiceEmpresa(nit);
        this.empresas.remove(indice);
        this.agregarDatos();
    }

    // Actualizar los detalles de una empresa
    public void actualizarEmpresa(String nit, String nuevoNombreEmpresa, AdministradorFlota nuevoAdministradorFlota) throws RuntimeException {
        if (!buscarNit(nit)) {
            throw new RuntimeException("La empresa con el NIT ingresado no fue encontrada.");
        }

        // Buscar la empresa y actualizar sus datos
        for (int i = 0; i < empresas.size(); i++) {
            if (this.empresas.get(i).getNit().equals(nit)) {
                this.empresas.get(i).setNombreEmpresa(nuevoNombreEmpresa);
                this.empresas.get(i).setAdministradorFlota(nuevoAdministradorFlota);
            }
        }
        this.agregarDatos();
    }

    // Obtener todas las empresas
    public ILista<Empresa> obtenerEmpresas() throws RuntimeException {
        return this.empresas;
    }

    // Buscar una empresa por NIT
    private boolean buscarNit(String nit) {
        for (int i = 0; i < this.empresas.size(); i++) {
            if (this.empresas.get(i).getNit().equals(nit)) {
                return true;
            }
        }
        return false;
    }

    // Obtener una empresa por NIT
    private Empresa obtenerEmpresaPorNit(String nit) {
        for (int i = 0; i < this.empresas.size(); i++) {
            if (this.empresas.get(i).getNit().equals(nit)) {
                return this.empresas.get(i);
            }
        }
        return null;
    }

    // Obtener el índice de una empresa por NIT
    private int obtenerIndiceEmpresa(String nit) {
        for (int i = 0; i < this.empresas.size(); i++) {
            if (this.empresas.get(i).getNit().equals(nit)) {
                return i;
            }
        }
        return -1;
    }

    // Persistir las empresas en el archivo
    private void agregarDatos() {
        try {
            this.servicioEmpresaDatos.agregarEmpresaArchivo(this.empresas);
        } catch (Exception e) {
            System.out.println("Error al agregar datos: " + e.getMessage());
        }
    }

    // Cargar las empresas desde el archivo
    private void cargarDatos() {
        this.empresas = this.servicioEmpresaDatos.cargarEmpresaArchivo();
    }
}
