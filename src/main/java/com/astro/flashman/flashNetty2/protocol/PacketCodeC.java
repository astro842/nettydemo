package com.astro.flashman.flashNetty2.protocol;

import static com.astro.flashman.flashNetty2.protocol.Command.Command.LOGIN_REQUEST;
import static com.astro.flashman.flashNetty2.protocol.Command.Command.LOGIN_RESPONSE;
import static com.astro.flashman.flashNetty2.protocol.Command.Command.MESSAGE_REQUEST;
import static com.astro.flashman.flashNetty2.protocol.Command.Command.MESSAGE_RESPONSE;

import java.util.HashMap;
import java.util.Map;

import com.astro.flashman.flashNetty2.protocol.request.LoginRequestPacket;
import com.astro.flashman.flashNetty2.protocol.request.MessageRequestPacket;
import com.astro.flashman.flashNetty2.protocol.response.LoginResponsePacket;
import com.astro.flashman.flashNetty2.protocol.response.MessageResponsePacket;
import com.astro.flashman.flashNetty2.serializer.Serializer;
import com.astro.flashman.flashNetty2.serializer.impl.JSONSerializer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author lyh
 * @date 2018/9/19 
 * @version v
 */


public class PacketCodeC {


    private static final int MAGIC_NUMBER = 0x12345678;
    public static final PacketCodeC INSTANCE = new PacketCodeC();
    //行为
    private final Map<Byte, Class<? extends Packet>> packetTypeMap;
    //序列化类型
    private final Map<Byte, Serializer> serializerMap;


    public PacketCodeC() {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlogrithm(), serializer);
    }


    //编码
    public ByteBuf encode(ByteBufAllocator all, Packet packet) {
        //1.创建bytebuf对象
        ByteBuf buf = all.ioBuffer();
        //2.序列化Java对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        //3.实际编码过程
        //协议 (魔数4 版本号1 序列化算法1 指令1 数据长度4 数据n)
        buf.writeInt(MAGIC_NUMBER);
        buf.writeByte(packet.getVersion());
        buf.writeByte(Serializer.DEFAULT.getSerializerAlogrithm());
        buf.writeByte(packet.getCommand());
        buf.writeInt(bytes.length);
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
        //requestType -》用command指令（用户行为），获取序列化的object
        Class<? extends Packet> requestType = getRequestType(command);
        //serializer  -》序列化的解析器
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

