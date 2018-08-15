package com.astro.timer.test;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author lyh
 * @version v
 * @date 2018/8/6
 */


public class TimerTest {
    @Bean
    public AsyncTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("yhh-schedule-");
        executor.setMaxPoolSize(10);
        executor.setCorePoolSize(3);
        executor.setQueueCapacity(0);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        return executor;
    }

    @Scheduled(cron = "0/1 * * * * ?")
    @Async
    public void sc1() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " | sc1 " + System.currentTimeMillis());
        while (true) {
            Thread.sleep(1000 * 5);
        }
    }


}

