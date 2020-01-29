import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CHFun {

    private static Map<Integer, BigInteger> cache = new ConcurrentHashMap<>();

    static {
        cache.put(0, BigInteger.ZERO);
        cache.put(1, BigInteger.ONE);
    }

    public static BigInteger fibonacci(int n) {
        return cache.computeIfAbsent(n, key ->
                fibonacci(n - 1).add(fibonacci(n - 2)));

    }

    public static void main(String[] args) {

        System.out.println(fibonacci(1000));

        /*for (int i = 0; i < 10000; i++) {

            System.out.println("fibonacci(" + i + ") = " + fibonacci(i));
        }*/
    }
}
