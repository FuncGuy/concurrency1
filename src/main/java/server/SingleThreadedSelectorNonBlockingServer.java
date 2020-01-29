package server;

import handler.AcceptHandler;
import handler.Handler;
import handler.ReadHandler;
import handler.TransmogrifyChannelHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

public class SingleThreadedSelectorNonBlockingServer {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel ssc = ServerSocketChannel.open();

        ssc.bind(new InetSocketAddress(8080));

        ssc.configureBlocking(false);

        Selector selector = Selector.open();

        ssc.register(selector, SelectionKey.OP_ACCEPT);

        Map<SocketChannel, Queue<ByteBuffer>> pendingData = new HashMap<>();

        Handler<SelectionKey> readHandler = new ReadHandler();

        Handler<SelectionKey> acceptHandler = new AcceptHandler(null);

        Handler<SocketChannel> handler = new TransmogrifyChannelHandler();

        Collection<SocketChannel> sockets = new ArrayList<>();

        while (true) {

            SocketChannel sc = ssc.accept();

            if (sc != null) {
                sockets.add(sc);
                System.out.println("Connected to " + sc);
                sc.configureBlocking(false);
            }

            for (SocketChannel socket : sockets) {
                if (socket.isConnected()) {
                    handler.handle(sc);
                }
            }

            sockets.removeIf(socket -> !socket.isConnected());
        }
    }

}
