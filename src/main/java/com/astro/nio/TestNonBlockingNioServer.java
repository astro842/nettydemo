package com.astro.nio;

import sun.util.locale.LocaleUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author lyh
 * @version v
 * @date 2018/7/25
 */


public class TestNonBlockingNioServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        TestNonBlockingNioServer t = new TestNonBlockingNioServer();
        t.server();
    }

    public void server() throws IOException {
        System.out.println("--------服务端开启--------");
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        ssChannel.configureBlocking(false);
        ssChannel.bind(new InetSocketAddress(8882));
        Selector selector = Selector.open();
        // 把channel注册到selector
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 轮询式
        while (selector.select() > 0) {
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey sk = it.next();
                if (sk.isAcceptable()) {
                    SocketChannel sChannel = ssChannel.accept();
                    sChannel.configureBlocking(false);
                    sChannel.register(selector, SelectionKey.OP_READ);
                } else if (sk.isReadable()) {
                    SocketChannel sChannel = (SocketChannel) sk.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = sChannel.read(buffer)) > 0) {
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, len));
                        buffer.clear();
                    }
                }
                it.remove();
            }
        }
    }

}

