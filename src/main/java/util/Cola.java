package util;

import util.interfaces.ICola;

import java.io.Serializable;

public class Cola<S> implements ICola<S>, Serializable {
    private Nodo<S> primero;

    @Override
    public void enqueve(S dato) {
        System.out.println("Agregando dato: " + dato);
        Nodo<S> nuevo = new Nodo<>(dato);
        if (this.primero == null) {
            this.primero = nuevo;
            System.out.println("El primero ahora es: " + this.primero.dato);
        } else {
            Nodo<S> aux = this.primero;
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
            System.out.println("Dato agregado al final: " + nuevo.dato);
        }
        
        /*Nodo<S> nuevo = new Nodo(dato);
        if (this.primero == null) {
            this.primero = nuevo;
        } else {
            Nodo<S> aux = this.primero;
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
        }*/
    }

    @Override
    public S dequeve() {
        if (this.primero == null) {
            return null;
        }
        Nodo<S> aux = this.primero;
        this.primero = aux.siguiente;
        return this.primero.dato;
    }

    @Override
    public S peek() {
        return this.primero.dato;
    }

    @Override
    public boolean isEmpty() {
        if(this.primero == null){
            return true;
        }
        return false;
    }
}
