package com.propertycrm.app.controller;

import com.propertycrm.app.dto.request.NotificationRequest;
import com.propertycrm.app.service.NotificationService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public Object createNotification(
            @RequestBody
            NotificationRequest request) {

        return notificationService
                .createNotification(request);
    }

    @GetMapping("/user/{userId}")
    public Object getUserNotifications(
            @PathVariable Long userId) {

        return notificationService
                .getUserNotifications(userId);
    }

    @PutMapping("/{notificationId}/read")
    public Object markAsRead(
            @PathVariable Long notificationId) {

        return notificationService
                .markAsRead(notificationId);
    }
}