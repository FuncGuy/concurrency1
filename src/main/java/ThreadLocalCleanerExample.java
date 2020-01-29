import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ThreadLocalCleanerExample {

    public static final ThreadLocal<DateFormat> df =
            ThreadLocal.withInitial(
                    () -> new SimpleDateFormat("yyyy-MM-dd"));

    public static void main(String... args) throws Exception {

        System.out.println("First ThreadLocal Context");

        try (ThreadLocalCleaner tlc = new ThreadLocalCleaner(ThreadLocalChangeListener.PRINTER)) {
            System.out.println(System.identityHashCode(df.get()));
            System.out.println(System.identityHashCode(df.get()));
            System.out.println(System.identityHashCode(df.get()));
        }

        System.out.println("Another ThreadLocal Context");

        try (ThreadLocalCleaner tlc = new ThreadLocalCleaner(ThreadLocalChangeListener.PRINTER)) {

            System.out.println(System.identityHashCode(df.get()));
            System.out.println(System.identityHashCode(df.get()));
            System.out.println(System.identityHashCode(df.get()));
        }

    }
}
