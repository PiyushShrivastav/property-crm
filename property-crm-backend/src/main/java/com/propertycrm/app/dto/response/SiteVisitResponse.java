package com.propertycrm.app.dto.response;

import com.propertycrm.app.entity.SiteVisitStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SiteVisitResponse {

    private Long id;

    private String customerName;

    private String employeeName;

    private LocalDateTime visitDateTime;

    private String visitLocation;

    private String feedback;

    private SiteVisitStatus status;
}