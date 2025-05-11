package com.priyanshu.notificationservice.email.service;

import com.priyanshu.notificationservice.email.dto.NotificationRequest;

public interface IEmailService {
     void sendDownAlert(NotificationRequest request);
}
