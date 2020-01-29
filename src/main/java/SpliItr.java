import java.util.*;

public class SpliItr {

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();

        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add("F");

        Spliterator<String> spliterator1 = list.parallelStream().spliterator();

        Spliterator<String> spliterator2 = spliterator1.trySplit();

        spliterator1.forEachRemaining(System.out::println);

        System.out.println("========");

        spliterator2.forEachRemaining(System.out::println);
    }

}