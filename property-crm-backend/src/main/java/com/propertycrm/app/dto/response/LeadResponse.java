package com.propertycrm.app.dto.response;

import com.propertycrm.app.entity.LeadSource;
import com.propertycrm.app.entity.LeadStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LeadResponse {

    private Long id;
    private String customerName;
    private String mobile;
    private String email;
    private String city;
    private Double budget;
    private String remarks;
    private LeadStatus status;
    private LeadSource source;
    private String assignedEmployee;
}