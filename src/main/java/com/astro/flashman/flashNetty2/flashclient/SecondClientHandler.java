package com.astro.flashman.flashNetty2.flashclient;

import java.util.Date;

import com.astro.flashman.flashNetty2.protocol.Packet;
import com.astro.flashman.flashNetty2.protocol.PacketCodeC;
import com.astro.flashman.flashNetty2.protocol.request.LoginRequestPacket;
import com.astro.flashman.flashNetty2.protocol.response.LoginResponsePacket;
import com.astro.flashman.flashNetty2.protocol.response.MessageResponsePacket;
import com.astro.flashman.flashNetty2.util.LoginUtil;

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
        ByteBuf encode = PacketCodeC.INSTANCE.encode(ctx.alloc(), login);
        //写数据
        ctx.channel().writeAndFlush(encode);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(buf);
        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket responsePacket = (LoginResponsePacket) packet;
            if (responsePacket.isSuccess()) {
                LoginUtil.markAsLogin(ctx.channel());
                System.out.println(new Date() + "----> 客户端：" + "登录成功");
            } else {
                System.out.println(new Date() + "----> 客户端：" + "登录失败，原因：" + responsePacket.getResason());
            }
        }
        if (packet instanceof MessageResponsePacket) {
            MessageResponsePacket responsePacket = (MessageResponsePacket) packet;
            System.out.println(new Date() + "收到服务端发过来的 ->" + responsePacket.getMessage());
        }
    }

}

