package com.propertycrm.app.repository;

import com.propertycrm.app.entity.WhatsappLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WhatsappLogRepository
        extends JpaRepository<WhatsappLog, Long> {
}