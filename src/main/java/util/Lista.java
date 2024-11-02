package util;
import util.interfaces.ILista;

import java.io.Serializable;

public class Lista<S> implements ILista<S>, Serializable {

    private Nodo<S> primero;
    private int size;

    @Override
    public void add(S dato) {
        Nodo<S> nuevo = new Nodo<>(dato);
        if (this.primero == null) {
            this.primero = nuevo;
        } else {
            Nodo<S> aux = this.primero;
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
        }
        this.size++;
    }

    @Override
    public S get(int index) {
        if (index < 0 || index >= this.size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (index == 0) {
            return this.primero.dato;
        } else {
            Nodo<S> aux = this.primero;
            int contador = 0;
            while (contador < index) {
                aux = aux.siguiente;
                contador++;
            }
            return aux.dato;
        }
    }

    @Override
    public void remove(int index){
        if(index < 0 || index >= this.size){
            throw new ArrayIndexOutOfBoundsException();
        }

        if (index == 0){
            this.primero = this.primero.siguiente;
        } else {
            Nodo<S> aux = this.primero;
            int contador = 0;
            while (contador < index - 1){
                aux = aux.siguiente;
                contador++;
            }
            aux.siguiente = aux.siguiente.siguiente;
        }
        this.size--;
    }

    public void set(int index, S dato){
        if(index < 0 || index >= this.size){
            throw new ArrayIndexOutOfBoundsException();
        }

        if (index == 0){
            this.primero.dato = dato;
        } else {
            Nodo aux = this.primero;
            int contador = 0;
            while (contador < index){
                aux = aux.siguiente;
                contador++;
            }
            aux.dato = dato;
        }
    }

    @Override
    public boolean isEmpty(){
        if (this.primero == null){
            return true;
        }
        return false;
    }

    @Override
    public int size(){
        return this.size;
    }

}

