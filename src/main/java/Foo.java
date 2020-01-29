import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Foo implements Runnable
{
    // SimpleDateFormat is not thread-safe, so give one to each thread
  /*  private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd HHmm"));

    */public String formatIt(Date date)
    {
        return formatter.format(date);
    }

  private static SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmm");





    @Override
    public void run() {
        System.out.println(formatIt(new Date()));

    }
    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(5);

        for(int i = 0; i<= 500; i++){
            service.submit(new Foo());
        }
    }

}