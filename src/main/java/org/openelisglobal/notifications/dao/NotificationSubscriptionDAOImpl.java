package org.openelisglobal.notifications.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.openelisglobal.notifications.entity.NotificationSubscriptions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class NotificationSubscriptionDAOImpl implements NotificationSubscriptionDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(NotificationSubscriptions notificationSubscription) {
        entityManager.merge(notificationSubscription);
    }

    @Override
    public NotificationSubscriptions getNotificationSubscriptionById(String id) {
        try {
            return entityManager.find(NotificationSubscriptions.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void updateNotificationSubscription(NotificationSubscriptions notificationSubscription) {
        entityManager.merge(notificationSubscription);
    }

    // Update saveOrUpdate method
@Transactional
public void saveOrUpdate(NotificationSubscriptions notificationSubscription) {
    if (notificationSubscription.getUser() == null) {
        throw new IllegalArgumentException("UserId must be provided");
    }

    NotificationSubscriptions existingSubscription = getNotificationSubscriptionById(notificationSubscription.getUser().getId());

    if (existingSubscription == null) {
        // Create a new subscription
        entityManager.persist(notificationSubscription); // Use persist() for new entities
    } else {
        // Update the existing subscription
        existingSubscription.setPfEndpoint(notificationSubscription.getPfEndpoint());
        existingSubscription.setPfP256dh(notificationSubscription.getPfP256dh());
        existingSubscription.setPfAuth(notificationSubscription.getPfAuth());
        entityManager.merge(existingSubscription); // Use merge() for existing entities
    }
}

}
