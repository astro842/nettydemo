package com.astro.flashman.flashNetty2.flashserver;

import java.util.Date;

import com.astro.flashman.flashNetty2.protocol.Packet;
import com.astro.flashman.flashNetty2.protocol.PacketCodeC;
import com.astro.flashman.flashNetty2.protocol.request.LoginRequestPacket;
import com.astro.flashman.flashNetty2.protocol.request.MessageRequestPacket;
import com.astro.flashman.flashNetty2.protocol.response.LoginResponsePacket;
import com.astro.flashman.flashNetty2.protocol.response.MessageResponsePacket;

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
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(new Date() + "---》服务端：收到客户端");

        //解码
        Packet packet = PacketCodeC.INSTANCE.decode(buf);
        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket login = (LoginRequestPacket) packet;

            LoginResponsePacket response = new LoginResponsePacket();
            response.setVersion(packet.getVersion());
            //校验
            if (vaild(login)) {
                response.setSuccess(true);
                System.out.println(new Date() + "---》服务端：" + login.getUsername() + " 登录成功");
            } else {
                response.setSuccess(false);
                response.setResason("检验失败");
                System.out.println(new Date() + "---》服务端： 登录失败");
            }
            //编码返回客户端
            ByteBuf out = PacketCodeC.INSTANCE.encode(ctx.alloc(), response);
            ctx.channel().writeAndFlush(out);
        }
        if (packet instanceof MessageRequestPacket) {
            MessageRequestPacket request = (MessageRequestPacket) packet;
            System.out.println(new Date() + "---》服务端：收到客户端发来的信息 --> " + request.getMessage());
            MessageResponsePacket response = new MessageResponsePacket();
            response.setMessage("服务端已收到：" + request.getMessage());
            ByteBuf out = PacketCodeC.INSTANCE.encode(ctx.alloc(), response);
            ctx.channel().writeAndFlush(out);

        }

    }


    private boolean vaild(LoginRequestPacket login) {
        return true;
    }
}

