package controladores;

import modelos.Caseta;
import servicios.ServicioCaseta;

public class ControladorVistaAdminFlota {
    private ServicioCaseta servicioCaseta;
    public ControladorVistaAdminFlota() {
        this.servicioCaseta = new ServicioCaseta();
    }

    //Metodo de casetas
    public Caseta[][] obtenerCasetas(){
        return this.servicioCaseta.obtenerCasetas();
    }
}
