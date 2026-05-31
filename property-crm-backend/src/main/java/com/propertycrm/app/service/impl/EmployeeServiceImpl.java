package com.propertycrm.app.service.impl;

import com.propertycrm.app.dto.request.EmployeeRequest;
import com.propertycrm.app.dto.response.EmployeeResponse;
import com.propertycrm.app.entity.*;
import com.propertycrm.app.repository.*;
import com.propertycrm.app.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl
        implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final DesignationRepository designationRepository;
    private final UserRepository userRepository;

    @Override
    public EmployeeResponse create(EmployeeRequest req) {

        Department department =
                departmentRepository.findById(
                        req.getDepartmentId()
                ).orElseThrow();

        Designation designation =
                designationRepository.findById(
                        req.getDesignationId()
                ).orElseThrow();

        User user =
                userRepository.findById(
                        req.getUserId()
                ).orElseThrow();

        Employee manager = null;

        if(req.getReportingManagerId() != null) {
            manager = employeeRepository.findById(
                    req.getReportingManagerId()
            ).orElseThrow();
        }

        Employee employee = Employee.builder()
                .employeeCode(req.getEmployeeCode())
                .firstName(req.getFirstName())
                .lastName(req.getLastName())
                .mobile(req.getMobile())
                .email(req.getEmail())
                .address(req.getAddress())
                .department(department)
                .designation(designation)
                .user(user)
                .reportingManager(manager)
                .build();

        employeeRepository.save(employee);

        return map(employee);
    }

    @Override
    public EmployeeResponse getById(Long id) {
        return map(employeeRepository.findById(id)
                .orElseThrow());
    }

    @Override
    public List<EmployeeResponse> getAll() {

        return employeeRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public EmployeeResponse update(
            Long id,
            EmployeeRequest req) {

        Employee employee =
                employeeRepository.findById(id)
                        .orElseThrow();

        employee.setFirstName(req.getFirstName());
        employee.setLastName(req.getLastName());
        employee.setMobile(req.getMobile());
        employee.setEmail(req.getEmail());
        employee.setAddress(req.getAddress());

        employeeRepository.save(employee);

        return map(employee);
    }

    @Override
    public void deactivate(Long id) {

        Employee employee =
                employeeRepository.findById(id)
                        .orElseThrow();

        employee.setActive(false);

        employeeRepository.save(employee);
    }

    private EmployeeResponse map(Employee e) {

        return EmployeeResponse.builder()
                .id(e.getId())
                .employeeCode(e.getEmployeeCode())
                .name(e.getFirstName() + " " + e.getLastName())
                .mobile(e.getMobile())
                .email(e.getEmail())
                .department(
                        e.getDepartment() != null
                                ? e.getDepartment().getName()
                                : null
                )
                .designation(
                        e.getDesignation() != null
                                ? e.getDesignation().getTitle()
                                : null
                )
                .manager(
                        e.getReportingManager() != null
                                ? e.getReportingManager().getFirstName()
                                : null
                )
                .build();
    }
}