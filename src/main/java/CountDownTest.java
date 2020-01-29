import java.util.concurrent.CountDownLatch;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class CountDownTest {

    public static void main(String[] args) throws InterruptedException {

        var latch = new CountDownLatch(3);

        var pool = newFixedThreadPool(5);

        for(int i = 0; i < 5; i++) {

            pool.execute(() -> {

                System.out.println("Waiting.....");

                try{

                    latch.await();

                } catch (InterruptedException e) {
                    java.lang.Thread.currentThread().interrupt();
                    return;
                }
                System.out.println(".....finished");
            } );

            java.lang.Thread.sleep(1000);

            latch.countDown();
        }

        pool.shutdown();
    }
}
