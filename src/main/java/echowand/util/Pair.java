package echowand.util;

public class Pair<T1, T2> {

    public T1 first;

    public T2 second;

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "Pair(" + first + ", " + second + ")";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.first != null ? this.first.hashCode() : 0);
        hash = 53 * hash + (this.second != null ? this.second.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) {
            return false;
        }
        Pair other = (Pair)o;
        return other.first.equals(first) && other.second.equals(second);
    }
}
