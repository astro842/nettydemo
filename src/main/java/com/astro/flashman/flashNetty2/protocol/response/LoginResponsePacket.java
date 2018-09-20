package com.astro.flashman.flashNetty2.protocol.response;

import static com.astro.flashman.flashNetty2.protocol.Command.Command.LOGIN_RESPONSE;

import com.astro.flashman.flashNetty2.protocol.Packet;

import lombok.Data;

/**
 * @author lyh
 * @date 2018/9/20 
 * @version v
 */

@Data
public class LoginResponsePacket extends Packet {

    private boolean success;
    private String resason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }


}

