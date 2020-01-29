import java.util.Collection;

public interface MyMutableCollection<E> extends MyCollection {

    boolean add(E e);

    boolean remove(Object o);

    boolean addAll(Collection<? extends E>  c);

    boolean removeAll(Collection<? extends E> c);

    boolean retainAll(Collection<? extends E> c);

    void clear();

    MyMutableIterator<E> iterator();
}
