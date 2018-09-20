package com.astro.flashman.flashNetty2.protocol.Command;

/**
 * @author lyh
 * @date 2018/9/19 
 * @version v
 */

public interface Command {
    /**
     *  登录标识
     */
    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    /**
     *  信息标识
     */
    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;
}
