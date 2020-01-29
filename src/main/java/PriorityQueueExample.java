import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class PriorityQueueExample
{
    public static void main(String[] args) throws InterruptedException
    {
        PriorityBlockingQueue<Integer> priorityBlockingQueue = new PriorityBlockingQueue<>();

        Runnable runnable = () ->
        {
             System.out.println("Waiting to poll ...");

             poolAndExecute(priorityBlockingQueue);

        };

        new Thread(runnable).start();

        Thread.sleep(TimeUnit.SECONDS.toMillis(2));
        priorityBlockingQueue.add(1);

        Thread.sleep(TimeUnit.SECONDS.toMillis(2));
        priorityBlockingQueue.add(2);

        Thread.sleep(TimeUnit.SECONDS.toMillis(2));
        priorityBlockingQueue.add(3);

        Thread.sleep(TimeUnit.SECONDS.toMillis(5));
        priorityBlockingQueue.add(4);

    }

    private static void poolAndExecute(PriorityBlockingQueue<Integer> priorityBlockingQueue) {
        try {
            while (true) {
                Integer poll = priorityBlockingQueue.take();
                System.out.println("Polled : " + poll);

                Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}