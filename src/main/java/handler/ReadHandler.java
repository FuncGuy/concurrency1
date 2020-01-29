package handler;

import util.Util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class ReadHandler implements Handler<SelectionKey> {
    @Override
    public void handle(SelectionKey selectionKey) throws IOException {
        SocketChannel sc = (SocketChannel) selectionKey.channel();
        ByteBuffer buf = ByteBuffer.allocate(80);
        int read = sc.read(buf);

        if(read == -1){
            sc.close();
            return;
        }

        if(read > 0){
            Util.transmogrify(buf);
        }
    }
}
