package com.propertycrm.app.service;

import java.util.List;

import com.propertycrm.app.dto.request.FollowUpRequest;
import com.propertycrm.app.dto.request.LeadRequest;
import com.propertycrm.app.dto.response.LeadResponse;
import com.propertycrm.app.entity.LeadStatus;

public interface LeadService {

    LeadResponse createLead(LeadRequest request);

    List<LeadResponse> getAllLeads();

    LeadResponse getLead(Long id);

    LeadResponse assignLead(Long leadId,
                            Long employeeId);

    LeadResponse updateStatus(Long leadId,
                              LeadStatus status);

    String addFollowUp(FollowUpRequest request);
}