package com.priyanshu.monitor_service.Website.service;

import com.priyanshu.monitor_service.Website.dto.Website;
import com.priyanshu.monitor_service.database.entity.WebsiteStatus;

import java.util.List;

public interface IWebsiteService {
    WebsiteStatus addWebsite(Website website);
    List<WebsiteStatus> getAllWebsite();
}
