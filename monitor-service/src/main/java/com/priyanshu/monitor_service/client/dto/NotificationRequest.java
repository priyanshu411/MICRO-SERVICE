package com.priyanshu.monitor_service.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class NotificationRequest implements Serializable {
    private String to;
    private String subject;
    private String body;
}
