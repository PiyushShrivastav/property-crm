package com.propertycrm.app.repository;

import com.propertycrm.app.entity.SiteVisit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SiteVisitRepository
        extends JpaRepository<SiteVisit, Long> {

    List<SiteVisit> findByEmployeeId(Long employeeId);

    List<SiteVisit> findByLeadId(Long leadId);
}