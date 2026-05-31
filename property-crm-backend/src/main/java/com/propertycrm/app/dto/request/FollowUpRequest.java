package com.propertycrm.app.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FollowUpRequest {

    private Long leadId;

    private String remarks;

    private LocalDateTime nextFollowUpDate;
}