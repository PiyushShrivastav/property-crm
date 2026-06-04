package com.propertycrm.app.controller;

import com.propertycrm.app.dto.request.SiteVisitFeedbackRequest;
import com.propertycrm.app.dto.request.SiteVisitRequest;
import com.propertycrm.app.service.SiteVisitService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/site-visits")
@RequiredArgsConstructor
public class SiteVisitController {

    private final SiteVisitService siteVisitService;

    @PostMapping
    public Object scheduleVisit(
            @RequestBody
            SiteVisitRequest request) {

        return siteVisitService
                .scheduleVisit(request);
    }

    @PutMapping("/{visitId}/feedback")
    public Object addFeedback(
            @PathVariable Long visitId,
            @RequestBody
            SiteVisitFeedbackRequest request) {

        return siteVisitService
                .addFeedback(
                        visitId,
                        request);
    }

    @GetMapping
    public Object getAllVisits() {

        return siteVisitService
                .getAllVisits();
    }

    @GetMapping("/employee/{employeeId}")
    public Object getEmployeeVisits(
            @PathVariable Long employeeId) {

        return siteVisitService
                .getEmployeeVisits(employeeId);
    }
}