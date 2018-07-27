package com.astro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.astro.service.DemoService;

/**
 * Created by astro on 2018/6/25.
 */
@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/hello")
    public String test1() throws InterruptedException {
        demoService.test();
        return "astro";
    }
}
