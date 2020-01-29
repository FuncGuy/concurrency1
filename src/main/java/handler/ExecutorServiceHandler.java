package handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

import static java.lang.Thread.currentThread;

public class ExecutorServiceHandler<S> extends DecoratedHandler<S> {

    private ExecutorService pool;
    private Thread.UncaughtExceptionHandler exceptionHandler;

    public ExecutorServiceHandler(Handler other, ExecutorService pool,
                                  Thread.UncaughtExceptionHandler exceptionHandler) {
        super(other);
        this.pool = pool;
        this.exceptionHandler = exceptionHandler;
    }

    public ExecutorServiceHandler(Handler<S> other, ExecutorService pool) {
        this(other, pool, (t, e) -> System.out.println("uncaught: " + t + " error " + e));
    }

    public void handle(S s) {
        pool.submit(new FutureTask<>(
                        () -> {
                                super.handle(s);
                                return null;
                            }) {
                                protected void setException(Throwable t) {
                                    exceptionHandler.uncaughtException(currentThread(), t);
                                }
                            }
        );
    }
}
