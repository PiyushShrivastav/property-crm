package com.propertycrm.app.dto.request;

import lombok.Data;

@Data
public class EmployeeRequest {

    private String employeeCode;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private String address;

    private Long departmentId;
    private Long designationId;
    private Long userId;
    private Long reportingManagerId;
}