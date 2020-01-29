@FunctionalInterface
public interface ThreadLocalChangeListener {
    ThreadLocalChangeListener EMPTY = (m, t, t1, v) -> {
    };
    ThreadLocalChangeListener PRINTER =
            (m, t, t1, v) -> System.out.printf(
                    "Thread %s %s ThreadLocal %s with value %s%n",
                    t, m, t1.getClass(), v);// thread 01 added threadLocal string with value "heinz"

    void changed(Mode mode, Thread thread, ThreadLocal<?> threadLocal, Object value);

    enum Mode {
        ADDED, REMOVED
    }
}