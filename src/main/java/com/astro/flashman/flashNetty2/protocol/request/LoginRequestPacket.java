package com.astro.flashman.flashNetty2.protocol.request;

import static com.astro.flashman.flashNetty2.protocol.Command.Command.LOGIN_REQUEST;

import com.astro.flashman.flashNetty2.protocol.Packet;

import lombok.Data;

/**
 * @author lyh
 * @date 2018/9/19 
 * @version v
 */

@Data
public class LoginRequestPacket extends Packet {

    private String userId;
    private String username;
    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }

}

