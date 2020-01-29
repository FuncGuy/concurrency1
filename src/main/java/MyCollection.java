import java.util.Collection;

public interface MyCollection<E> {
    int size();

    boolean isEmpty();

    boolean contains(Object o);

    MyIterator<E> iterator();

    Object[] toArray();

    <T> T[] toArray(T... a);

    boolean containsAll(Collection<?> c);

    boolean equal(Object o);

    int hashCode();

}
