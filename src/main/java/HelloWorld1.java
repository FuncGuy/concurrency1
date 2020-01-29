import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class HelloWorld1 {

    public byte[] generateHelloWorlds(int numberOfHelloWorlds) throws IOException {

        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        //after compressing count
        CountingOutputStream2 counting2 =
                new CountingOutputStream2(bout);

        // before compressing count
        CountingOutputStream2 counting1 = new CountingOutputStream2(
                new GZIPOutputStream(counting2));

        PrintStream out = new PrintStream(
                new BufferedOutputStream(
                        counting1
                )

        );

        for (int i = 0; i < numberOfHelloWorlds; i++) {
            out.println("Hello World!");
        }

        out.close();

        System.out.println(counting1.getNumberOfBytesWritten());
        System.out.println(counting2.getNumberOfBytesWritten());
        return bout.toByteArray();
    }

    public void display(byte[] message) throws IOException {

        InputStream in = new GZIPInputStream(
                new ByteArrayInputStream(message));
        int i;

        while((i = in.read()) != -1){
            System.out.println((char)i);
        }
    }

    public static void main(String[] args) throws IOException {
        HelloWorld1 hw = new HelloWorld1();
        byte[] message = hw.generateHelloWorlds(1000);
        hw.display(message);
    }
}
