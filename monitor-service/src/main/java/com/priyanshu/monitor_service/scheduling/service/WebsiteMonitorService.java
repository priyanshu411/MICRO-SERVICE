package com.priyanshu.monitor_service.scheduling.service;

import com.priyanshu.monitor_service.client.NotificationClient;
import com.priyanshu.monitor_service.client.dto.NotificationRequest;
import com.priyanshu.monitor_service.database.entity.WebsiteStatus;
import com.priyanshu.monitor_service.database.repository.DbFactory;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
public class WebsiteMonitorService {

    private final DbFactory dbFactory;
    private NotificationClient notificationClient;
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${alert.email.body}")
    private String htmlBody;
    @Value("${alert.email.subject}")
    private String subject;



    public WebsiteMonitorService(DbFactory dbFactory,NotificationClient notificationClient) {
        this.dbFactory=dbFactory;
        this.notificationClient=notificationClient;
    }


    @Scheduled(cron = "${monitor.check-interval}")
    public void monitorWebsites() {
        List<WebsiteStatus> websites = dbFactory.getWebsiteStatusRepository().findAll();
        for (WebsiteStatus website : websites) {
            String previousStatus=website.getStatus();
            try {
                restTemplate.getForEntity(website.getUrl(), String.class);
                website.setStatus("UP");
                website.setPreviousStatus(previousStatus);
            } catch (Exception e) {
                website.setStatus("DOWN");
                website.setPreviousStatus(previousStatus);
//                if (previousStatus.equals("UP")) {
                    String html = htmlBody
                            .replace("{{website_url}}", website.getUrl())
                            .replace("{{timestamp}}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

                    notificationClient.sendDownNotification(new NotificationRequest(website.getAlertEmail(), subject, html));
//                }
            }
            website.setLastCheckedAt(LocalDateTime.now());
            log.info("\nurl info :{}",website);
            dbFactory.getWebsiteStatusRepository().save(website);
        }
    }
}
