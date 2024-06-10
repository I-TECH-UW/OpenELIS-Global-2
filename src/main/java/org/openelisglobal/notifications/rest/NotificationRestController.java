package org.openelisglobal.notifications.rest;

import java.time.OffsetDateTime;
import java.util.List;

import org.openelisglobal.notifications.dao.NotificationDAO;
import org.openelisglobal.notifications.entity.Notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class NotificationRestController {

    // define field for notification service

    private final NotificationDAO notificationDAO;

    // inject notification service using constructor injection
    @Autowired
    public NotificationRestController(NotificationDAO notificationDAO) {
        this.notificationDAO = notificationDAO;
    }


    // define endpoint for "/notifications"

    @GetMapping("/notifications")
    public List<Notifications> getAllNotifications() {
        return notificationDAO.getAllNotifications();
    }


    @PostMapping("/notification")
    public void saveNotification(@RequestBody Notifications notification) {
        notification.setCreatedDate(OffsetDateTime.now());
        notificationDAO.save(notification);
    }





}
