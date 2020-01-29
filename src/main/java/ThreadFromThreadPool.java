import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadFromThreadPool {

    public static void main(String... args) throws InterruptedException {

        System.setSecurityManager(
                new ThreadWatcher(
                        DemoSupport.createPredicate(),
                        DemoSupport.createConsumer()

                )
        );

        ExecutorService pool = Executors.newFixedThreadPool(2);

        Future<?> future = pool.submit(() ->
                new Thread(DemoSupport.createHelloJob(),
                        "This should print a warning 1"));
        try {

            future.get();

        } catch (ExecutionException e) {
            e.getCause().printStackTrace();
        }
        pool.shutdown();

        System.setSecurityManager(null);
    }
}