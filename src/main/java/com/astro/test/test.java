package com.astro.test;

import java.util.function.Supplier;

/**
 * @author lyh
 * @version v
 * @date 2018/8/1
 */


public class test {


    public static void main(String[] args) {
        Supplier sp = () -> "ssss";
        System.out.println(sp.get());
    }
}

