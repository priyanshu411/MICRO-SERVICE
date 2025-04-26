package com.priyanshu.monitor_service.database.repository;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
public class DbFactory {
    @Autowired
    private WebsiteStatusRepository websiteStatusRepository;
}
