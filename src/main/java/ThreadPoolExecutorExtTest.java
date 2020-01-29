import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ThreadPoolExecutorExtTest {
    private final static Logger LOG = Logger.getLogger(ThreadPoolExecutorExtTest.class.getName()
    );
    private static final ThreadLocal<DateFormat> df =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    public static void main(String... args) throws InterruptedException {

        ThreadPoolExecutor tpe = new ThreadPoolExecutorExt(1, 1, 0, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                (m, t, tl, v) -> {
                    LOG.warning(
                            () -> String.format(
                                    "Thread %s %s ThreadLocal %s with value %s%n",
                                    t, m, tl.getClass(), v)
                    );
                }
        );
        for (int i = 0; i < 10; i++) {
            tpe.submit(() ->
                    System.out.println(System.identityHashCode(df.get())));
            Thread.sleep(1000);
        }
        tpe.shutdown();
    }
}