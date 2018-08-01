package com.astro.Easy;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author lyh
 * @version v
 * @date 2018/7/30
 */

public class NettyServer {

    /*
     * Netty 服务器启动（指定配置3个个属性）
     * 1.线程模型 2.io模型 3.连接读写处理逻辑
     * 最后调用bind(8888)
     *
     */
    private static int PORT = 1000;

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workergroup = new NioEventLoopGroup();
        //服务器启动类
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workergroup)
                .channel(NioServerSocketChannel.class) //指定服务端的IO模型为NIO
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        System.out.println("连接成功");

                    }
                });
        bootstrap.bind(8888);
        //bind(bootstrap, PORT);
    }

    private static void bind(ServerBootstrap bootstrap, int port) {
        bootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("端口" + port + "绑定成功");
                } else {
                    System.err.println("端口" + port + "绑定失败");
                    Thread.sleep(200);
                    bind(bootstrap, port + 1);
                }
            }
        });

    }
}

