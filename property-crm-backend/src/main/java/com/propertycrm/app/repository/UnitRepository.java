package com.propertycrm.app.repository;

import com.propertycrm.app.entity.Unit;
import com.propertycrm.app.entity.UnitStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnitRepository
        extends JpaRepository<Unit, Long> {

    List<Unit> findByProjectId(Long projectId);

    List<Unit> findByStatus(UnitStatus status);

    boolean existsByUnitNo(String unitNo);
    
    long countByStatus(UnitStatus status);
}