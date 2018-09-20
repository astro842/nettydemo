package com.astro.flashman.flashNetty2.flashserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author lyh
 * @date 2018/9/10 
 * @version v
 */


public class serverdemo {
    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap boot = new ServerBootstrap();
        boot.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new SecondServerHandler());
                    }
                });
        bind(boot, 8001);
        //boot.bind(8111);
    }

    private static void bind(ServerBootstrap boot, int port) {
        boot.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("端口：" + port + "绑定成功");
                } else {
                    System.out.println("端口：" + port + "绑定失败");
                    Thread.sleep(200);
                    bind(boot, port + 1);
                }
            }
        });
    }
}

