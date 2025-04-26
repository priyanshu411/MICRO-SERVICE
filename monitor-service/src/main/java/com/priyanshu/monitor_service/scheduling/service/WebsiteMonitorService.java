package com.priyanshu.monitor_service.scheduling.service;

import com.priyanshu.monitor_service.config.MonitorConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class WebsiteMonitorService {

    private final MonitorConfig monitorConfig;

    public WebsiteMonitorService(MonitorConfig monitorConfig) {
        this.monitorConfig = monitorConfig;
    }

    @PostConstruct
    public void init() {
        System.out.println("Websites to monitor: " + monitorConfig.getUrls());
    }

    @Scheduled(cron = "#{@monitorConfig.checkInterval}")
    public void monitorWebsites() {
        for (String url : monitorConfig.getUrls()) {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int code = connection.getResponseCode();
                if (code == 200) {
                    System.out.println(url + " is UP");
                } else {
                    System.out.println(url + " is DOWN  | Status code: " + code);
                }
            } catch (Exception e) {
                System.out.println(url + " is DOWN | Error: " + e.getMessage());
            }
        }
    }
}
