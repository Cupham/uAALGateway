package echowand.util;

public interface Selector<T> {

    public boolean match(T object);
}
