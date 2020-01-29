interface Callback {

     void done();
}

class Demonstration1 {

    public static void main(String args[]) throws Exception {

        Executor executor = new Executor();

        executor.asynchronousExecution(() -> System.out.println("I am done"));

        System.out.println("main thread exiting...");
    }
}

class Executor {

    public void asynchronousExecution(Callback callback) throws Exception {

        Thread t = new Thread(() -> {
            // Do some useful work
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ie) {
            }
            callback.done();
        });
        t.start();
    }
}