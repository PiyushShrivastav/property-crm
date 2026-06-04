package com.propertycrm.app.dto.request;

import com.propertycrm.app.entity.SiteVisitStatus;
import lombok.Data;

@Data
public class SiteVisitFeedbackRequest {

    private String feedback;

    private SiteVisitStatus status;
}