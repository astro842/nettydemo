package com.astro.flashman.flashNetty2.protocol;

import com.alibaba.fastjson.JSON;

/**
 * @author lyh
 * @date 2018/9/19 
 * @version v
 */

public class JSONSerializer implements Serializer {

    /**
     * 序列化算法
     */
    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlogrithm.JSON;
    }

    /**
     * java 对象转换成二进制
     */
    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    /**
     * 二进制转换成 java 对象
     */
    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
