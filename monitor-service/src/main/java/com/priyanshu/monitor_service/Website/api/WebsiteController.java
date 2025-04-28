package com.priyanshu.monitor_service.Website.api;

import com.priyanshu.monitor_service.Website.dto.Website;
import com.priyanshu.monitor_service.Website.service.IWebsiteService;
import com.priyanshu.monitor_service.database.entity.WebsiteStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monitor/api/websites")
public class WebsiteController {

    private final IWebsiteService websiteService;

    public WebsiteController(IWebsiteService websiteService) {
        this.websiteService =websiteService;
    }

    @PostMapping
    public WebsiteStatus addWebsite(@RequestBody Website website) {
        return websiteService.addWebsite(website);
    }

    @GetMapping
    public List<WebsiteStatus> getAllWebsites() {
        return websiteService.getAllWebsite();
    }
}

