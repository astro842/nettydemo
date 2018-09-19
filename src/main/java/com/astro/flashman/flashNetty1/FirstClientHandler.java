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
public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //客户端写数据
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = "---astro---".getBytes(Charset.forName("utf-8"));
        buffer.writeBytes(bytes);

        ctx.channel().writeAndFlush(buffer);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf b = (ByteBuf) msg;

        System.out.println(new Date() + "客户端接收到的------" + b.toString(Charset.forName("utf-8")));
    }
}

