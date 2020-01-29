import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class HelloWorld {

    public byte[] generateHelloWorlds(int numberOfHelloWorlds, int compressionRepeat) throws IOException {

        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        CountingOutputStream counting2 = new CountingOutputStream(bout);

        OutputStream zip = counting2;
        //GZIPOutputStream zip = new GZIPOutputStream(counting2);
        for(int i =1; i<compressionRepeat;i++){
            zip = new GZIPOutputStream(zip);
        }
        CountingOutputStream counting1 = new CountingOutputStream(
                zip);

        PrintStream out = new PrintStream(new BufferedOutputStream(counting1));

        for (int i = 0; i < numberOfHelloWorlds; i++) {
            out.println("hello world!");
        }

        out.close();

        System.out.println(counting1.getNumberOfBytesWritten());
        System.out.println(counting2.getNumberOfBytesWritten());
        return bout.toByteArray();

    }

    public void display(byte[] message, int compressionRepeat) throws IOException {
        InputStream in = new ByteArrayInputStream(message);

        for(int i=0; i<compressionRepeat;i++){
            in = new GZIPInputStream(in);
        }

        int i;

        while((i=in.read())!= -1){
            System.out.println((char)i);
        }
    }

    public static void main(String[] args) throws IOException {
        HelloWorld hw = new HelloWorld();
        byte[] message = hw.generateHelloWorlds(1000, 1);
        hw.display(message, 1);
    }
}
