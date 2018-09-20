package com.astro.flashman.flashNetty2.Attributes;

import io.netty.util.AttributeKey;

/**
 * @author lyh
 * @date 2018/9/20 
 * @version v
 */

public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
