package org.openelisglobal.notifications.dao;

import java.util.List;

import org.openelisglobal.notifications.entity.Notifications;



public interface NotificationDAO {

    void save(Notifications notification);

    // void update(Notification notification);

    // List<Notification> getNotificationsByUserId(Long userId);

    // Notification getNotificationById(Long id);

    List<Notifications> getAllNotifications();







}
