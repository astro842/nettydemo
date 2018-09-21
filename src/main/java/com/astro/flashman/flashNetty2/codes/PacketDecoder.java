package com.astro.flashman.flashNetty2.codes;

import java.util.List;

import com.astro.flashman.flashNetty2.protocol.PacketCodeC;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * @author lyh
 * @date 2018/9/21 
 * @version v
 */


public class PacketDecoder extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(PacketCodeC.INSTANCE.decode(in));
    }
}

