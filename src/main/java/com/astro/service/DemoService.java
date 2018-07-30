package com.astro.service;

import org.springframework.stereotype.Service;

/**
 * @author lyh
 * @version v
 * @date 2018/7/27
 */

@Service public class DemoService {


    public String test() throws InterruptedException {
        Thread.sleep(1000);
        return "DemoService";
    }
}

