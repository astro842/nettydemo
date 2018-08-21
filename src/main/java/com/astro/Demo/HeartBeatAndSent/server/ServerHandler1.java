package com.astro.Demo.HeartBeatAndSent.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author lyh
 * @version v
 * @date 2018/8/3
 */


public class ServerHandler1 extends ChannelInboundHandlerAdapter {

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端init handler add");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端init channel注册");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端init channel active");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String mm = (String) msg;
        if ("heartbeat".equals(mm)) {
            idle_count++;
            System.out.println("服务端接收到心跳：====》" + mm);
            ctx.write("服务端接收到心跳 并返回：====》" + mm);
        } else {
            System.out.println("服务端接收到：====== > " + msg.toString());
            idle_count = 0;
            ctx.write("服务端把接受的数据返回 -》" + msg.toString());
        }
        ctx.flush();

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

    //心跳
    private int idle_count = 1; //客户端发来的心跳次数
    private int server_count = 1; //服务端自己的心跳

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state().equals(IdleState.READER_IDLE)) {
                System.out.println("服务端没接受到信息：开始心跳 次数为 " + server_count);
                if (server_count > 3) {
                    System.out.println("服务端 关闭这个channel");
                    ctx.channel().close();
                }
                server_count++;
            }
        }
    }
}

