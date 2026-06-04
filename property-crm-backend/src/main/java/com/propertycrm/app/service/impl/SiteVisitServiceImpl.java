package com.propertycrm.app.service.impl;

import com.propertycrm.app.dto.request.SiteVisitFeedbackRequest;
import com.propertycrm.app.dto.request.SiteVisitRequest;
import com.propertycrm.app.dto.response.SiteVisitResponse;
import com.propertycrm.app.entity.*;
import com.propertycrm.app.repository.*;
import com.propertycrm.app.service.SiteVisitService;
import com.propertycrm.app.service.WhatsappService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SiteVisitServiceImpl
        implements SiteVisitService {

    private final SiteVisitRepository siteVisitRepository;
    private final LeadRepository leadRepository;
    private final EmployeeRepository employeeRepository;
    private final WhatsappService whatsappService;

    @Override
    public SiteVisitResponse scheduleVisit(
            SiteVisitRequest request) {

        Lead lead =
                leadRepository.findById(
                        request.getLeadId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Lead not found"));

        Employee employee =
                employeeRepository.findById(
                        request.getEmployeeId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Employee not found"));

        SiteVisit visit =
                SiteVisit.builder()
                        .lead(lead)
                        .employee(employee)
                        .visitDateTime(
                                request.getVisitDateTime())
                        .visitLocation(
                                request.getVisitLocation())
                        .status(
                                SiteVisitStatus.SCHEDULED)
                        .build();

        visit = siteVisitRepository.save(visit);
        
        String message =
                "Your site visit is scheduled on "
                + request.getVisitDateTime()
                + " at "
                + request.getVisitLocation();

        whatsappService.sendMessage(
                lead.getMobile(),
                message,
                WhatsappMessageType.SITE_VISIT
        );

        lead.setStatus(
                LeadStatus.SITE_VISIT_SCHEDULED);

        leadRepository.save(lead);

        return map(visit);
    }

    @Override
    public SiteVisitResponse addFeedback(
            Long visitId,
            SiteVisitFeedbackRequest request) {

        SiteVisit visit =
                siteVisitRepository.findById(visitId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Visit not found"));

        visit.setFeedback(request.getFeedback());

        visit.setStatus(request.getStatus());

        visit = siteVisitRepository.save(visit);

        if(request.getStatus()
                == SiteVisitStatus.COMPLETED) {

            Lead lead = visit.getLead();

            lead.setStatus(
                    LeadStatus.SITE_VISIT_DONE);

            leadRepository.save(lead);
        }

        return map(visit);
    }

    @Override
    public List<SiteVisitResponse> getAllVisits() {

        return siteVisitRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<SiteVisitResponse>
    getEmployeeVisits(Long employeeId) {

        return siteVisitRepository
                .findByEmployeeId(employeeId)
                .stream()
                .map(this::map)
                .toList();
    }

    private SiteVisitResponse map(
            SiteVisit visit) {

        return SiteVisitResponse.builder()
                .id(visit.getId())
                .customerName(
                        visit.getLead()
                                .getCustomerName())
                .employeeName(
                        visit.getEmployee()
                                .getFirstName()
                                + " "
                                + visit.getEmployee()
                                .getLastName())
                .visitDateTime(
                        visit.getVisitDateTime())
                .visitLocation(
                        visit.getVisitLocation())
                .feedback(
                        visit.getFeedback())
                .status(
                        visit.getStatus())
                .build();
    }
}