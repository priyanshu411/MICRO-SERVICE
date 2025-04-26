package com.priyanshu.monitor_service.database.repository;

import com.priyanshu.monitor_service.database.entity.WebsiteStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebsiteStatusRepository extends JpaRepository< WebsiteStatus,Integer> {
}
