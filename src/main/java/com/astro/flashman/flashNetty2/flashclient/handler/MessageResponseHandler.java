package com.astro.flashman.flashNetty2.flashclient.handler;

import java.util.Date;

import com.astro.flashman.flashNetty2.protocol.response.MessageResponsePacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author lyh
 * @date 2018/9/21 
 * @version v
 */


public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket packet) throws Exception {
        System.out.println(new Date() + " 收到服务端发来的信息：" + packet.getMessage());
    }
}

