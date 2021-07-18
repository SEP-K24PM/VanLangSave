package com.vls.managementservice.service;

import java.util.List;
import java.util.UUID;

import com.vls.managementservice.model.Notification;
import com.vls.managementservice.repository.NotificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getNotificationsByUserId(UUID userId) {
        return notificationRepository.getNotificationsByUserId(userId);
    }
}
