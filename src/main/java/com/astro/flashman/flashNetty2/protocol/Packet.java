package com.astro.flashman.flashNetty2.protocol;

import lombok.Data;

/**
 * @author lyh
 * @date 2018/9/19 
 * @version v
 */

@Data
public abstract class Packet {

    private byte version = 1;

    public abstract Byte getCommand();


}

