package org.openelisglobal.notifications.dao;

import java.util.List;
import javax.persistence.EntityManager;
import org.openelisglobal.notifications.entity.Notification;
import org.openelisglobal.systemuser.valueholder.SystemUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class NotifcationDAOImpl implements NotificationDAO {

  private final EntityManager entityManager;

  public NotifcationDAOImpl(EntityManager entityManager) {

    this.entityManager = entityManager;
  }

  @Override
  @Transactional
  public void save(Notification notification) {

    entityManager.persist(notification);
  }

  @Override
  public List<Notification> getNotifications() {
    return entityManager
        .createQuery("SELECT n FROM Notification n LEFT JOIN FETCH n.user", Notification.class)
        .getResultList();
  }

  @Override
  public List<Notification> getNotificationsByUserId(Long userId) {
    return entityManager
        .createQuery(
            "SELECT n FROM Notification n LEFT JOIN FETCH n.user WHERE n.user.id = :userId",
            Notification.class)
        .setParameter("userId", userId)
        .getResultList();
  }

  @Override
  public List<SystemUser> getSystemUsers() {
    return entityManager
        .createQuery("SELECT u FROM SystemUser u", SystemUser.class)
        .getResultList();
  }
}
