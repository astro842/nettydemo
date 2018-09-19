package com.astro.flashman.flashNetty1;

import java.nio.charset.Charset;
import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author lyh
 * @date 2018/9/19 
 * @version v
 */


public class FirstServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        String s = buf.toString(Charset.forName("utf-8"));
        System.out.println("服务端接收到的数据：---》" + s);

        System.out.println(new Date() + "服务端写数据");
        byte[] bytes = "服务端发过来的数据".getBytes(Charset.forName("utf-8"));
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeBytes(bytes);
        ctx.channel().writeAndFlush(buffer);
    }
}

