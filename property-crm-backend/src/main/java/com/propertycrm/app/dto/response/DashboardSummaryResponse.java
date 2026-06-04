package com.propertycrm.app.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardSummaryResponse {

    private Long totalLeads;

    private Long totalCustomers;

    private Long totalBookings;

    private Long totalProjects;

    private Long totalUnits;

    private Long bookedUnits;

    private Long availableUnits;

    private Double totalCollection;

    private Double pendingCollection;
}