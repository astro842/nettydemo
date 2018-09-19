package com.astro.flashman.flashNetty2;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author lyh
 * @date 2018/9/10 
 * @version v
 */


public class clientdemo {

    private static final int MAX_RETRY = 5;
    private static final int PORT = 8001;
    private static final String HOST = "127.0.0.1";

    public static void main(String[] args) {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new SecondClientHandler());
                    }
                });

        connent(bootstrap, HOST, PORT, MAX_RETRY);
        //bootstrap.connect(HOST, PORT);

    }

    private static void connent(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, PORT).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("client连接成功");
            } else if (retry == 0) {
                System.out.println("cilent连接失败");
            } else {
                int order = (MAX_RETRY - retry) + 1;
                // int delay = 1 << order;
                int delay = 2;
                System.out.println(new Date().toString() + "client第" + order + "重连");
                // connent(bootstrap, "127.0.0.1", 1024, retry - 1);
                bootstrap.config().group().schedule(() -> connent(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }
}

