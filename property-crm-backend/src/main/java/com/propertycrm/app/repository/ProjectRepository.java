package com.propertycrm.app.repository;

import com.propertycrm.app.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository
        extends JpaRepository<Project, Long> {

    boolean existsByProjectCode(String projectCode);
}