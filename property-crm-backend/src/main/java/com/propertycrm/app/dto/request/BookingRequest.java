package com.propertycrm.app.dto.request;

import lombok.Data;

@Data
public class BookingRequest {

    private Long customerId;

    private Long projectId;

    private Long unitId;

    private Double bookingAmount;
}