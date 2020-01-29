public class ThreadFromMainThread {
    public static void main(String[] args) {
        System.setSecurityManager(
                new ThreadWatcher(
                        DemoSupport.createPredicate(),
                        DemoSupport.createConsumer()
                )
        );

        new Thread(DemoSupport.createHelloJob(),
                "This should work 1").start();
        System.setSecurityManager(null);
    }
}
