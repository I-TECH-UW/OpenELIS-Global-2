package org.openelisglobal.notifications.dao;

import java.util.List;

import org.openelisglobal.notifications.entity.Notification;

public interface NotificationDAO {
    
    void save(Notification notification);

    List<Notification> getNotifications();

    // Set<Notification> getNotificationsByUserId(Long userId);
}
