package com.propertycrm.app.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeResponse {

    private Long id;
    private String employeeCode;
    private String name;
    private String mobile;
    private String email;
    private String department;
    private String designation;
    private String manager;
}