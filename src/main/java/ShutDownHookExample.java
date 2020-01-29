import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ShutDownHookExample {

    public static void main(String[] args) throws IOException {
        var out1 = new PrintWriter(
                new FileWriter("test1.txt"), false);
        out1.println("This will not be in test1.txt file!");

        var out2 = new PrintWriter(
                new FileWriter("test2.txt"), false);

        Runtime.getRuntime().addShutdownHook(new java.lang.Thread(out2::close));
        out2.println("This will be in the test2.txt file!");
    }
}
