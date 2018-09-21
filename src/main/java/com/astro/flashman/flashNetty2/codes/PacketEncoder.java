package com.astro.flashman.flashNetty2.codes;

import com.astro.flashman.flashNetty2.protocol.Packet;
import com.astro.flashman.flashNetty2.protocol.PacketCodeC;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author lyh
 * @date 2018/9/21 
 * @version v
 */


public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        PacketCodeC.INSTANCE.encode1(out, msg);
    }
}
