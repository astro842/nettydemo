package com.astro.flashman.flashNetty2.flashserver.handler;

import java.util.Date;

import com.astro.flashman.flashNetty2.protocol.request.MessageRequestPacket;
import com.astro.flashman.flashNetty2.protocol.response.MessageResponsePacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author lyh
 * @date 2018/9/21 
 * @version v
 */


public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        System.out.println(new Date() + "服务端：接受客户端发来的信息---->  " + msg.getMessage());
        MessageResponsePacket packet = new MessageResponsePacket();
        packet.setMessage("-------->服务端回复：" + msg.getMessage());

        ctx.channel().writeAndFlush(packet);

    }
}

