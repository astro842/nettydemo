package com.astro.flashman.flashNetty2.protocol;

/**
 * @author lyh
 * @date 2018/9/19 
 * @version v
 */

public interface Serializer {

    /**
     * json 序列化（序列化算法的类型以及默认序列化算法）
     */
    byte JSON_SERIALIZER = 1;

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法 标识
     */
    byte getSerializerAlogrithm();

    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);


}
