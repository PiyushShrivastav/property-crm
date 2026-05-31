package com.propertycrm.app.repository;

import com.propertycrm.app.entity.Designation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignationRepository
        extends JpaRepository<Designation, Long> {
}