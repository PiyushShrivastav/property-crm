package com.propertycrm.app.repository;

import com.propertycrm.app.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository
        extends JpaRepository<Department, Long> {
}