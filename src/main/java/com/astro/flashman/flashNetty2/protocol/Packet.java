package com.astro.flashman.flashNetty2.protocol;

/**
 * @author lyh
 * @date 2018/9/19 
 * @version v
 */


public abstract class Packet {

    private byte version = 1;

    public abstract Byte getCommand();

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }
}

