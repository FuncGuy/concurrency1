import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class DemoTask implements Runnable {
    // Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId = ThreadLocal.withInitial(() -> nextId.getAndIncrement());
    // Returns the current thread's starting timestamp
    private static final ThreadLocal<Date> startDate = ThreadLocal.withInitial(() -> new Date());

    // Returns the current thread's unique ID, assigning it if necessary
    public int getThreadId() {
        return threadId.get();
    }

    @Override
    public void run() {
        System.out.printf("Starting Thread: %s : %s\n", getThreadId(), startDate.get());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Thread Finished: %s : %s\n", getThreadId(), startDate.get());
    }

    public static void main(String[] args) {
        Executor executor = Executors.newFixedThreadPool(8);

        for(int i = 0 ;i<50 ;i++){
            ((ExecutorService) executor).submit(new DemoTask());
        }
    }
}