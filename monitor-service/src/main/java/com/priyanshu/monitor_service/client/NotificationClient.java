package com.priyanshu.monitor_service.client;

import com.priyanshu.monitor_service.client.dto.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "notification-service")
public interface NotificationClient {

    @PostMapping("/api/notify/email")
    void sendDownNotification(@RequestBody NotificationRequest payload);
}
