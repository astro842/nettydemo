package com.astro.flashman.flashNetty2.protocol.request;

import static com.astro.flashman.flashNetty2.protocol.Command.Command.MESSAGE_REQUEST;

import com.astro.flashman.flashNetty2.protocol.Packet;

import lombok.Data;

/**
 * @author lyh
 * @date 2018/9/20 
 * @version v
 */

@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}

