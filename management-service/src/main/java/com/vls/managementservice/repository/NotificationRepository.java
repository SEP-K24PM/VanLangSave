package com.vls.managementservice.repository;

import java.util.List;
import java.util.UUID;

import com.vls.managementservice.model.Notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID>{
    
    @Query("select n from Notification n where n.user_id = :userId")
    public List<Notification> getNotificationsByUserId(UUID userId);
}
