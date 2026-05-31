package com.propertycrm.app.controller;

import com.propertycrm.app.dto.request.EmployeeRequest;
import com.propertycrm.app.dto.response.EmployeeResponse;
import com.propertycrm.app.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public EmployeeResponse create(
            @RequestBody EmployeeRequest request) {

        return employeeService.create(request);
    }

    @GetMapping("/{id}")
    public EmployeeResponse getById(
            @PathVariable Long id) {

        return employeeService.getById(id);
    }

    @GetMapping
    public List<EmployeeResponse> getAll() {

        return employeeService.getAll();
    }

    @PutMapping("/{id}")
    public EmployeeResponse update(
            @PathVariable Long id,
            @RequestBody EmployeeRequest request) {

        return employeeService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deactivate(
            @PathVariable Long id) {

        employeeService.deactivate(id);
    }
}