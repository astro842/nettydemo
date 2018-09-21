package com.astro.flashman.flashNetty2.flashserver.handler;

import java.util.Date;

import com.astro.flashman.flashNetty2.protocol.request.LoginRequestPacket;
import com.astro.flashman.flashNetty2.protocol.response.LoginResponsePacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author lyh
 * @date 2018/9/21 
 * @version v
 */


public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        System.out.println(new Date() + "服务端：接受客户端的登录请求。。。。");
        LoginResponsePacket packet = new LoginResponsePacket();
        packet.setVersion(msg.getVersion());
        if (vaild(msg)) {
            System.out.println(new Date() + "服务端：客户端" + msg.getUsername() + " 登录成功");
            packet.setSuccess(true);
        } else {
            System.out.println(new Date() + "服务端：客户端 登录失败");
            packet.setResason("校验失败！");
        }
        ctx.channel().writeAndFlush(packet);
    }

    private boolean vaild(LoginRequestPacket msg) {
        return true;
    }
}

