package com.propertycrm.app.repository;

import com.propertycrm.app.entity.PaymentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentScheduleRepository
        extends JpaRepository<PaymentSchedule, Long> {

    List<PaymentSchedule> findByBookingId(Long bookingId);

    @Query("""
            SELECT COALESCE(
                SUM(p.amount - p.paidAmount),0
            )
            FROM PaymentSchedule p
           """)
    Double getPendingCollection();
}