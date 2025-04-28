package com.priyanshu.monitor_service.Website.service;

import com.priyanshu.monitor_service.Website.dto.Website;
import com.priyanshu.monitor_service.database.entity.WebsiteStatus;
import com.priyanshu.monitor_service.database.repository.DbFactory;
import com.priyanshu.monitor_service.utility.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WebsiteService implements IWebsiteService{

    @Autowired
    DbFactory dbFactory;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WebsiteStatus addWebsite(Website website) {
        WebsiteStatus websiteStatus=new WebsiteStatus();
        websiteStatus.setUrl(website.getUrl());
        websiteStatus.setStatus(Constant.UNKNOWN);
        return dbFactory.getWebsiteStatusRepository().save(websiteStatus);
    }

    @Override
    public List<WebsiteStatus> getAllWebsite() {
        return dbFactory.getWebsiteStatusRepository().findAll();
    }
}
