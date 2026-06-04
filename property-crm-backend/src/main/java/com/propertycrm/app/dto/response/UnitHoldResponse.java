package com.propertycrm.app.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UnitHoldResponse {

    private Long id;

    private String customerName;

    private String mobile;

    private String unitNo;

    private Double tokenAmount;

    private LocalDateTime holdTime;

    private LocalDateTime expiryTime;

    private Boolean active;
}