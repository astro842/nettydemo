package com.astro.Demo.NettyFromWeb.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author lyh
 * @version v
 * @date 2018/8/2
 */


public class HelloWordClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端----》channelActive");
        //有两个HelloWordClientHandler时，加上下面那句  才会运行到 下个个HelloWordClientHandler2
        ctx.fireChannelActive();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端----》channelRead " + msg.toString());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端----》 is close");
    }
}

