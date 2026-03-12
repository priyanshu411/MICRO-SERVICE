package com.priyanshu.notificationservice.email.service;

import com.priyanshu.notificationservice.email.dto.NotificationRequest;
import jakarta.mail.MessagingException;

public interface IEmailService {
     void sendDownAlert(NotificationRequest request) throws MessagingException;
}
