package com.propertycrm.app.dto.response;

import com.propertycrm.app.entity.UnitStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UnitResponse {

    private Long id;
    private String project;
    private String unitNo;
    private String tower;
    private Double area;
    private Double finalPrice;
    private UnitStatus status;
}