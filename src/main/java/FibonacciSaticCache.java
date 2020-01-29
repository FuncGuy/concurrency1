import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FibonacciSaticCache implements Fibonacci {
    private final static Map<Integer, BigInteger> cache;

    static {
        cache = new ConcurrentHashMap<>();
        cache.put(0, BigInteger.ZERO);
        cache.put(1, BigInteger.ONE);
    }

    @Override
    public BigInteger f(int n) {
        BigInteger result = cache.get(n);
        if (result == null) {
            BigInteger newVal = f(n - 1).add(f(n - 2));
            result = cache.putIfAbsent(n, newVal);
            if (result == null) result = newVal;
        }
        return result;
    }

    public static void main(String[] args) {

    }
}

