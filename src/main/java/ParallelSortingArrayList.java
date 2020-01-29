import java.lang.reflect.Field;
import java.util.*;

public class ParallelSortingArrayList<E> extends ArrayList<E> {

    private static final Field elementDataField;

    static {
        try {
            elementDataField = ArrayList.class.getDeclaredField("elementData");
            elementDataField.setAccessible(true);
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    public ParallelSortingArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    public ParallelSortingArrayList() {

    }

    public ParallelSortingArrayList(Collection<? extends E> c) {
        //super(c);
    }

    public void sort(Comparator<? super E> c) {
        try {
            final int expectedModCount = modCount;
            Arrays.parallelSort((E[]) elementDataField.get(this), 0, size(), c);
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            modCount++;
        } catch (IllegalAccessException e) {
            throw new IllegalStateException();
        }
    }

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();

        for(int i =0 ; i< 1_000_000_0; i++){
            strings.add(i+"");
        }

        ParallelSortingArrayList parallelSortingArrayList = new ParallelSortingArrayList(strings);
        parallelSortingArrayList.sort(Comparator.naturalOrder());

    }
}
