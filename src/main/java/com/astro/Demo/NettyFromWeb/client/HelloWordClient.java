package com.astro.Demo.NettyFromWeb.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author lyh
 * @version v
 * @date 2018/8/2
 */


public class HelloWordClient {

    private String host;
    private int port;

    public HelloWordClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws IOException {
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();

                        p.addLast("decoder", new StringDecoder());
                        p.addLast("encoder", new StringEncoder());
                        p.addLast("handler", new HelloWordClientHandler());
                        p.addLast("handler2", new HelloWordClientHandler2());
                    }
                });
        try {
            Channel channel = b.connect(host, port).sync().channel();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            for (; ; ) {
                String msg = reader.readLine();
                if (msg == null) {
                    continue;
                }
                channel.writeAndFlush(msg + "\r\n");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws IOException {
        HelloWordClient client = new HelloWordClient("127.0.0.1", 8888);
        client.start();
    }

}

