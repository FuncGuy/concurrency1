import java.util.concurrent.LinkedBlockingQueue;

@SuppressWarnings("unused")
public class CustomThreadPool
{
    //Thread pool size
    private final int poolSize;

    //Internally pool is an array
    private final WorkerThread[] workers;

    // FIFO ordering
    private final LinkedBlockingQueue<Runnable> queue;

    public CustomThreadPool(int poolSize)
    {
        this.poolSize = poolSize;
        queue = new LinkedBlockingQueue<>(); // queue to pile up the tasks
        workers = new WorkerThread[poolSize]; // create the worker threads equal to the pool size

        for (int i = 0; i < poolSize; i++) {
            workers[i] = new WorkerThread();
            workers[i].start();
        }
    }

    public void execute(Runnable task) {
        synchronized (queue) {
            queue.add(task);
            queue.notify();
        }
    }

    private class WorkerThread extends Thread {
        public void run() {
            Runnable task;

            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            System.out.println("An error occurred while queue is waiting: " + e.getMessage());
                        }
                    }
                    task = queue.poll();
                }

                try {
                    task.run();
                } catch (RuntimeException e) {
                    System.out.println("Thread pool is interrupted due to an issue: " + e.getMessage());
                }

                if(shutdown()){
                    break;
                }
            }
        }
    }

    public boolean shutdown() {
        for (int i = 0; i < poolSize; i++) {
            workers[i] = null;
        }
        return true;
    }
}