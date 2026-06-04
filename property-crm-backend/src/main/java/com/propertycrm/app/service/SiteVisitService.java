package com.propertycrm.app.service;

import com.propertycrm.app.dto.request.SiteVisitFeedbackRequest;
import com.propertycrm.app.dto.request.SiteVisitRequest;

public interface SiteVisitService {

    Object scheduleVisit(
            SiteVisitRequest request);

    Object addFeedback(
            Long visitId,
            SiteVisitFeedbackRequest request);

    Object getAllVisits();

    Object getEmployeeVisits(Long employeeId);
}