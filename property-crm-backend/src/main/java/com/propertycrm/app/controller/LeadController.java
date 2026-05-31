package com.propertycrm.app.controller;

import com.propertycrm.app.dto.request.FollowUpRequest;
import com.propertycrm.app.dto.request.LeadRequest;
import com.propertycrm.app.entity.LeadStatus;
import com.propertycrm.app.service.LeadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leads")
@RequiredArgsConstructor
public class LeadController {

    private final LeadService leadService;

    @PostMapping
    public Object createLead(
            @RequestBody LeadRequest request) {

        return leadService.createLead(request);
    }

    @GetMapping
    public Object getAllLeads() {

        return leadService.getAllLeads();
    }

    @GetMapping("/{id}")
    public Object getLead(
            @PathVariable Long id) {

        return leadService.getLead(id);
    }

    @PutMapping("/{leadId}/assign/{employeeId}")
    public Object assignLead(
            @PathVariable Long leadId,
            @PathVariable Long employeeId) {

        return leadService.assignLead(
                leadId,
                employeeId);
    }

    @PutMapping("/{leadId}/status")
    public Object updateStatus(
            @PathVariable Long leadId,
            @RequestParam LeadStatus status) {

        return leadService.updateStatus(
                leadId,
                status);
    }

    @PostMapping("/followup")
    public Object addFollowUp(
            @RequestBody FollowUpRequest request) {

        return leadService.addFollowUp(
                request);
    }
}