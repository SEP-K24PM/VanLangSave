package com.vls.managementservice.controller;

import java.util.List;
import java.util.UUID;

import com.vls.managementservice.model.Notification;
import com.vls.managementservice.service.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RequestMapping("/list/{userId}")
    public ResponseEntity<List<Notification>> getListNotifications(@PathVariable("userId") String userId) {
        List<Notification> list = notificationService.getNotificationsByUserId(UUID.fromString(userId));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
