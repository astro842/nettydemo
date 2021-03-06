package com.astro.Demo.NettyFromGitHub.heartbeat.client;

import java.io.IOException;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Title: NettyClient
 * Description:
 * Netty客户端  心跳测试
 * Version:1.0.0
 *
 * @author pancm
 * @date 2017年10月8日
 */
public class NettyClient2 {

    public static String host = "127.0.0.1";  //ip地址
    public static int port = 9876;          //端口
    /// 通过nio方式来接收连接和处理连接   
    private static EventLoopGroup group = new NioEventLoopGroup();
    private static Bootstrap b = new Bootstrap();
    private static Channel ch;

    /**
     * Netty创建全部都是实现自AbstractBootstrap。
     * 客户端的是Bootstrap，服务端的则是    ServerBootstrap。
     **/
    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("客户端成功启动...");
        b.group(group);
        b.channel(NioSocketChannel.class);
        b.handler(new NettyClientFilter2());
        // 连接服务端
        ch = b.connect(host, port).sync().channel();
        star();
    }

    public static void star() throws IOException {
        String str = "Hello Netty";
        ch.writeAndFlush(str);
        System.out.println("客户端发送数据:" + str);
    }
}
