import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class ThreadPoolExample
{
    public static void main(String[] args)
    {
        //available threads 2
        ThreadPoolExecutor executor = (ThreadPoolExecutor) newFixedThreadPool(2);

        //task count is 5
        //3 task to waiting queue

        for (int i = 1; i <= 5; i++)
        {
            Task task = new Task("Task " + i);
            System.out.println("Created : " + task.getName());

            executor.execute(task);
        }
        executor.shutdown();
    }
}
