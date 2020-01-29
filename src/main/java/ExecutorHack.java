import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorHack {

    public static void main(String ... args) {

        ExecutorService executor = Executors.newFixedThreadPool(20);

        executor.submit(() -> {});
        executor.shutdownNow();
    }

}
