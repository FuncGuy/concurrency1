import java.util.Objects;
import java.util.stream.Collectors;
import static java.util.stream.Stream.of;

public class BasicHashTable<K, V> {

    private final Object[] values = new Object[128];

    public void put(K key, V value) {

        values[calculatePosition(key)] = value;

    }

    public V get(K key) {

        return (V) values[ calculatePosition(key)];
    }

    private int calculatePosition(K key) {
        int hash = key.hashCode();
        int pos = hash & 127;
        if(pos < 0) pos+= values.length;
        return pos;
    }

    public String toString(){
        return  of(values)
                .filter(Objects::nonNull)
                .map(Object::toString)
                .collect(Collectors.joining(", ", "{", "}"));
    }
}
