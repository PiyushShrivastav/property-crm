package com.propertycrm.app.service.impl;

import com.propertycrm.app.dto.response.DashboardSummaryResponse;
import com.propertycrm.app.entity.UnitStatus;
import com.propertycrm.app.repository.*;
import com.propertycrm.app.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl
        implements DashboardService {

    private final LeadRepository leadRepository;
    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;
    private final ProjectRepository projectRepository;
    private final UnitRepository unitRepository;
    private final CollectionRepository collectionRepository;
    private final PaymentScheduleRepository paymentScheduleRepository;

    @Override
    public DashboardSummaryResponse getSummary() {

        Double totalCollection =
                collectionRepository.getTotalCollection();

        Double pendingCollection =
                paymentScheduleRepository
                        .getPendingCollection();

        return DashboardSummaryResponse.builder()
                .totalLeads(
                        leadRepository.count())
                .totalCustomers(
                        customerRepository.count())
                .totalBookings(
                        bookingRepository.count())
                .totalProjects(
                        projectRepository.count())
                .totalUnits(
                        unitRepository.count())
                .bookedUnits(
                        unitRepository.countByStatus(
                                UnitStatus.BOOKED))
                .availableUnits(
                        unitRepository.countByStatus(
                                UnitStatus.AVAILABLE))
                .totalCollection(
                        totalCollection)
                .pendingCollection(
                        pendingCollection)
                .build();
    }
}