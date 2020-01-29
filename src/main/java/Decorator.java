import java.io.*;

public class Decorator {

    public static void main(String[] args) throws IOException {

        ObjectOutputStream oout =
                new ObjectOutputStream(
                        new FileOutputStream("bla"));

        oout.writeObject(new Integer(42));

        PrintStream pout = new PrintStream(
                new ByteArrayOutputStream());

        pout.println("hello world");



    }
}
