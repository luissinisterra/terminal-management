package util;

import java.io.Serializable;

public class Nodo<T> implements Serializable {
    public T dato;
    public Nodo<T> siguiente;

    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}
