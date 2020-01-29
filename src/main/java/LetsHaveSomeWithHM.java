 import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;
 import java.util.concurrent.locks.Lock;
 import java.util.concurrent.locks.ReentrantLock;

 public class LetsHaveSomeWithHM {

    private final static Map<Integer, Integer> map = new ConcurrentHashMap<>();
    private static LongAdder counter = new LongAdder();
    static Lock lock = new ReentrantLock();

    public static void main(String... args) throws InterruptedException {

        Thread[] threads = new Thread[8];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 50_000_000; j++) {
                    for (int k = 0; k < 10; k++) {

                        map.computeIfAbsent(k, key -> {
                                    counter.increment();
                                    return 0;

                                }
                        );
                        map.remove(k);
                    }
                }
            });

            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        try {
            if (lock.tryLock()) {
                //
            }
        }finally {
            lock.unlock();
        }

        System.out.println("couter.longValue() = " + counter.longValue());
    }
}
