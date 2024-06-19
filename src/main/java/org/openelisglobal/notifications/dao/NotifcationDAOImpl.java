    package org.openelisglobal.notifications.dao;

    import java.util.List;

    import javax.persistence.EntityManager;

    import org.openelisglobal.notifications.entity.Notification;
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
            // TODO Auto-generated method stub
            entityManager.persist(notification);


        }

        @Override
        public List<Notification> getNotifications() {
            return entityManager.createQuery(
                    "SELECT n FROM Notification n LEFT JOIN FETCH n.user", Notification.class)
                    .getResultList();
        }

       
        
    }
