import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CountingOutputStream2 extends FilterOutputStream {

    private long numberOfBytesWritten = 0;

    public CountingOutputStream2(OutputStream out){
        super(out);
    }

    public void write(int b) throws IOException {
        super.write(b);
        numberOfBytesWritten++;
    }

    public long getNumberOfBytesWritten(){
        return numberOfBytesWritten;
    }

}
