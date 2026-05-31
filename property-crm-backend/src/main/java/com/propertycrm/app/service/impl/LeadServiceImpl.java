package com.propertycrm.app.service.impl;

import com.propertycrm.app.dto.request.FollowUpRequest;
import com.propertycrm.app.dto.request.LeadRequest;
import com.propertycrm.app.dto.response.LeadResponse;
import com.propertycrm.app.entity.Employee;
import com.propertycrm.app.entity.Lead;
import com.propertycrm.app.entity.LeadFollowUp;
import com.propertycrm.app.entity.LeadStatus;
import com.propertycrm.app.repository.EmployeeRepository;
import com.propertycrm.app.repository.LeadFollowUpRepository;
import com.propertycrm.app.repository.LeadRepository;
import com.propertycrm.app.service.LeadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeadServiceImpl implements LeadService {

    private final LeadRepository leadRepository;
    private final EmployeeRepository employeeRepository;
    private final LeadFollowUpRepository leadFollowUpRepository;

    @Override
    public LeadResponse createLead(LeadRequest request) {

        if (leadRepository.existsByMobile(request.getMobile())) {
            throw new RuntimeException(
                    "Lead already exists with mobile : "
                            + request.getMobile()
            );
        }

        Employee employee =
                employeeRepository.findById(
                        request.getAssignedEmployeeId()
                ).orElseThrow(() ->
                        new RuntimeException("Employee not found"));

        Lead lead = Lead.builder()
                .customerName(request.getCustomerName())
                .mobile(request.getMobile())
                .email(request.getEmail())
                .city(request.getCity())
                .budget(request.getBudget())
                .remarks(request.getRemarks())
                .source(request.getSource())
                .status(LeadStatus.NEW)
                .assignedEmployee(employee)
                .createdAt(LocalDateTime.now())
                .build();

        lead = leadRepository.save(lead);

        return mapToResponse(lead);
    }

    @Override
    public List<LeadResponse> getAllLeads() {

        return leadRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public LeadResponse getLead(Long id) {

        Lead lead = leadRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Lead not found"));

        return mapToResponse(lead);
    }

    @Override
    public LeadResponse assignLead(
            Long leadId,
            Long employeeId) {

        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() ->
                        new RuntimeException("Lead not found"));

        Employee employee =
                employeeRepository.findById(employeeId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Employee not found"));

        lead.setAssignedEmployee(employee);

        lead = leadRepository.save(lead);

        return mapToResponse(lead);
    }

    @Override
    public LeadResponse updateStatus(
            Long leadId,
            LeadStatus status) {

        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() ->
                        new RuntimeException("Lead not found"));

        lead.setStatus(status);

        lead = leadRepository.save(lead);

        return mapToResponse(lead);
    }

    @Override
    public String addFollowUp(
            FollowUpRequest request) {

        Lead lead = leadRepository.findById(
                request.getLeadId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Lead not found"));

        LeadFollowUp followUp =
                LeadFollowUp.builder()
                        .lead(lead)
                        .remarks(request.getRemarks())
                        .nextFollowUpDate(
                                request.getNextFollowUpDate()
                        )
                        .build();

        leadFollowUpRepository.save(followUp);

        lead.setStatus(LeadStatus.FOLLOW_UP);

        leadRepository.save(lead);

        return "Follow Up Added Successfully";
    }

    private LeadResponse mapToResponse(
            Lead lead) {

        return LeadResponse.builder()
                .id(lead.getId())
                .customerName(lead.getCustomerName())
                .mobile(lead.getMobile())
                .email(lead.getEmail())
                .city(lead.getCity())
                .budget(lead.getBudget())
                .remarks(lead.getRemarks())
                .status(lead.getStatus())
                .source(lead.getSource())
                .assignedEmployee(
                        lead.getAssignedEmployee() != null
                                ? lead.getAssignedEmployee()
                                .getFirstName()
                                + " "
                                + lead.getAssignedEmployee()
                                .getLastName()
                                : null
                )
                .build();
    }
}