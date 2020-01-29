import java.util.concurrent.SynchronousQueue;

public class SynchQueueTest {

    public static void main(String[] args) throws InterruptedException {

        SynchronousQueue<Integer> queue = new SynchronousQueue<>(false);

        for (int i = 0; i < 10; i++) {
            int finalI = i;

            new Thread(() -> {
                try {
                    queue.put(finalI);
                } catch (InterruptedException e) {
                }
            }).start();

            Thread.sleep(100);
        }
            for(int i = 0; i < 10; i++){
                System.out.println(queue.take());
            }


        }
    }

