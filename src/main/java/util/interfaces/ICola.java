package util.interfaces;

public interface ICola<T> {
    public void enqueve(T elemento);
    public T dequeve();
    public T peek();
    public boolean isEmpty();
}
