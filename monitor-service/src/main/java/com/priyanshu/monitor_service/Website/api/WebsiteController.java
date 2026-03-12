package com.priyanshu.monitor_service.Website.api;

import com.priyanshu.monitor_service.Website.dto.Website;
import com.priyanshu.monitor_service.Website.service.IWebsiteService;
import com.priyanshu.monitor_service.database.entity.WebsiteStatus;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.internal.log.SubSystemLogging;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
@Slf4j
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

    @GetMapping("/status/{code}")
    public ResponseEntity<String> simulateStatus(@PathVariable int code) throws InterruptedException {

        log.info("API_REQUEST endpoint=/status/{} status={} message=Start request", code, code);
        int randomDelay = ThreadLocalRandom.current().nextInt(1, 5); // 1–4 seconds
        log.info("delay time :{}sec", randomDelay);
        Thread.sleep(randomDelay * 1000);
        return ResponseEntity
                .status(code)
                .body("Status: " + code + " Delay: " + randomDelay + "s");
    }
}

