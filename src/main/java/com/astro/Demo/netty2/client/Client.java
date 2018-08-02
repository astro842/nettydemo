package com.astro.Demo.netty2.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author lyh
 * @version v
 * @date 2018/7/26
 */


public class Client {

    private final String host;
    private final int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }


    public void start() throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                .addLast(new ClientHandler());
                    }
                });
        ChannelFuture future = bootstrap.connect(host, port).sync();
        future.channel().writeAndFlush(Unpooled.copiedBuffer("client 发信息".getBytes()));
        future.channel().closeFuture().sync();
        workerGroup.shutdownGracefully();
    }

    public static void main(String[] args) throws Exception {
        new Client("127.0.0.1", 8888).start();
    }

}
