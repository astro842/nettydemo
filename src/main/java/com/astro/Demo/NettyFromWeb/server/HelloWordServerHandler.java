package com.astro.Demo.NettyFromWeb.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author lyh
 * @version v
 * @date 2018/8/2
 */


public class HelloWordServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端----------注册channelRegistered");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端----------添加handlerAdded");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端----------接收到请求channelActive");
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务端接收到数据: " + ctx.channel().remoteAddress() + "=====>" + msg.toString());
        ctx.write("服务端返回， 已接受到 ：" + msg.toString());
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}

