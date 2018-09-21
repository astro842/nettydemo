package com.astro.flashman.flashNetty2.flashclient.handler;

import java.util.Date;

import com.astro.flashman.flashNetty2.protocol.request.LoginRequestPacket;
import com.astro.flashman.flashNetty2.protocol.response.LoginResponsePacket;
import com.astro.flashman.flashNetty2.util.LoginUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author lyh
 * @date 2018/9/21 
 * @version v
 */


public class LoginResponeHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LoginRequestPacket packet = new LoginRequestPacket();
        packet.setUserId("11111");
        packet.setPassword("22222");
        packet.setUsername("astro");

        ctx.channel().writeAndFlush(packet);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        if (msg.isSuccess()) {
            System.out.println(new Date() + "--->客户端：登录成功！");
            LoginUtil.markAsLogin(ctx.channel());
        } else {
            System.out.println(new Date() + "--->客户端：登录失败，原因 -> " + msg.getResason());
        }

    }
}

