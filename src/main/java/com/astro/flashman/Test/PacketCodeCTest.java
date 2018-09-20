package com.astro.flashman.Test;


import com.astro.flashman.flashNetty2.protocol.Packet;
import com.astro.flashman.flashNetty2.protocol.PacketCodeC;
import com.astro.flashman.flashNetty2.protocol.request.LoginRequestPacket;
import com.astro.flashman.flashNetty2.serializer.Serializer;
import com.astro.flashman.flashNetty2.serializer.impl.JSONSerializer;

import io.netty.buffer.ByteBuf;


/**
 * @author lyh
 * @date 2018/9/19 
 * @version v
 */


public class PacketCodeCTest {


    public void encode() {

        Serializer serializer = new JSONSerializer();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setVersion(((byte) 1));
        loginRequestPacket.setUserId("123");
        loginRequestPacket.setUsername("zhangsan");
        loginRequestPacket.setPassword("password");

        PacketCodeC packetCodeC = new PacketCodeC();
        ByteBuf byteBuf = packetCodeC.encode(null, loginRequestPacket);

        Packet decodedPacket = packetCodeC.decode(byteBuf);

        //Assert.assertArrayEquals(serializer.serialize(loginRequestPacket), serializer.serialize(decodedPacket));
        byte[] bytes = serializer.serialize(loginRequestPacket);
        byte[] bytes1 = serializer.serialize(decodedPacket);

        System.out.println("----------------------------->" + bytes.equals(bytes1));
        System.out.println("------------- bytes.length---------------->" + bytes.length);
        System.out.println("------------- bytes1.length---------------->" + bytes1.length);

    }
}

