import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class LinkedHashSetExample {

    public static void main(String[] args) {

        LinkedHashSet<Integer> numbers = new LinkedHashSet<>();

        numbers.add(2);
        numbers.add(3);
        numbers.add(1);
        numbers.add(5);
        numbers.add(0);

        System.out.println("numbers= "+numbers);

        LinkedHashMap<Integer, Integer> squres =
                new LinkedHashMap<>(16, 0.75f, false);

        squres.put(1, 1);
        squres.put(3, 9);
        squres.put(12, 144);
        squres.put(15, 225);
        squres.put(16, 256);

        System.out.println("squares = " + squres);

        Thread printingHook = new Thread(() -> System.out.println("In the middle of a shutdown"));
        System.exit(129);

        Runtime.getRuntime().addShutdownHook(printingHook);


    }
}
