package com.astro.flashman.flashNetty2;

import com.astro.flashman.flashNetty2.protocol.LoginRequestPacket;
import com.astro.flashman.flashNetty2.protocol.PacketCodeC;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author lyh
 * @date 2018/9/19 
 * @version v
 */


public class SecondClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LoginRequestPacket login = new LoginRequestPacket();
        login.setUserId("11111");
        login.setUsername("astro");
        login.setPassword("123");

        //编码
        PacketCodeC code = new PacketCodeC();
        ByteBuf buf = code.encode(ctx.alloc(), login);
        //写数据
        ctx.channel().writeAndFlush(buf);

    }
}

