import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example3 {

    public static void main(String[] args) {

        new Example3();

    }

    public Example3() {

        try {
            //testFixedPool(1);
            testFixedPool(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void testFixedPool(int numberOfThreads) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        long start = System.currentTimeMillis();

        executor.execute(new TaskPrintInteger(0, 10000));
        executor.execute(new TaskPrintInteger(1, 10000));
        executor.execute(new TaskPrintInteger(2, 10000));
        executor.execute(new TaskPrintInteger(3, 10000));
        executor.execute(new TaskPrintInteger(4, 10000));
        executor.execute(new TaskPrintInteger(5, 10000));
        executor.execute(new TaskPrintInteger(6, 10000));
        executor.execute(new TaskPrintInteger(7, 10000));
        executor.execute(new TaskPrintInteger(8, 10000));
        executor.execute(new TaskPrintInteger(9, 10000));
        executor.shutdown();

        while(!executor.isTerminated()){
        }

        System.out.println();
        System.out.println((System.currentTimeMillis()) - start);
    }

    private class TaskPrintInteger implements Runnable {

        private int number, times;

        public TaskPrintInteger(int number, int times) {

            this.number = number;
            this.times = times;
        }

        @Override
        public void run() {

            for (int i = 0; i < times; i++) {
                number+=times;
            }

        }

    }

}