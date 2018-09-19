package com.astro.flashman.flashNetty2;

import com.astro.flashman.flashNetty2.protocol.LoginRequestPacket;
import com.astro.flashman.flashNetty2.protocol.Packet;
import com.astro.flashman.flashNetty2.protocol.PacketCodeC;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author lyh
 * @date 2018/9/19 
 * @version v
 */


public class SecondServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;

        PacketCodeC code = new PacketCodeC();
        //解码
        Packet packet = code.decode(buf);
        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket login = (LoginRequestPacket) packet;
            //校验
        }

    }
}

