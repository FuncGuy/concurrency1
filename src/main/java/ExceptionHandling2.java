public class ExceptionHandling2 {

    public static void main(String[] args) {

        Thread t = new Thread(() -> System.out.println("Run"));

        t.setDaemon(true);

        t.start();

        Runtime.getRuntime().halt(1);

    }
}
