import java.lang.ref.Reference;

public class SoftReference {

    public static void main(String[] args) {

        String name = new String("Heinz");

        Reference<String> refStr = new java.lang.ref.SoftReference<>(name);

        name = null;

        System.out.println(refStr.get());

        System.gc();

        System.out.println(refStr.get());

        try {
            byte[][] b = new byte[1024][1024 * 1024 * 1024];
        } catch (OutOfMemoryError e) {
            System.out.println(e);
        }
        System.out.println(refStr.get());
    }
}
