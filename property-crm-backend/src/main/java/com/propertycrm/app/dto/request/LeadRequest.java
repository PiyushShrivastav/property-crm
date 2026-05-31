package com.propertycrm.app.dto.request;

import com.propertycrm.app.entity.LeadSource;
import lombok.Data;

@Data
public class LeadRequest {

    private String customerName;
    private String mobile;
    private String email;
    private String city;
    private Double budget;
    private String remarks;
    private LeadSource source;
    private Long assignedEmployeeId;
}