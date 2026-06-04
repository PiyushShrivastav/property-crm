package com.propertycrm.app.service.impl;

import com.propertycrm.app.dto.request.NotificationRequest;
import com.propertycrm.app.dto.response.NotificationResponse;
import com.propertycrm.app.entity.Notification;
import com.propertycrm.app.entity.User;
import com.propertycrm.app.repository.NotificationRepository;
import com.propertycrm.app.repository.UserRepository;
import com.propertycrm.app.service.NotificationService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl
        implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Override
    public NotificationResponse createNotification(
            NotificationRequest request) {

        User user =
                userRepository.findById(
                        request.getUserId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"));

        Notification notification =
                Notification.builder()
                        .title(request.getTitle())
                        .message(request.getMessage())
                        .type(request.getType())
                        .createdAt(LocalDateTime.now())
                        .isRead(false)
                        .user(user)
                        .build();

        notification =
                notificationRepository.save(
                        notification);

        return map(notification);
    }

    @Override
    public List<NotificationResponse>
    getUserNotifications(Long userId) {

        return notificationRepository
                .findByUserIdOrderByCreatedAtDesc(
                        userId)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public String markAsRead(Long notificationId) {

        Notification notification =
                notificationRepository.findById(
                        notificationId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Notification not found"));

        notification.setIsRead(true);

        notificationRepository.save(notification);

        return "Notification marked as read";
    }

    private NotificationResponse map(
            Notification notification) {

        return NotificationResponse.builder()
                .id(notification.getId())
                .title(notification.getTitle())
                .message(notification.getMessage())
                .type(notification.getType())
                .isRead(notification.getIsRead())
                .createdAt(notification.getCreatedAt())
                .build();
    }
}