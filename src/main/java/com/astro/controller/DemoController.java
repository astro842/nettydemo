package com.astro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by astro on 2018/6/25.
 */
@RestController
public class DemoController {

    @RequestMapping("/hello")
    public String test1(){
        return "astro";
    }
}
