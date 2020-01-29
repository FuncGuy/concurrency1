import java.util.List;
import java.util.concurrent.*;

import static java.util.Arrays.asList;

public class Threads {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Starting to send emails from the " + Thread.currentThread().getName());

        sendAllEmails(asList("tirumalesh.yeligar@gmail.com", "tim25659@gmail.com", "abc@xyz.com"));
    }

    private static void sendAllEmails(List<String> emails) throws InterruptedException {
        ExecutorService executorService;
        final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(100);
        executorService = new ThreadPoolExecutor(8, 8,
                0L, TimeUnit.MILLISECONDS,
                queue);
        emails.forEach(email ->
                executorService.submit(() ->
                        sendEmail(email)));
        executorService.submit(() -> System.out.println(1 / 0));
        executorService.shutdown();
        final boolean done = executorService.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("All e-mails were sent so far ");
    }

    private static void sendEmail(String email) {
        System.out.println("sending email to "+ email + " from the thread " + Thread.currentThread().getName());
    }

}
