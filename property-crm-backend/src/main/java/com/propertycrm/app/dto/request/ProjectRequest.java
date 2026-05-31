package com.propertycrm.app.dto.request;

import lombok.Data;

@Data
public class ProjectRequest {

    private String projectCode;
    private String projectName;
    private String builderName;
    private String city;
    private String location;
    private Double totalArea;
    private Integer totalUnits;
}