package echowand.util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Collector<T> {
    private Selector<? super T> selector;

    public Collector(Selector<? super T> selector) {
        this.selector = selector;
    }

    public List<T> collect(Collection<? extends T> objects) {
        LinkedList<T> newList = new LinkedList<T>();
        
        for (T object : objects) {
            if (selector.match(object)) {
                newList.add(object);
            }
        }
        
        return newList;
    }
}
