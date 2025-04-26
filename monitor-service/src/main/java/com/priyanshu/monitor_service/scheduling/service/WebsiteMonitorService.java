package com.priyanshu.monitor_service.scheduling.service;

import com.priyanshu.monitor_service.config.MonitorConfig;
import com.priyanshu.monitor_service.database.entity.WebsiteStatus;
import com.priyanshu.monitor_service.database.repository.DbFactory;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class WebsiteMonitorService {

    private final MonitorConfig monitorConfig;
    private final DbFactory dbFactory;
    private final RestTemplate restTemplate = new RestTemplate();


    public WebsiteMonitorService(MonitorConfig monitorConfig,DbFactory dbFactory) {
        this.monitorConfig = monitorConfig;
        this.dbFactory=dbFactory;
    }

    @PostConstruct
    public void init() {
        System.out.println("Websites to monitor: " + monitorConfig.getUrls());
    }

    @Scheduled(cron = "#{@monitorConfig.checkInterval}")
    public void monitorWebsites() {
        List<WebsiteStatus> websites = dbFactory.getWebsiteStatusRepository().findAll();
        for (WebsiteStatus website : websites) {
            try {
                restTemplate.getForEntity(website.getUrl(), String.class);
                website.setStatus("UP");
            } catch (Exception e) {
                website.setStatus("DOWN");
            }
            website.setLastCheckedAt(LocalDateTime.now());
            log.info("\nurl info :{}",website);
            dbFactory.getWebsiteStatusRepository().save(website);
        }
    }
}
