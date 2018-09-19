package com.astro.flashman.flashNetty2.protocol;

import java.util.HashMap;
import java.util.Map;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author lyh
 * @date 2018/9/19 
 * @version v
 */


public class PacketCodeC {


    private static final int MAGIC_NUMBER = 0x12345678;
    private static final Map<Byte, Class<? extends Packet>> packetTypeMap = new HashMap<>();
    private static final Map<Byte, Serializer> serializerMap = new HashMap<>();

    //编码
    public ByteBuf encode(ByteBufAllocator all, Packet packet) {
        //1.创建bytebuf对象
        //ByteBuf buf = ByteBufAllocator.DEFAULT.ioBuffer();
        ByteBuf buf = all.ioBuffer();
        //2.序列化Java对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        //3.实际编码过程
        //协议 (魔数4 版本号1 序列化算法1 指令1 数据长度4 数据n)
        buf.writeInt(MAGIC_NUMBER);
        buf.writeByte(packet.getVersion());
        buf.writeByte(Serializer.DEFAULT.getSerializerAlogrithm());
        buf.writeByte(packet.getCommand());
        buf.writeByte(bytes.length);
        buf.writeBytes(bytes);

        return buf;
    }

    //解码
    //协议 (魔数4 版本号1 序列化算法1 指令1 数据长度4 数据n)
    public Packet decode(ByteBuf byteBuf) {
        //在byteBuf中获取鞋业信息
        //先跳过魔数和版本号
        byteBuf.skipBytes(4);
        byteBuf.skipBytes(1);

        byte serializeAlgorithm = byteBuf.readByte();
        byte command = byteBuf.readByte();
        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        //用获取的指令1和序列化算法1 来得到对应的
        //requestType -》序列化的object
        //serializer  -》序列化的解析器
        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);
        if (null != requestType && null != serializer) {
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }

    //通过command指令获取序列化的object类型
    private Class<? extends Packet> getRequestType(byte command) {
        return packetTypeMap.get(command);
    }

    //通过序列话算法指令 获取对应的序列化算法
    private Serializer getSerializer(byte serializeAlgorithm) {
        return serializerMap.get(serializeAlgorithm);
    }
}

