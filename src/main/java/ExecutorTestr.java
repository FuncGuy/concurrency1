import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTestr {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(1);

        executor.submit(() -> System.out.println("hello"));

        executor.shutdown();

        executor.submit(() -> System.out.println("g"));




    }
}
