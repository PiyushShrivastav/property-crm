package com.propertycrm.app.repository;

import com.propertycrm.app.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository
        extends JpaRepository<Booking, Long> {

    boolean existsByUnitId(Long unitId);
}