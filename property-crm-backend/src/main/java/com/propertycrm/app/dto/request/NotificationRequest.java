package com.propertycrm.app.dto.request;

import com.propertycrm.app.entity.NotificationType;
import lombok.Data;

@Data
public class NotificationRequest {

    private Long userId;

    private String title;

    private String message;

    private NotificationType type;
}