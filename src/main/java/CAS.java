import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class CAS {

    private volatile double[] xy = {0, 0};

    private static VarHandle XY_HANDLE;

    static {
        try {
            XY_HANDLE = MethodHandles.lookup().findVarHandle(CAS.class,"xy", double[].class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
