class Demonstration222 {

    public static void main(String args[]) throws Exception {

        SynchronousExecutor executor = new SynchronousExecutor();

        executor.asynchronousExecution(() -> System.out.println("I am done"));

        System.out.println("main thread exiting...");
    }
}


class SynchronousExecutor extends Executor {

    @Override
    public void asynchronousExecution(Callback callback) throws Exception {

        Object signal = new Object();
        final boolean[] isDone = new boolean[1];

        Callback cb = () -> {
            callback.done();
            synchronized (signal) {
                signal.notify();
                isDone[0] = true;
            }
        };

        // Call the asynchronous executor
        super.asynchronousExecution(cb);

        synchronized (signal) {
            while (!isDone[0]) {
                signal.wait();
            }
        }

    }
}

