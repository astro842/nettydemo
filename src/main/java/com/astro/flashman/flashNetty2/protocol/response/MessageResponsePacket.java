package com.astro.flashman.flashNetty2.protocol.response;

import static com.astro.flashman.flashNetty2.protocol.Command.Command.MESSAGE_RESPONSE;

import com.astro.flashman.flashNetty2.protocol.Packet;

import lombok.Data;

/**
 * @author lyh
 * @date 2018/9/20 
 * @version v
 */

@Data
public class MessageResponsePacket extends Packet {
    
    private String message;

    @Override
    public Byte getCommand() {

        return MESSAGE_RESPONSE;
    }
}

