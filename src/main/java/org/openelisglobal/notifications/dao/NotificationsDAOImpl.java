package org.openelisglobal.notifications.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.openelisglobal.notifications.entity.Notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class NotificationsDAOImpl implements NotificationDAO {

    // define field for entity manager
    private final EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public NotificationsDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // implement save method

    @Override
    @Transactional
    public void save(Notifications notification) {
        entityManager.persist(notification);
    }

    // implement getAllNotifications method

    @Override
    public List<Notifications> getAllNotifications() {
        return entityManager.createQuery("from Notifications", Notifications.class).getResultList();
    }






}
