package com.propertycrm.app.repository;

import com.propertycrm.app.entity.LeadFollowUp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadFollowUpRepository
        extends JpaRepository<LeadFollowUp, Long> {
}