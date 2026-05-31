package com.propertycrm.app.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectResponse {

    private Long id;
    private String projectCode;
    private String projectName;
    private String city;
    private String location;
    private Integer totalUnits;
}