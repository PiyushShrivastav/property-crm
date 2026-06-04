package com.propertycrm.app.service;

import com.propertycrm.app.dto.request.NotificationRequest;

public interface NotificationService {

    Object createNotification(
            NotificationRequest request);

    Object getUserNotifications(Long userId);

    Object markAsRead(Long notificationId);
}