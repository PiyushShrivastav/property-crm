package com.propertycrm.app.repository;

import com.propertycrm.app.entity.Lead;
import com.propertycrm.app.entity.LeadStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeadRepository
        extends JpaRepository<Lead, Long> {

    List<Lead> findByStatus(LeadStatus status);

    List<Lead> findByAssignedEmployeeId(Long employeeId);

    boolean existsByMobile(String mobile);
    
    long countByStatus(LeadStatus status);
}