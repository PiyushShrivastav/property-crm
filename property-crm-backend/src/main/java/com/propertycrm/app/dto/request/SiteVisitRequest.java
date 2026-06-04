package com.propertycrm.app.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SiteVisitRequest {

    private Long leadId;

    private Long employeeId;

    private LocalDateTime visitDateTime;

    private String visitLocation;
}