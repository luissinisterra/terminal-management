package servicios;

import modelos.AdministradorFlota;
import modelos.Empresa;
import util.Lista;
import util.interfaces.ILista;

public class ServicioEmpresa {

    private ILista<Empresa> empresas;

    public ServicioEmpresa() {
        this.empresas = new Lista<>();
    }

    public String agregarEmpresa(String nit, String nombreEmpresa, AdministradorFlota administradoFlota) {
        this.empresas.add(new Empresa(nit, nombreEmpresa, administradoFlota));
        return "Empresa agregada con exito";
    }

    public String eliminarEmpresa(String nit) {
        for (int i = 0; i < this.empresas.size(); i++) {
            if (this.empresas.get(i).getNit().equals(nit)) {
                this.empresas.remove(i);
                return "Empresa eliminada con exito";
            }
        }
        return "Empresa no encontrada";
    }

    public String editarEmpresa(String nit, String nombreEmpresa) {
        for (int i = 0; i < this.empresas.size(); i++) {
            if (this.empresas.get(i).getNit().equals(nit)) {
                this.empresas.get(i).setNombreEmpresa(nombreEmpresa);
                return "Empresa actualizada con exito";
            }
        }
        return "Empresa no encontrada";
    }

    public Empresa buscarEmpresa(String nit) {
        for (int i = 0; i < this.empresas.size(); i++) {
            if (this.empresas.get(i).getNit().equals(nit)) {
                return this.empresas.get(i);
            }
        }
        return null;
    }

}
