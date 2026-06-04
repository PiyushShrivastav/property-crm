package com.propertycrm.app.dto.request;

import lombok.Data;

@Data
public class CollectionRequest {

    private Long scheduleId;

    private Double amount;

    private String paymentMode;

    private String referenceNumber;
}