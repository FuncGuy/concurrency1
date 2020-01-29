import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorExample {

    public static void main(String[] args)
    {
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(2);

        Task task = new Task("Repeat Task");
        System.out.println("Created : " + task.getName());

        executor.scheduleWithFixedDelay(task, 2, 2, TimeUnit.SECONDS);
    }
}
