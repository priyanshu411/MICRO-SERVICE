package com.priyanshu.notificationservice.email.api;

import com.priyanshu.notificationservice.email.dto.NotificationRequest;
import com.priyanshu.notificationservice.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notify")
public class NotificationController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/email")
    public String sendEmail(@RequestBody NotificationRequest request) {

        emailService.sendDownAlert(request);
        return "Email sent successfully!";
    }
}
