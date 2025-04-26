package com.priyanshu.monitor_service.database.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Data
@ToString
@Getter
@Setter
public class WebsiteStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String status;
    private LocalDateTime lastCheckedAt;
}
