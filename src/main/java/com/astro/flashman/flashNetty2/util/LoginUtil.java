package com.astro.flashman.flashNetty2.util;

import com.astro.flashman.flashNetty2.Attributes.Attributes;

import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @author lyh
 * @date 2018/9/20 
 * @version v
 */


public class LoginUtil {

    //登录成功加标志
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    //判断是否有登录成功的标志
    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> attr = channel.attr(Attributes.LOGIN);
        return attr.get() != null;
    }
}

