package com.priyanshu.monitor_service.scheduling.service;

import com.priyanshu.monitor_service.anotation.MonitoredScheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
public class ErrorService {

    @MonitoredScheduler(jobName = "error_job")
    @Scheduled(cron = "${monitor.check-interval}")
    public void throeException() throws InterruptedException {

        int randomDelay = ThreadLocalRandom.current().nextInt(1, 5); // 1–4 seconds
        log.info("delay time :{}sec",randomDelay);
        Thread.sleep(randomDelay * 1000);
        if(randomDelay%3==0){
            throw new IndexOutOfBoundsException();
        }
        if(randomDelay%2==0){
            throw new NullPointerException();
        }
    }

}
