package com.astro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class NettydemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NettydemoApplication.class, args);
    }
}
