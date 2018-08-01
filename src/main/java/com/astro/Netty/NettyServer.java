package com.astro.Netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

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
                .handler(new NettyServerHandler())
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        System.out.println("连接成功");
                    }
                });
        bootstrap.bind(8888);
    }

}

