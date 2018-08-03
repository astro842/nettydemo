package com.astro.Demo.HeartBeatAndSent.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author lyh
 * @version v
 * @date 2018/8/3
 */


public class ClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端init handler add");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端init channel注册");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端init channel active");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端 接收到 ：" + msg.toString());
        // idle_count = 0;
    }

    public ClientHandler(int idle_count) {
        this.idle_count = idle_count;
    }
    //心跳

    private int idle_count;

    public int getIdle_count() {
        return idle_count;
    }

    public void setIdle_count(int idle_count) {
        this.idle_count = idle_count;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete111111111");
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        System.out.println("-------------channelWritabilityChanged");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("客户端进入心跳机制,次数 ：" + getIdle_count());
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state().equals(IdleState.WRITER_IDLE)) {
                if (idle_count <= 4) {
                    idle_count++;
                    ctx.channel().writeAndFlush("heartbeat");
                } else {
                    System.out.println("客户端不再心跳");
                }
            }
        }
    }
}

