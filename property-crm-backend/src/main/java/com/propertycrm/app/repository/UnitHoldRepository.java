package com.propertycrm.app.repository;

import com.propertycrm.app.entity.UnitHold;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UnitHoldRepository
        extends JpaRepository<UnitHold, Long> {

    List<UnitHold> findByActiveTrue();

    List<UnitHold> findByExpiryTimeBeforeAndActiveTrue(
            LocalDateTime time);
}