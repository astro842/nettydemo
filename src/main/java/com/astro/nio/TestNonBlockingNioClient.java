package com.astro.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author lyh
 * @version v
 * @date 2018/7/25
 */


public class TestNonBlockingNioClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        TestNonBlockingNioClient t = new TestNonBlockingNioClient();
        t.client();
    }

    public void client() throws IOException {
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8882));
        // 切换为 非阻塞模式
        sChannel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 数据都要先写入buffer
        Scanner cScanner = new Scanner(System.in);
        String str = null;
        while (cScanner.hasNext()) {
            str = cScanner.nextLine();
            buffer.put((new Date().toString() + "\n" + str).getBytes());
            buffer.flip();
            sChannel.write(buffer);
            buffer.clear();
        }
        //        buffer.put("你好".getBytes());
        //        buffer.flip();
        //        sChannel.write(buffer);
        //        buffer.clear();

        sChannel.close();
    }

}

