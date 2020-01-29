package server;

import handler.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class BlockingNIOServer {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel ssc = ServerSocketChannel.open();

        ssc.bind(new InetSocketAddress(8080));

        ExecutorServiceHandler<SocketChannel> handler =

                new ExecutorServiceHandler(
                        new PrintingHandler(
                                new BlockingChannelHandler(
                                        new TransmogrifyChannelHandler())),
                                                newFixedThreadPool(10));

        while (true) {

            SocketChannel sc = ssc.accept();

            handler.handle(sc);
        }
    }

}
