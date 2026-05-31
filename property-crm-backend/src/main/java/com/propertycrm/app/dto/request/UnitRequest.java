package com.propertycrm.app.dto.request;

import com.propertycrm.app.entity.UnitType;
import lombok.Data;

@Data
public class UnitRequest {

    private Long projectId;

    private String unitNo;

    private String tower;

    private Integer floorNo;

    private Double area;

    private Double basePrice;

    private Double finalPrice;

    private UnitType unitType;
}