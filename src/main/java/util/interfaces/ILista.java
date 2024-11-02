package util.interfaces;

public interface ILista<T> {
    public void add(T dato);
    public T get(int index);
    public void remove(int index);
    public boolean isEmpty();
    public int size();
}
