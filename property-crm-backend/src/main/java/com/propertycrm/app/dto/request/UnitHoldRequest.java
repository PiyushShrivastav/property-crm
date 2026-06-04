package com.propertycrm.app.dto.request;

import lombok.Data;

@Data
public class UnitHoldRequest {

    private Long unitId;

    private String customerName;

    private String mobile;

    private Double tokenAmount;

    // hold duration in hours
    private Integer holdHours;
}