package com.propertycrm.app.service;

import com.propertycrm.app.dto.request.EmployeeRequest;
import com.propertycrm.app.dto.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse create(EmployeeRequest request);

    EmployeeResponse getById(Long id);

    List<EmployeeResponse> getAll();

    EmployeeResponse update(Long id,
                            EmployeeRequest request);

    void deactivate(Long id);
}