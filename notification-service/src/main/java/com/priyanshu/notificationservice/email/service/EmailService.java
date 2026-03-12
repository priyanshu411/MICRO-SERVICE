package com.priyanshu.notificationservice.email.service;

import com.priyanshu.notificationservice.email.dto.NotificationRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService implements IEmailService{


    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void sendDownAlert(NotificationRequest request) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        String to=request.getTo().trim();
        helper.setTo(to);
        helper.setSubject(request.getSubject());
        helper.setText(request.getBody(), true); // 'true' means it's HTML
//            mailSender.send(mimeMessage);

    }

}








