package com.vls.managementservice;

import com.vls.managementservice.controller.NotificationController;
import com.vls.managementservice.model.Notification;
import com.vls.managementservice.repository.NotificationRepository;
import com.vls.managementservice.service.NotificationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class NotificationControllerTest extends AbstractTest {
    private NotificationController notificationController;
    private NotificationService notificationService;

    @Mock
    private NotificationRepository notificationRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        notificationService = new NotificationService(notificationRepository);
        notificationController = new NotificationController(notificationService);
    }

    @Test
    public void getNotificationsByUserIdTest() {
        Notification notification = new Notification(UUID.randomUUID(), "description", "url" , new Date(), UUID.randomUUID());
        List<Notification> list = new ArrayList<>();
        list.add(notification);
        list.add(notification);

        Mockito.when(notificationRepository.getNotificationsByUserId(notification.getUser_id())).thenReturn(list);

        ResponseEntity<List<Notification>> response = notificationController.getListNotifications(notification.getUser_id().toString());
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(list.size(), response.getBody().size());

    }
}
