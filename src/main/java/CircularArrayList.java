import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;

import static java.lang.System.arraycopy;

public class CircularArrayList<E> extends AbstractList<E> {

    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public CircularArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public CircularArrayList(int initialCapacity) {
        elements = new Object[initialCapacity];
        size = 0;
    }

    public CircularArrayList(Collection<? extends E> collection) {
        elements = collection.toArray();
        size = collection.size();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int index) {
        checkIndexRange(index);
        return (E) elements[index];
    }

    private void checkIndexRange(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    public E set(int index, E element) {

        checkIndexRange(index);
        E old = (E) elements[index];
        elements[index] = element;
        return old;

    }


    public void add(int index, E element) {

        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        ensureCapacity();

        if (index != size) {
            arraycopy(elements, index, elements, index + 1, size - index);
        }

        elements[index] = element;
        size++;
        modCount++;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, (elements.length + 1) * 3 / 2);
        }
    }


    public E remove(int index) {

        checkIndexRange(index);

        E old = (E) elements[index];

        if (index != size - 1) {
            arraycopy(elements, index + 1, elements, index, size - index - 1);
        }
        elements[size - 1] = null;

        size--;
        modCount++;
        return old;
    }


}
