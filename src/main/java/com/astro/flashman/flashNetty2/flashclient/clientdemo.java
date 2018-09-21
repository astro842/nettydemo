package com.astro.flashman.flashNetty2.flashclient;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.astro.flashman.flashNetty2.codes.PacketDecoder;
import com.astro.flashman.flashNetty2.codes.PacketEncoder;
import com.astro.flashman.flashNetty2.flashclient.handler.LoginResponeHandler;
import com.astro.flashman.flashNetty2.flashclient.handler.MessageResponseHandler;
import com.astro.flashman.flashNetty2.protocol.PacketCodeC;
import com.astro.flashman.flashNetty2.protocol.request.MessageRequestPacket;
import com.astro.flashman.flashNetty2.util.LoginUtil;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
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
                        //ch.pipeline().addLast(new SecondClientHandler());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponeHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });

        connent(bootstrap, HOST, PORT, MAX_RETRY);
        //bootstrap.connect(HOST, PORT);

    }

    private static void connent(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, PORT).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("client连接成功");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
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

    private static void startConsoleThread(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (LoginUtil.hasLogin(channel)) {
                    System.out.println("------》" + Thread.currentThread().getName() + "--输入信息发送到服务端：");
                    Scanner sc = new Scanner(System.in);
                    String str = sc.nextLine();
                    if (str.isEmpty()) {
                        System.out.println(new Date() + "请输入内容！");
                    } else {
                        MessageRequestPacket packet = new MessageRequestPacket();
                        packet.setMessage(str);
                        ByteBuf encode = PacketCodeC.INSTANCE.encode(channel.alloc(), packet);
                        channel.writeAndFlush(encode);
                    }
                }
            }
        }).start();
    }
}

